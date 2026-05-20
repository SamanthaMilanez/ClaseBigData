# BigData
Unidad 2
# Práctica 1
Ejercicio regresion lineal

Import LinearRegression

```scala
import org.apache.spark.ml.regression.LinearRegression
```

Configurar errores

```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

Iniciar una sesión Spark

```scala
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder()
  .appName("LinearRegressionExercise")
  .master("local[*]")
  .getOrCreate()
```

```scala
val spark: org.apache.spark.sql.SparkSession =
org.apache.spark.sql.classic.SparkSession@5bda90af
```

Leer archivo CSV Clean-Ecommerce

```scala
val data = spark.read.option("header","true")
  .option("inferSchema","true")
  .csv("Clean-Ecommerce.csv")
```

```scala
val data: org.apache.spark.sql.DataFrame =
[Email: string, Avatar: string ... 5 more fields]
```

Imprimir schema del DataFrame

```scala
data.printSchema()
```

```scala
root
 |-- Email: string (nullable = true)
 |-- Avatar: string (nullable = true)
 |-- Avg Session Length: double (nullable = true)
 |-- Time on App: double (nullable = true)
 |-- Time on Website: double (nullable = true)
 |-- Length of Membership: double (nullable = true)
 |-- Yearly Amount Spent: double (nullable = true)
```

Imprimir un renglón de ejemplo del DataFrame

```scala
data.head(1).foreach(println)
```

```scala
[mstephenson@fernandez.com,Violet,34.49726772511229,12.65565114916675,39.57766801952616,4.0826206329529615,587.9510539684005]
```

Configuración del DataFrame para Machine Learning

Importar VectorAssembler y Vectors

```scala
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```

Crear DataFrame con label y columnas numéricas

```scala
val df = data.select(
  data("Yearly Amount Spent").as("label"),
  data("Avg Session Length"),
  data("Time on App"),
  data("Time on Website"),
  data("Length of Membership")
)
```

```scala
val df: org.apache.spark.sql.DataFrame =
[label: double, Avg Session Length: double ... 3 more fields]
```

Crear objeto VectorAssembler

```scala
val assembler = new VectorAssembler()
  .setInputCols(Array(
    "Avg Session Length",
    "Time on App",
    "Time on Website",
    "Length of Membership"
  ))
  .setOutputCol("features")
