import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.feature.StringIndexer

//1.A-CARGAR DATOS
val data = spark.read.option("header","true").option("inferSchema","true").csv("C:/Users/LV/Documents/PracticaEv3BD/iris.csv")
//val data  = spark.read.option("header","true").option("inferSchema", "true").format("").load("iris.csv")

//1.B-Limpieza y Preparación de datos
val df = data.select(data("species").as("label"), $"sepal_length", $"sepal_width", $"petal_length", $"petal_width")

//1.B-Transformar a vector para que el algoritmo ml pueda leer la entrada
val assembler = new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width")).setOutputCol("features")
val output = assembler.transform(df).select($"label", $"features")
output.show()


//2-Mostrar nombre de columnas
val nombresColumnas: Array[String] = df.columns
// Imprime nombre de las columas
nombresColumnas.foreach(println)

//3-Mostrar el esquema del nuevo DataFrame preparado
df.printSchema

//4-Imprimir los primeros 5 registros 
df.show(5)

//5-El método describe realiza calculos estadisticos como cantidad de registros, valores mínimos y máximos
df.describe().show()
//<<<PRIMER COMMIT

//6-dividir los datos en conjuntos de entrenamiento y prueba, y posteriormente entrenar el modelo
val Array(training, test) = output.randomSplit(Array(0.8, 0.2), seed = 1234L)
//6.1-Convertir la columna categórica en índice numérico
val indexer = new StringIndexer().setInputCol("label").setOutputCol("labelIndex").fit(training)

//6-Aplicar la transformación a tus datos de entrenamiento
val trainingIndexed = indexer.transform(training)
//Con el siguiente código se puede ver cómo queda la etiqueta con su índice
indexer.labels.zipWithIndex.foreach { case (label, index) => println(label + " -> " + index) }

//6-Se genera nuevo DataFrame (testIndexed) que contiene la columna indexada necesaria para que el modelo procese las etiquetas.
val testIndexed = indexer.transform(test)
//6-Impresión del dataframe
testIndexed.show()


// 7-specify layers for the neural network:
// input layer of size 4 (features), two intermediate of size 5 and 4
// and output of size 3 (classes). 
//Definir la arquitectura de la red neuronal del algoritmo MultilayerPerceptronClassifier
val layers = Array[Int](4, 5, 4, 3)

//7-create the trainer and set its parameters
//val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
//crea una red neuronal multicapa, define su arquitectura, especifica las etiquetas a clasificar 
//y establece las variables de entrada que utilizará el modelo de Machine Learning.
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("labelIndex") .setFeaturesCol("features")

//7-Almacena el modelo configurado, listo para entrenarse
//Se obtiene el modelo de entrenamiento, el que conoce los patrones de los datos. El método fit, ordena al algoritmo a 
//realizar cálculos matemáticos para ajustar los pesos de la red neuronal.
//trainingIndexed, Es el conjunto de datos con el que la red neuronal estudiará para aprender a clasificar.
val model = trainer.fit(trainingIndexed)

//<<<SEGUNDO COMMIT

//8-Transformar el conjunto de prueba indexado
val result = model.transform(testIndexed)
//val predictionAndLabels = result.select("prediction", "label")
//8.A-Configurar el evaluador para usar la columna numérica
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("labelIndex").setPredictionCol("prediction").setMetricName("accuracy")
//8.B-Usando la interpolación correcta 
val accuracy = evaluator.evaluate(result)

println(s"Test set accuracy = $accuracy")



