//Se importaron las librerías de Random Forest en Spark MLlib
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

//Carga de datos del archivo sample_libsvm_data.txt, ubicado en la instalación local de Spark. 
//Archivo en formato LIBSVM que se carga en un DataFrame, con una estructura de dos columnas: label y features 
val data = spark.read.format("libsvm").load("C:/spark/data/mllib/sample_libsvm_data.txt")

//Convierte etiquetas de tipo texto (o categóricas) en índices numéricos. Y ajusta los datos completos para incluir todas las etiquetas en el índice.
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

//Se crea y se entrena un VectorIndexer para automatizar el manejo de atributos categóricos dentro de un vector de características, dentro de la columna features
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

//Se separan los datos de entrenamiento y evaluación 
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

//Se crea y configura un modelo de clasificación Random Forest.
//La columna indexedLabel se establece como la variable objetivo y la columna indexedFeatures como las variables predictoras.
//El modelo utilizará 10 arboles
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)

//Se revierte la transformación numérica realizada previamente para facilitar la interpretación de los resultados de predicción
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labelsArray(0))

//Se define un flujo de trabajo, encadenando secuencialmente las etapas necesarias para procesar los datos y entrenar el modelo
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))

//Se entrena el flujo de trabajo completo utilizando los datos proporcionados.
//Se genera un objeto model que contiene los parámetros aprendidos y las transformaciones ajustadas.
val model = pipeline.fit(trainingData)

//Se aplica el modelo entrenado a los datos de prueba testData para generar predicciones.
val predictions = model.transform(testData)

//Muestra una vista previa de los resultados del modelo
predictions.select("predictedLabel", "label", "features").show(5)

//Configurar un evaluador de rendimiento, para posteriormente evaluar los resultados obtenidos.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
//Evalua las predicciones del modelo y calcula el porcentaje de aciertos del modelo
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

//Extrae y recupera el modelo de Random Forest entrenado y lo convierte al tipo correspondiente
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")