```

```scala
val assembler: org.apache.spark.ml.feature.VectorAssembler =
VectorAssembler: uid=vecAssembler_e8671c0170a4,
handleInvalid=error,
numInputCols=4
```

Transformar DataFrame a label y features

```scala
val output = assembler.transform(df).select("label","features")
```

```scala
val output: org.apache.spark.sql.DataFrame =
[label: double, features: vector]
```

Mostrar DataFrame transformado

```scala
output.show(5,false)
```

```scala
+------------------+----------------------------------------------------------------------------+
|label             |features                                                                    |
+------------------+----------------------------------------------------------------------------+
|587.9510539684005 |[34.49726772511229,12.65565114916675,39.57766801952616,4.0826206329529615] |
|392.2049334443264 |[31.92627202636016,11.109460728682564,37.268958868297744,2.66403418213262] |
|487.54750486747207|[33.000914755642675,11.330278057777512,37.110597442120856,4.104543202376424]|
|581.8523440352177 |[34.30555662975554,13.717513665142507,36.72128267790313,3.120178782748092] |
|599.4060920457634 |[33.33067252364639,12.795188551078114,37.53665330059473,4.446308318351434] |
+------------------+----------------------------------------------------------------------------+
only showing top 5 rows
```

Modelo de Regresión Lineal

Crear objeto LinearRegression

```scala
val lr = new LinearRegression()
```

```scala
val lr: org.apache.spark.ml.regression.LinearRegression =
linReg_f194cc8e0afe
```

Ajustar modelo

```scala
val lrModelo = lr.fit(output)
```

```scala
val lrModelo: org.apache.spark.ml.regression.LinearRegressionModel =
LinearRegressionModel: uid=linReg_f194cc8e0afe, numFeatures=4
```

Imprimir coefficients

```scala
println("Coefficients: " + lrModelo.coefficients)
```

```scala
Coefficients:
[25.734271084670716,38.709153810828816,0.43673883558514964,61.57732375487594]
```

Imprimir intercept

```scala
println("Intercept: " + lrModelo.intercept)
```

```scala
Intercept: -1051.5942552990748
```

Métricas del modelo

Crear objeto trainingSummary

```scala
val trainingSummary = lrModelo.summary
```

```scala
val trainingSummary:
org.apache.spark.ml.regression.LinearRegressionTrainingSummary =
org.apache.spark.ml.regression.LinearRegressionTrainingSummary@6ea29a87
```

Mostrar residuals

```scala
trainingSummary.residuals.show()
```

```scala
+-------------------+
| residuals         |
+-------------------+
| -6.788234090018818|
| 11.841128565326073|
| -17.65262700858966|
| 11.454889631178617|
| 7.7833824373080915|
| -1.8347332184773677|
| 4.620232401352382|
| -8.526545950978175|
| 11.012210896516763|
| -13.828032682158891|
+-------------------+
only showing top 10 rows
```

Mostrar RMSE

```scala
println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
```

```scala
RMSE: 9.923256785022229
```

Mostrar MSE

```scala
println(s"MSE: ${trainingSummary.meanSquaredError}")
```

```scala
MSE: 98.47102522148971
```

Mostrar R2

```scala
println(s"R2: ${trainingSummary.r2}")
```

```scala
R2: 0.9843155370226727
```
# Práctica 2

Ejercicio de regresion logistica 

Importar librerías

```scala
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
```

Configurar errores

```scala
Logger.getLogger("org").setLevel(Level.ERROR)
```

Crear sesión Spark

```scala
val spark = SparkSession.builder().getOrCreate()
```

```scala
val spark: org.apache.spark.sql.SparkSession =
org.apache.spark.sql.classic.SparkSession@6cb62e90
```

Leer archivo advertising.csv

```scala
val data = spark.read
  .option("header","true")
  .option("inferSchema", "true")
  .format("csv")
  .load("advertising.csv")
```

```scala
val data: org.apache.spark.sql.DataFrame =
[Daily Time Spent on Site: double, Age: int ... 8 more fields]
```

Imprimir schema del DataFrame

```scala
data.printSchema()
```

```scala
root
 |-- Daily Time Spent on Site: double (nullable = true)
 |-- Age: integer (nullable = true)
 |-- Area Income: double (nullable = true)
 |-- Daily Internet Usage: double (nullable = true)
 |-- Ad Topic Line: string (nullable = true)
 |-- City: string (nullable = true)
 |-- Male: integer (nullable = true)
 |-- Country: string (nullable = true)
 |-- Timestamp: timestamp (nullable = true)
 |-- Clicked on Ad: integer (nullable = true)
```

Despliegue de datos

Imprimir un renglón de ejemplo

```scala
data.head(1)
```

```scala
val res2: Array[org.apache.spark.sql.Row] =
Array([68.95,35,61833.9,256.09,Cloned 5thgeneration orchestration,
Wrightburgh,0,Tunisia,2016-03-27 00:53:11.0,0])
```

Obtener nombres de columnas y primera fila

```scala
val colnames = data.columns
val firstrow = data.head(1)(0)
```

```scala
val colnames: Array[String] =
Array(
Daily Time Spent on Site,
Age,
Area Income,
Daily Internet Usage,
Ad Topic Line,
City,
Male,
Country,
Timestamp,
Clicked on Ad
)

val firstrow: org.apache.spark.sql.Row =
[68.95,35,61833.9,256.09,
Cloned 5thgeneration orchestration,
Wrightburgh,0,Tunisia,
2016-03-27 00:53:11.0,0]
```

Mostrar datos de ejemplo

```scala
println("\n")
println("Example data row")

for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}
```

```scala
Example data row

Age
35

Area Income
61833.9

Daily Internet Usage
256.09

Ad Topic Line
Cloned 5thgeneration orchestration

City
Wrightburgh

Male
0

Country
Tunisia

Timestamp
2016-03-27 00:53:11.0

