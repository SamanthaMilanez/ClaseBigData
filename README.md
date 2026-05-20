

# BigData
Unidad 2
# Práctica 5
Random Forest Classifier

```scala
//Importar las librerías de Random Forest en Spark MLlib
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

//Carga de datos del archivo sample_libsvm_data.txt, ubicado en la instalación local de Spark. 
//Archivo en formato LIBSVM que se carga en un DataFrame, con una estructura de dos columnas: label y features 
val data = spark.read.format("libsvm").load("C:/spark/data/mllib/sample_libsvm_data.txt")
```
```sh
val data: org.apache.spark.sql.DataFrame = [label: double, features: vector] 
```

```scala
//Convierte etiquetas de tipo texto (o categóricas) en índices numéricos. Y ajusta los datos completos para incluir todas las etiquetas en el índice.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```
```sh
val labelIndexer: org.apache.spark.ml.feature.StringIndexerModel = StringIndexerModel: uid=strIdx_620a32527bfd, handleInvalid=error
```

```scala
//Se crea y se entrena un VectorIndexer para automatizar el manejo de atributos categóricos dentro de un vector de características, dentro de la columna features
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```
```sh
val featureIndexer: org.apache.spark.ml.feature.VectorIndexerModel = VectorIndexerModel: uid=vecIdx_119148dd4faf, numFeatures=692, handleInvalid=error
``` 
```scala
//Se separan los datos de entrenamiento y evaluación 
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```
```sh
val trainingData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
val testData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
```
```scala
//Se crea y configura un modelo de clasificación Random Forest.
//La columna indexedLabel se establece como la variable objetivo y la columna indexedFeatures como las variables predictoras.
//El modelo utilizará 10 arboles
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)
```
```sh
val rf: org.apache.spark.ml.classification.RandomForestClassifier = rfc_0777029d235a
```
```scala
//Se revierte la transformación numérica realizada previamente para facilitar la interpretación de los resultados de predicción
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labelsArray(0))
```
```sh
val labelConverter: org.apache.spark.ml.feature.IndexToString = idxToStr_88bf9edfaf12
```
```scala
//Se define un flujo de trabajo, encadenando secuencialmente las etapas necesarias para procesar los datos y entrenar el modelo
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
```
```sh
val pipeline: org.apache.spark.ml.Pipeline = pipeline_747cd549cc22
```

```scala
//Se entrena el flujo de trabajo completo utilizando los datos proporcionados.
//Se genera un objeto model que contiene los parámetros aprendidos y las transformaciones ajustadas.
val model = pipeline.fit(trainingData)
```
```sh
val model: org.apache.spark.ml.PipelineModel = pipeline_747cd549cc22 
```

```scala
//Se aplica el modelo entrenado a los datos de prueba testData para generar predicciones.
val predictions = model.transform(testData)
```
```sh
val predictions: org.apache.spark.sql.DataFrame = [label: double, features: vector ... 6 more fields]
```

```scala
//Muestra una vista previa de los resultados del modelo
predictions.select("predictedLabel", "label", "features").show(5)
```
```sh
+--------------+-----+--------------------+
|predictedLabel|label|            features|
+--------------+-----+--------------------+
|           0.0|  0.0|(692,[121,122,123...|
|           0.0|  0.0|(692,[123,124,125...|
|           0.0|  0.0|(692,[124,125,126...|
|           0.0|  0.0|(692,[124,125,126...|
|           0.0|  0.0|(692,[126,127,128...|
+--------------+-----+--------------------+
only showing top 5 rows
```

```scala
//Configurar un evaluador de rendimiento, para posteriormente evaluar los resultados obtenidos.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
```
```sh
val evaluator: org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator = MulticlassClassificationEvaluator: uid=mcEval_111481061ba8, metricName=accuracy, metricLabel=0.0, beta=1.0, eps=1.0E-15
```

```scala
//Evalua las predicciones del modelo y calcula el porcentaje de aciertos del modelo
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
```
```sh
val accuracy: Double = 1.0
Test Error = 0.0
```

