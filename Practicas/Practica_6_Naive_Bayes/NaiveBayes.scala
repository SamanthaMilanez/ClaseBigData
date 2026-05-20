//Importa librería para utilizar el algoritmo de clasificación Naive Bayes
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator


//Carga los datos desde un archivo de texto en formato LIBSVM y lo convierte en un DataFrame, estructurándolo automáticamente con dos columnas: label (etiqueta) y features (vector de características).
val data = spark.read.format("libsvm").load("C:/spark/data/mllib/sample_libsvm_data.txt")


//División aleatorea del conjunto de datos, en datos de entrenamiento y prueba.
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)


//Crea y entrena un modelo de clasificación Naive Bayes usando los datos de entrenamiento.
val model = new NaiveBayes().fit(trainingData)


//Aplica el modelo entrenado a los datos de prueba para generar predicciones.
val predictions = model.transform(testData)
//Imprime las filas y columnas con los resultados de las predicciones generadas por el modelo
predictions.show()



//Crea y configura un evaluador para medir la precisión del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")


//Evalúa las predicciones del modelo y calcula la métrica de precisión
val accuracy = evaluator.evaluate(predictions)
println(s"Test set accuracy = $accuracy")