Clicked on Ad
0
```

Preparar DataFrame para Machine Learning

Crear columna Hour

```scala
val timedata = data.withColumn("Hour",hour(data("Timestamp")))
```

```scala
val timedata: org.apache.spark.sql.DataFrame =
[Daily Time Spent on Site: double, Age: int ... 9 more fields]
```

Seleccionar columnas y renombrar label

```scala
val logregdata = timedata.select(
  data("Clicked on Ad").as("label"),
  $"Daily Time Spent on Site",
  $"Age",
  $"Area Income",
  $"Daily Internet Usage",
  $"Hour",
  $"Male"
)
```

```scala
val logregdata: org.apache.spark.sql.DataFrame =
[label: int, Daily Time Spent on Site: double ... 5 more fields]
```

VectorAssembler

Importar librerías

```scala
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```

Crear assembler

```scala
val assembler = (new VectorAssembler()
  .setInputCols(Array(
    "Daily Time Spent on Site",
    "Age",
    "Area Income",
    "Daily Internet Usage",
    "Hour",
    "Male"
  ))
  .setOutputCol("features"))
```

```scala
val assembler: org.apache.spark.ml.feature.VectorAssembler =
VectorAssembler: uid=vecAssembler_88bcdb3a2a89,
handleInvalid=error,
numInputCols=6
```

División de datos

Crear conjuntos training y test

```scala
val Array(training, test) =
logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)
```

```scala
val training: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] =
[label: int, Daily Time Spent on Site: double ... 5 more fields]

val test: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] =
[label: int, Daily Time Spent on Site: double ... 5 more fields]
```

Pipeline y modelo

Importar Pipeline

```scala
import org.apache.spark.ml.Pipeline
```

Crear LogisticRegression

```scala
val lr = new LogisticRegression()
```

```scala
val lr: org.apache.spark.ml.classification.LogisticRegression =
logreg_b094f13e8ba4
```

Crear pipeline

```scala
val pipeline = new Pipeline().setStages(Array(assembler, lr))
```

```scala
val pipeline: org.apache.spark.ml.Pipeline =
pipeline_54edadaddb6e
```

Ajustar modelo

```scala
val model = pipeline.fit(training)
```

```scala
26/05/20 11:24:15 WARN InstanceBuilder:
Failed to load implementation from:dev.ludovic.netlib.blas.JNIBLAS

val model: org.apache.spark.ml.PipelineModel =
pipeline_54edadaddb6e
```

Transformar datos de prueba

```scala
val results = model.transform(test)
```

```scala
val results: org.apache.spark.sql.DataFrame =
[label: int, Daily Time Spent on Site: double ... 9 more fields]
```

Evaluación del modelo

Importar MulticlassMetrics

```scala
import org.apache.spark.mllib.evaluation.MulticlassMetrics
```

Crear predictionAndLabels

```scala
val predictionAndLabels =
results.select($"prediction",$"label")
  .as[(Double, Double)]
  .rdd
```

```scala
val predictionAndLabels:
org.apache.spark.rdd.RDD[(Double, Double)] =
MapPartitionsRDD[69] at rdd at practicalogisticregression.scala:1
```

Crear métricas

```scala
val metrics = new MulticlassMetrics(predictionAndLabels)
```

```scala
val metrics:
org.apache.spark.mllib.evaluation.MulticlassMetrics =
org.apache.spark.mllib.evaluation.MulticlassMetrics@2badf071
```

Mostrar matriz de confusión

```scala
println("Confusion matrix:")
println(metrics.confusionMatrix)
```

```scala
Confusion matrix:

