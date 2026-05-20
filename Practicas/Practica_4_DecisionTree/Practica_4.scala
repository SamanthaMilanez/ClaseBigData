import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder()
  .appName("DecisionTreeCreditExample")
  .getOrCreate()

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


import org.apache.spark.ml.feature.VectorAssembler

val assembler = new VectorAssembler()
  .setInputCols(Array("income_high","has_debt","good_history"))
  .setOutputCol("features")

val dataset = assembler.transform(data)
dataset.select("features","label").show()


val Array(trainingData, testData) = dataset.randomSplit(Array(0.7, 0.3), seed = 42)


import org.apache.spark.ml.classification.DecisionTreeClassifier

val dt = new DecisionTreeClassifier()
  .setLabelCol("label")
  .setFeaturesCol("features")
  .setMaxDepth(3)


val model = dt.fit(trainingData)


println(model.toDebugString)



val predictions = model.transform(testData)

predictions.select("features","label","prediction","probability").show(false)


import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("label")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")

val accuracy = evaluator.evaluate(predictions)

println("Accuracy = " + accuracy)