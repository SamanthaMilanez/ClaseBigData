
// Import LinearRegression
import org.apache.spark.ml.regression.LinearRegression

// Opcional: Utilice el siguiente codigo para configurar errores
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Inicie una simple Sesion Spark
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().appName("LinearRegressionExercise").master("local[*]").getOrCreate()

// Utilice Spark para el archivo csv Clean-Ecommerce .
val data = spark.read.option("header","true").option("inferSchema","true").csv("Clean-Ecommerce.csv")

// Imprima el schema en el DataFrame.
data.printSchema()

// Imprima un renglon de ejemplo del DataFrame.
data.head(1).foreach(println)



//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

// Transforme el data frame para que tome la forma de
// ("label","features")

// Importe VectorAssembler y Vectors
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Renombre la columna Yearly Amount Spent como "label"
// Tambien de los datos tome solo la columa numerica
// Deje todo esto como un nuevo DataFrame que se llame df

val df = data.select(data("Yearly Amount Spent").as("label"),data("Avg Session Length"),data("Time on App"),data("Time on Website"),data("Length of Membership"))

// Que el objeto assembler convierta los valores de entrada a un vector

// Utilice el objeto VectorAssembler para convertir la columnas de entradas del df
// a una sola columna de salida de un arreglo llamado  "features"
// Configure las columnas de entrada de donde se supone que leemos los valores.
// Llamar a esto nuevo assembler.

val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length","Time on App","Time on Website","Length of Membership")).setOutputCol("features")

// Utilice el assembler para transformar nuestro DataFrame
// a dos columnas: label and features

val output = assembler.transform(df).select("label","features")

output.show(5,false)


// Crear un objeto para modelo de regresion lineal.

val lr = new LinearRegression()

// Ajuste el modelo para los datos y llame a este modelo lrModelo

val lrModelo = lr.fit(output)

// Imprima the coefficients y intercept para la regresion lineal

println("Coefficients: " + lrModelo.coefficients)
println("Intercept: " + lrModelo.intercept)


// Resuma el modelo sobre el conjunto de entrenamiento
// imprima la salida de algunas metricas!

// Utilize metodo .summary de nuestro modelo
// para crear un objeto llamado trainingSummary

val trainingSummary = lrModelo.summary

// Muestre los valores de residuals, el RMSE,
// el MSE, y tambien el R^2 .

trainingSummary.residuals.show()

println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"MSE: ${trainingSummary.meanSquaredError}")
println(s"R2: ${trainingSummary.r2}")