136.0  1.0
4.0    146.0
```

Mostrar accuracy

```scala
metrics.accuracy
```

```scala
val res8: Double = 0.9825783972125436
```
Análisis

En esta práctica se utilizó el algoritmo de Regresión Logística de Apache Spark MLlib para predecir si un usuario dará clic en un anuncio publicitario.

Primero se cargó el archivo `advertising.csv` y se revisó la estructura de los datos utilizando `printSchema()`. Después se prepararon las variables necesarias para Machine Learning, renombrando la columna `Clicked on Ad` como `label`.

Se creó una nueva columna llamada `Hour` obtenida del campo `Timestamp`, para incluir la hora en la que ocurrió el clic del anuncio.

Posteriormente se utilizó `VectorAssembler` para combinar las variables numéricas en una sola columna llamada `features`, necesaria para entrenar el modelo de Regresión Logística.

Los datos fueron divididos en entrenamiento y prueba utilizando una proporción de 70/30.

Después se creó un `Pipeline` con las etapas de ensamblado de datos y entrenamiento del modelo.

Finalmente se evaluó el modelo utilizando una matriz de confusión y la métrica `accuracy`.

La matriz de confusión obtenida fue:

```scala
136.0  1.0
4.0    146.0
```

Esto indica que el modelo clasificó correctamente la mayoría de los registros, teniendo muy pocos errores de predicción.

La exactitud (`accuracy`) del modelo fue:

```scala
0.9825783972125436
```

El resultado muestra que el modelo tiene una precisión aproximada del 98.25%, por lo que el desempeño del algoritmo fue bastante bueno para predecir si un usuario hará clic en un anuncio.

# Práctica 3
Ejercicio Multilayer Classifier Perceptron
```scala

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

Importar los datos guardados en el formato LIBSVM format como un DataFrame.
```scala
val data = spark.read.format("libsvm").load("C:/Spark/data/mllib/sample_multiclass_classification_data.txt")
val Array(training, test) = data.randomSplit(Array(0.6, 0.4), seed = 12345)
```

Se especifican las capas de la red neuronal:
Capa de entrada de tamaño 4, dos intermedias de tamaño 5 y 4 de salida de tamaño 3. 
```scala
val layers = Array[Int](4, 5, 4, 3)
```

Se crea el entrenador y sus parametos 
```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```

Se entrena el modelo
```scala
val model = trainer.fit(training)
```
Se muestra la precision del set de prueba
```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```scala
Test set accuracy = 0.9607843137254902
```
Este código implementa un modelo de clasificación usando una red neuronal, se usa para aprender a clasificar datos en varias categorías. Primero se cargan los datos en formato LIBSVM y se dividen en entrenamiento y prueba utilizando una seed, que es un valor que hace que siempre obtengas los mismos resultados al ejecutar el código. Luego se definen las capas de la red neuronal, donde cada número representa la cantidad de neuronas en cada capa: la capa de entrada (4) corresponde a las variables de entrada, las capas intermedias (5 y 4) son las capas ocultas que procesan la información y aprenden patrones, y la capa de salida (3) representa las clases posibles a predecir. Después se configura el modelo indicando parámetros como el número máximo de iteraciones y la semilla, se entrena con los datos de entrenamiento y finalmente se evalúa su desempeño con los datos de prueba, calculando la precisión que indica qué tan bien el modelo clasifica correctamente los datos. En este caso la precisión fue de el 96.07%

# Práctica 4
Ejercicio Decision Tree
```scala
import org.apache.spark.sql.SparkSession

```
Crear una sesion Spark
```scala
val spark = SparkSession.builder()
  .appName("DecisionTreeCreditExample")
  .getOrCreate()
```

Se crea el dataset con las variables. Este es para determinar si se puede dar un credito o no.
```scala
val data = Seq(
  (1.0,0.0,1.0,1.0),
  (1.0,0.0,1.0,1.0),
  (1.0,1.0,1.0,1.0),
  (1.0,0.0,0.0,0.0),
  (1.0,1.0,0.0,0.0),
  (0.0,1.0,0.0,0.0),
  (0.0,1.0,0.0,0.0),
  (0.0,0.0,1.0,1.0),
  (0.0,1.0,1.0,0.0),
  (0.0,0.0,0.0,0.0)
).toDF("income_high","has_debt","good_history","label")

data.show()
```
```scala

+-----------+--------+------------+-----+
|income_high|has_debt|good_history|label|
+-----------+--------+------------+-----+
|        1.0|     0.0|         1.0|  1.0|
|        1.0|     0.0|         1.0|  1.0|
|        1.0|     1.0|         1.0|  1.0|
|        1.0|     0.0|         0.0|  0.0|
|        1.0|     1.0|         0.0|  0.0|
|        0.0|     1.0|         0.0|  0.0|
|        0.0|     1.0|         0.0|  0.0|
|        0.0|     0.0|         1.0|  1.0|
|        0.0|     1.0|         1.0|  0.0|
|        0.0|     0.0|         0.0|  0.0|
+-----------+--------+------------+-----+
```
Spark necesita un vector de features:

```scala
import org.apache.spark.ml.feature.VectorAssembler

