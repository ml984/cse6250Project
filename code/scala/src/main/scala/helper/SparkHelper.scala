package helper

import org.apache.spark.sql.{ SQLContext, SparkSession }
import org.apache.spark.{ SparkConf, SparkContext }

object SparkHelper {
  lazy val sparkMasterURL = "local[*]"

  lazy val spark: SparkSession = SparkHelper.createSparkSession(
    appName = "CSE 6250 Final Project",
    masterUrl = sparkMasterURL,
    cfg = {
      _.set("spark.executor.memory", "10G")
        .set("spark.driver.memory", "10G")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .set("spark.kryoserializer.buffer", "24")
    })

  lazy val sc: SparkContext = spark.sparkContext

  lazy val sqlContext: SQLContext = spark.sqlContext

  def createSparkSession(
    appName: String,
    masterUrl: String = sparkMasterURL,
    cfg: SparkConf => SparkConf = { in => in }): SparkSession = {
    val session = SparkSession.builder().config(sparkConf(appName, masterUrl, cfg)).getOrCreate()
    session
  }

  def sparkConf(appName: String, masterUrl: String, cfg: SparkConf => SparkConf): SparkConf = {
    cfg(new SparkConf()
      .setAppName(appName)
      .setMaster(masterUrl)
      .set("spark.driver.memory", "10G")
      .set("spark.driver.maxResultSize", "10G")
    // .setMaster("local[1]")
    )
  }

  def createSparkSession: SparkSession = createSparkSession("CSE 6250 Final Project")
}
