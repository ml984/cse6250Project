package model

case class PhysiologicalData(HR: Double, O2Sat: Double, Temp: Double, SBP: Double, MAP: Double, DBP: Double, Resp: Double,
  EtCO2: Double, BaseExcess: Double, HCO3: Double, FiO2: Double, pH: Double, PaCO2: Double, SaO2: Double,
  AST: Double, BUN: Double, Alkalinephos: Double, Calcium: Double, Chloride: Double, Creatinine: Double,
  Bilirubin_direct: Double, Glucose: Double, Lactate: Double, Magnesium: Double, Phosphate: Double,
  Potassium: Double, Bilirubin_total: Double, TroponinI: Double, Hct: Double, Hgb: Double, PTT: Double,
  WBC: Double, Fibrinogen: Double, Platelets: Double, Age: Double, Gender: Double, Unit1: Double,
  Unit2: Double, HospAdmTime: Double, ICULOS: Double, id: Double,
  high_temp: Double, low_map: Double,

  HR_base: Double, HR_icr: Double,
  O2Sat_base: Double, O2Sat_icr: Double,
  Glucose_base: Double, gl_icr: Double,
  Lactate_base: Double, lac_base: Double,
  Potassium_base: Double, pot_icr: Double,

  SepsisLabel: Double)

