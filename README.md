# BigData
Unidad 3 Practica Evaluatoria
# Machine Learning

Importar librerias

```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.feature.StringIndexer
```
1. A-Cargar Datos

```scala
val data = spark.read
  .option("header", "true")
  .option("inferSchema", "true")
  .format("csv")
  .load("C:/Users/jessu/OneDrive/Documents/Proyectos/ClaseBigData/Practica_evaluatoria/iris.csv")
  ////val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
```
1. B-Limpieza y Preparación de datos

```scala
val df = data.select(data("species").as("label"), $"sepal_length", $"sepal_width", $"petal_length", $"petal_width")

```
Aqui se muestra el dataframe que se creo
```scala
data.printSchema()
root
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)
 |-- species: string (nullable = true)

```
1. B-Transformar a vector para que el algoritmo ml pueda leer la entrada
```scala
val assembler = new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width")).setOutputCol("features")

val output = assembler.transform(df).select($"label", $"features")
output.show()
```
Muestra los datos transformados
```scala
+------+-----------------+
| label|         features|
+------+-----------------+
|setosa|[5.1,3.5,1.4,0.2]|
|setosa|[4.9,3.0,1.4,0.2]|
|setosa|[4.7,3.2,1.3,0.2]|
|setosa|[4.6,3.1,1.5,0.2]|
|setosa|[5.0,3.6,1.4,0.2]|
|setosa|[5.4,3.9,1.7,0.4]|
|setosa|[4.6,3.4,1.4,0.3]|
|setosa|[5.0,3.4,1.5,0.2]|
|setosa|[4.4,2.9,1.4,0.2]|
|setosa|[4.9,3.1,1.5,0.1]|
|setosa|[5.4,3.7,1.5,0.2]|
|setosa|[4.8,3.4,1.6,0.2]|
|setosa|[4.8,3.0,1.4,0.1]|
|setosa|[4.3,3.0,1.1,0.1]|
|setosa|[5.8,4.0,1.2,0.2]|
|setosa|[5.7,4.4,1.5,0.4]|
|setosa|[5.4,3.9,1.3,0.4]|
|setosa|[5.1,3.5,1.4,0.3]|
|setosa|[5.7,3.8,1.7,0.3]|
|setosa|[5.1,3.8,1.5,0.3]|
+------+-----------------+
only showing top 20 rows

```
2. Mostrar nombre de columnas

```scala
val nombresColumnas: Array[String] = df.columns

```
Se imprime el nombre de las columnas
```scala
nombresColumnas.foreach(println)

label
sepal_length
sepal_width
petal_length
petal_width

```

3. Mostrar el esquema del nuevo DataFrame preparado

```scala
df.printSchema

root
 |-- label: string (nullable = true)
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)

```

4. Imprimir los primeros 5 registros 

```scala 
df.show(5)

+------+------------+-----------+------------+-----------+
| label|sepal_length|sepal_width|petal_length|petal_width|
+------+------------+-----------+------------+-----------+
|setosa|         5.1|        3.5|         1.4|        0.2|
|setosa|         4.9|        3.0|         1.4|        0.2|
|setosa|         4.7|        3.2|         1.3|        0.2|
|setosa|         4.6|        3.1|         1.5|        0.2|
|setosa|         5.0|        3.6|         1.4|        0.2|
+------+------------+-----------+------------+-----------+
only showing top 5 rows

```
5. El método describe realiza calculos estadisticos como cantidad de registros, valores mínimos y máximos
```scala
26/05/23 07:17:02 WARN SparkStringUtils: Truncated the string representation of a plan since it was too large. This behavior can be adjusted by setting 'spark.sql.debug.maxToStringFields'.
[Stage 8:>                                  [Stage 10:>                                                                             +-------+---------+------------------+-------------------+------------------+------------------+
|summary|    label|      sepal_length|        sepal_width|      petal_length|       petal_width|
+-------+---------+------------------+-------------------+------------------+------------------+
|  count|      150|               150|                150|               150|               150|
|   mean|     NULL| 5.843333333333335| 3.0540000000000007|3.7586666666666693|1.1986666666666672|
| stddev|     NULL|0.8280661279778637|0.43359431136217375| 1.764420419952262|0.7631607417008414|
|    min|   setosa|               4.3|                2.0|               1.0|               0.1|
|    max|virginica|               7.9|                4.4|               6.9|               2.5|
+-------+---------+------------------+-------------------+------------------+------------------+

```