val assembler = new VectorAssembler()
.setInputCols(Array("income_high","has_debt","good_history"))
.setOutputCol("features")

val dataset = assembler.transform(data)
dataset.select("features","label").show()
```
```scala
+-------------+-----+
|     features|label|
+-------------+-----+
|[1.0,0.0,1.0]|  1.0|
|[1.0,0.0,1.0]|  1.0|
|[1.0,1.0,1.0]|  1.0|
|[1.0,0.0,0.0]|  0.0|
|[1.0,1.0,0.0]|  0.0|
|[0.0,1.0,0.0]|  0.0|
|[0.0,1.0,0.0]|  0.0|
|[0.0,0.0,1.0]|  1.0|
|[0.0,1.0,1.0]|  0.0|
|    (3,[],[])|  0.0|
+-------------+-----+
```

Dividimos datos en:
70% entrenamiento → aprender patrones 
30% prueba → evaluar modelo
```scala
val Array(trainingData, testData) = 
dataset.randomSplit(Array(0.7, 0.3), seed = 42)

```
Configuramos el algoritmo
• Parámetros clave:
labelCol → qué queremos predecir 
featuresCol → variables de entrada 
maxDepth → complejidad del árbol 
maxDepth = 3 significa: máximo 3 niveles de preguntas.
```scala
import org.apache.spark.ml.classification.DecisionTreeClassifier
val dt = new DecisionTreeClassifier()
.setLabelCol("label")
.setFeaturesCol("features")
.setMaxDepth(3)
```
Entrenamiento del modelo
```scala
val model = dt.fit(trainingData)
```
Spark nos muestra las reglas que descubrió analizando los datos.
Ejemplo:
If (feature 2 <= 0.5)
Predict: 0.0 (rechazar crédito)
Else
Predict: 1.0 (aprobar crédito)

```scala
println(model.toDebugString)
```

```scala
DecisionTreeClassificationModel: uid=dtc_8acdd6fa1533, depth=2, numNodes=5, numClasses=2, numFeatures=3
  If (feature 1 <= 0.5)
   If (feature 2 <= 0.5)
    Predict: 0.0
   Else (feature 2 > 0.5)
    Predict: 1.0
  Else (feature 1 > 0.5)
   Predict: 0.0
```
Aplicamos el árbol a clientes nuevos para predecir si pagarían el crédito

```scala
val predictions = model.transform(testData)
predictions.select("features","label","prediction","probability")
.show(false)

```

```scala
+-------------+-----+----------+-----------+
|features     |label|prediction|probability|
+-------------+-----+----------+-----------+
|[1.0,0.0,1.0]|1.0  |1.0       |[0.0,1.0]  |
|[1.0,1.0,1.0]|1.0  |0.0       |[1.0,0.0]  |
|[1.0,0.0,0.0]|0.0  |0.0       |[1.0,0.0]  |
+-------------+-----+----------+-----------+
```

Medimos qué tan bien generaliza el modelo con datos reales
```scala
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
val evaluator = new MulticlassClassificationEvaluator()
.setLabelCol("label")
.setPredictionCol("prediction")
.setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println("Accuracy = " + accuracy)
```
```scala
Accuracy = 0.6666666666666666
```
Un modelo de árbol de decisión se usa para predecir un resultado por ejemplo en este caso, si un credito es aprobado o no a partir de ciertas datos como ingresos, deudas e historial. Primero crea un conjunto de datos sencillo y los organiza en un formato, luego junta esas variables en una sola columna llamada features. Después divide los datos en entrenamiento y prueba usando una seed para que la división sea siempre la misma. El modelo de árbol de decisión aprende las reglas tipo “si pasa A, entonces es B" como un diagrama de decisiones que se suele usar en programacion para crear condiciones o eventos. 


# Práctica 5
Random Forest Classifier. LVGG

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


# Práctica 6
Naive Bayes. LVGG

```scala
//Importa librería para utilizar el algoritmo de clasificación Naive Bayes
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Carga los datos desde un archivo de texto en formato LIBSVM y lo convierte en un DataFrame, estructurándolo automáticamente con dos columnas: label (etiqueta) y features (vector de características).
val data = spark.read.format("libsvm").load("C:/spark/data/mllib/sample_libsvm_data.txt")
```
```sh
val data: org.apache.spark.sql.DataFrame = [label: double, features: vector]
```
```scala
//División aleatorea del conjunto de datos, en datos de entrenamiento y prueba.
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)
```
```sh
val trainingData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
val testData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
```
```scala
//Crea y entrena un modelo de clasificación Naive Bayes usando los datos de entrenamiento.
val model = new NaiveBayes().fit(trainingData)
```
```sh
val model: org.apache.spark.ml.classification.NaiveBayesModel = NaiveBayesModel: uid=nb_5114205e4ff5, modelType=multinomial, numClasses=2, numFeatures=692
```

```scala
//Aplica el modelo entrenado a los datos de prueba para generar predicciones.
val predictions = model.transform(testData)
//Imprime las filas y columnas con los resultados de las predicciones generadas por el modelo
predictions.show()
```
```sh
val predictions: org.apache.spark.sql.DataFrame = [label: double, features: vector ... 3 more fields]

