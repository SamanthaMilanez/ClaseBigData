import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")
df.show()
df.printSchema()
df.show(5)
df.describe().show()
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
