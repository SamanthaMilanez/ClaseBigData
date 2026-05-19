////////////////////////////////////////////
//// LINEAR REGRESSION EXERCISE ///////////
////////////////////////////////////////////

import org.apache.spark.ml.regression.LinearRegression

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

import spark.implicits._

// Leer archivo CSV
val data = spark.read.option("header","true").option("inferSchema","true").csv("Clean-Ecommerce.csv")

// Mostrar schema
data.printSchema()

// Mostrar ejemplo de datos
data.show(5)

//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Crear DataFrame con label y columnas numéricas
val df = data.select(
  data("Yearly Amount Spent").as("label"),
  $"Avg Session Length",
  $"Time on App",
  $"Time on Website",
  $"Length of Membership"
)

// Crear assembler
val assembler = new VectorAssembler()
  .setInputCols(Array(
    "Avg Session Length",
    "Time on App",
    "Time on Website",
    "Length of Membership"
  ))
  .setOutputCol("features")

// Transformar datos
val output = assembler.transform(df)
  .select("label","features")

// Mostrar datos transformados
output.show(5,false)

//////////////////////////////////////////////////////
//////////// Modelo de Regresión Lineal //////////////
//////////////////////////////////////////////////////

// Crear modelo
val lr = new LinearRegression()

// Entrenar modelo
val lrModel = lr.fit(output)

// Mostrar coeficientes e intercept
println("Coefficients: " + lrModel.coefficients)
println("Intercept: " + lrModel.intercept)

//////////////////////////////////////////////////////
//////////// Resumen del Modelo //////////////////////
//////////////////////////////////////////////////////

// Obtener resumen
val trainingSummary = lrModel.summary

// Mostrar residuals
trainingSummary.residuals.show()

// Mostrar métricas
println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"MSE: ${trainingSummary.meanSquaredError}")
println(s"R2: ${trainingSummary.r2}")