+-----+--------------------+--------------------+-----------+----------+
|label|            features|       rawPrediction|probability|prediction|
+-----+--------------------+--------------------+-----------+----------+
|  0.0|(692,[95,96,97,12...|[-173266.38465085...|  [1.0,0.0]|       0.0|
|  0.0|(692,[98,99,100,1...|[-176798.24796349...|  [1.0,0.0]|       0.0|
|  0.0|(692,[122,123,124...|[-189371.23080028...|  [1.0,0.0]|       0.0|
|  0.0|(692,[126,127,128...|[-210969.37526481...|  [1.0,0.0]|       0.0|
|  0.0|(692,[127,128,129...|[-170881.90406252...|  [1.0,0.0]|       0.0|
|  0.0|(692,[127,128,129...|[-213398.60801697...|  [1.0,0.0]|       0.0|
|  0.0|(692,[127,128,129...|[-183284.52661405...|  [1.0,0.0]|       0.0|
|  0.0|(692,[128,129,130...|[-246027.39704974...|  [1.0,0.0]|       0.0|
|  0.0|(692,[150,151,152...|[-157898.87276406...|  [1.0,0.0]|       0.0|
|  0.0|(692,[152,153,154...|[-208299.36235153...|  [1.0,0.0]|       0.0|
|  0.0|(692,[152,153,154...|[-243127.71890150...|  [1.0,0.0]|       0.0|
|  0.0|(692,[153,154,155...|[-144207.79475583...|  [1.0,0.0]|       0.0|
|  1.0|(692,[100,101,102...|[-144208.40561310...|  [0.0,1.0]|       1.0|
|  1.0|(692,[123,124,125...|[-138363.44872824...|  [0.0,1.0]|       1.0|
|  1.0|(692,[124,125,126...|[-127978.05376288...|  [0.0,1.0]|       1.0|
|  1.0|(692,[124,125,126...|[-79957.487724508...|  [0.0,1.0]|       1.0|
|  1.0|(692,[125,126,127...|[-102430.14231250...|  [0.0,1.0]|       1.0|
|  1.0|(692,[125,126,127...|[-81588.939249410...|  [0.0,1.0]|       1.0|
|  1.0|(692,[126,127,128...|[-118122.23190317...|  [0.0,1.0]|       1.0|
|  1.0|(692,[126,127,128...|[-80661.473798128...|  [0.0,1.0]|       1.0|
+-----+--------------------+--------------------+-----------+----------+
only showing top 20 rows
```

```scala
//Crea y configura un evaluador para medir la precisión del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
```
```sh
val evaluator: org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator = MulticlassClassificationEvaluator: uid=mcEval_cef6bd9cd2fc, metricName=accuracy, metricLabel=0.0, beta=1.0, eps=1.0E-15
```

```scala
//Evalúa las predicciones del modelo y calcula la métrica de precisión
val accuracy = evaluator.evaluate(predictions)
println(s"Test set accuracy = $accuracy")
```
```sh
val accuracy: Double = 1.0
Test set accuracy = 1.0
```