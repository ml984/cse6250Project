package main

import helper.SparkHelper
import model.PhysiologicalData
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]) {
    transform(SparkHelper.spark)
  }

  def transform(spark: SparkSession): Unit = {
    import spark.implicits._

    val sc = spark.sparkContext
    var fileNameContentPair = sc.wholeTextFiles("data")

    val rows = fileNameContentPair.map {
      case (filename, content) => {
        var rows = content.split("\n").drop(1).map(x => x.split("\\|"))

        var HR_base: Double = Double.NaN
        var O2Sat_base: Double = Double.NaN
        var Glucose_base: Double = Double.NaN
        var Lactate_base: Double = Double.NaN
        var Potassium_base: Double = Double.NaN

        for (i <- 0 until rows.length) {
          if (HR_base.isNaN && !(rows(i)(0).toDouble.isNaN)) {
            HR_base = rows(i)(0).toDouble
          }
          if (O2Sat_base.isNaN && !(rows(i)(1).toDouble.isNaN)) {
            O2Sat_base = rows(i)(1).toDouble
          }
          if (Glucose_base.isNaN && !(rows(i)(21).toDouble.isNaN)) {
            Glucose_base = rows(i)(21).toDouble
          }
          if (Lactate_base.isNaN && !(rows(i)(22).toDouble.isNaN)) {
            Lactate_base = rows(i)(22).toDouble
          }
          if (Potassium_base.isNaN && !(rows(i)(25).toDouble.isNaN)) {
            Potassium_base = rows(i)(25).toDouble
          }
        }

        var features: Array[PhysiologicalData] = rows.map(y => PhysiologicalData(
          y(0).toDouble,
          y(1).toDouble,
          y(2).toDouble,
          y(3).toDouble,
          y(4).toDouble,
          y(5).toDouble,
          y(6).toDouble,
          y(7).toDouble,
          y(8).toDouble,
          y(9).toDouble,
          y(10).toDouble,
          y(11).toDouble,
          y(12).toDouble,
          y(13).toDouble,
          y(14).toDouble,
          y(15).toDouble,
          y(16).toDouble,
          y(17).toDouble,
          y(18).toDouble,
          y(19).toDouble,
          y(20).toDouble,
          y(21).toDouble,
          y(22).toDouble,
          y(23).toDouble,
          y(24).toDouble,
          y(25).toDouble,
          y(26).toDouble,
          y(27).toDouble,
          y(28).toDouble,
          y(29).toDouble,
          y(30).toDouble,
          y(31).toDouble,
          y(32).toDouble,
          y(33).toDouble,
          y(34).toDouble,
          y(35).toDouble,
          y(36).toDouble,
          y(37).toDouble,
          y(38).toDouble,
          y(39).toDouble,

          if (y(2).toDouble >= 38.0) 1.0 else 0.0,
          if (y(4).toDouble <= 70.0) 1.0 else 0.0,

          HR_base,
          (y(0).toDouble - HR_base) / HR_base,
          O2Sat_base,
          (y(1).toDouble - O2Sat_base) / O2Sat_base,
          Glucose_base,
          (y(21).toDouble - Glucose_base) / Glucose_base,
          Lactate_base,
          (y(22).toDouble - Lactate_base) / Lactate_base,
          Potassium_base,
          (y(25).toDouble - Potassium_base) / Potassium_base,

          y(40).toDouble,
          filename.substring(filename.length() - 10, filename.length() - 4).toDouble))

          .filter(x => !(x.HR.isNaN && x.O2Sat.isNaN && x.Temp.isNaN && x.SBP.isNaN && x.MAP.isNaN && x.DBP.isNaN && x.Resp.isNaN && x.EtCO2.isNaN && x.BaseExcess.isNaN && x.HCO3.isNaN && x.FiO2.isNaN && x.pH.isNaN && x.PaCO2.isNaN && x.SaO2.isNaN && x.AST.isNaN && x.BUN.isNaN && x.Alkalinephos.isNaN && x.Calcium.isNaN && x.Chloride.isNaN && x.Creatinine.isNaN && x.Bilirubin_direct.isNaN && x.Glucose.isNaN && x.Lactate.isNaN && x.Magnesium.isNaN && x.Phosphate.isNaN && x.Potassium.isNaN && x.Bilirubin_total.isNaN && x.TroponinI.isNaN && x.Hct.isNaN && x.Hgb.isNaN && x.PTT.isNaN && x.WBC.isNaN && x.Fibrinogen.isNaN && x.Platelets.isNaN))

        features
      }

    }.flatMap(list => list).toDF.coalesce(1).write.format("csv").mode("overwrite").save("output")
  }

}
