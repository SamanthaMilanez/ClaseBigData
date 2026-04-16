import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")
df.show()
df.printSchema()
df.show(5)
df.describe().show()
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
df.orderBy($"Open".desc).select("Date", "Open").show(1)
//La columna “Close” representa el precio de cierre de la acción al final de la jornada bursátil.//

//Es importante porque://

//Refleja el último valor al que se negoció la acción ese día.//
//Se usa como referencia principal para análisis financiero.//
//Muchos indicadores técnicos se basan en este valor.//
//Permite comparar rendimiento entre días.//
df.select(max("Volume"),min("Volume")).show()