```scala
//Extrae y recupera el modelo de Random Forest entrenado y lo convierte al tipo correspondiente
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
```
```sh
val rfModel: org.apache.spark.ml.classification.RandomForestClassificationModel = RandomForestClassificationModel: uid=rfc_0777029d235a, numTrees=10, numClasses=2, numFeatures=692
```
```scala
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")
```
```sh
Learned classification forest model:
 RandomForestClassificationModel: uid=rfc_0777029d235a, numTrees=10, numClasses=2, numFeatures=692
  Tree 0 (weight 1.0):
    If (feature 455 <= 11.5)
     If (feature 521 <= 91.5)
      If (feature 457 <= 5.0)
       Predict: 0.0
      Else (feature 457 > 5.0)
       Predict: 1.0
     Else (feature 521 > 91.5)
      Predict: 1.0
    Else (feature 455 > 11.5)
     Predict: 1.0
  Tree 1 (weight 1.0):
    If (feature 604 <= 90.0)
     If (feature 426 <= 3.5)
      If (feature 355 <= 5.5)
       Predict: 0.0
      Else (feature 355 > 5.5)
       Predict: 1.0
     Else (feature 426 > 3.5)
      Predict: 1.0
    Else (feature 604 > 90.0)
     If (feature 434 <= 70.5)
      Predict: 1.0
     Else (feature 434 > 70.5)
      Predict: 0.0
  Tree 2 (weight 1.0):
    If (feature 463 <= 2.0)
     If (feature 461 <= 56.5)
      Predict: 1.0
     Else (feature 461 > 56.5)
      Predict: 0.0
    Else (feature 463 > 2.0)
     Predict: 0.0
  Tree 3 (weight 1.0):
    If (feature 469 <= 4.0)
     If (feature 356 <= 16.0)
      If (feature 467 <= 70.0)
       If (feature 273 <= 30.0)
        Predict: 0.0
       Else (feature 273 > 30.0)
        Predict: 1.0
      Else (feature 467 > 70.0)
       Predict: 1.0
     Else (feature 356 > 16.0)
      Predict: 1.0
    Else (feature 469 > 4.0)
     Predict: 1.0
  Tree 4 (weight 1.0):
    If (feature 540 <= 87.0)
     If (feature 565 <= 15.5)
      Predict: 0.0
     Else (feature 565 > 15.5)
      Predict: 1.0
    Else (feature 540 > 87.0)
     Predict: 1.0
  Tree 5 (weight 1.0):
    If (feature 378 <= 16.0)
     Predict: 1.0
    Else (feature 378 > 16.0)
     If (feature 678 in {0.0})
      If (feature 155 <= 254.5)
       Predict: 0.0
      Else (feature 155 > 254.5)
       Predict: 1.0
     Else (feature 678 not in {0.0})
      Predict: 1.0
  Tree 6 (weight 1.0):
    If (feature 511 <= 1.5)
     If (feature 457 <= 5.0)
      Predict: 0.0
     Else (feature 457 > 5.0)
      Predict: 1.0
    Else (feature 511 > 1.5)
     Predict: 1.0
  Tree 7 (weight 1.0):
    If (feature 518 <= 18.0)
     If (feature 432 <= 56.0)
      Predict: 1.0
     Else (feature 432 > 56.0)
      Predict: 0.0
    Else (feature 518 > 18.0)
     Predict: 0.0
  Tree 8 (weight 1.0):
    If (feature 385 <= 4.0)
     If (feature 287 <= 8.5)
      If (feature 490 <= 27.5)
       Predict: 1.0
      Else (feature 490 > 27.5)
       Predict: 0.0
     Else (feature 287 > 8.5)
      Predict: 1.0
    Else (feature 385 > 4.0)
     Predict: 1.0
  Tree 9 (weight 1.0):
    If (feature 469 <= 4.0)
     If (feature 327 <= 19.5)
      If (feature 406 <= 9.5)
       Predict: 1.0
      Else (feature 406 > 9.5)
       Predict: 0.0
     Else (feature 327 > 19.5)
      If (feature 600 <= 127.0)
       Predict: 0.0
      Else (feature 600 > 127.0)
       Predict: 1.0
    Else (feature 469 > 4.0)
     Predict: 1.0
```
