import numpy as np
import pandas as pd

#Specify prediction file path
prediction_csv_file = "C:\\Temp\\data_with_predict.csv"
#Read prediction file into dataframe
df = pd.read_csv(prediction_csv_file)
#Only keep "SepsisLabel","ID","predict","p1" columns
df_truncated = df[["SepsisLabel","ID","predict","p1"]].copy()
#Rename columns to 'SepsisLabel','ID','PredictedLabel','PredictedProbability'
df_truncated.columns = ['SepsisLabel','ID','PredictedLabel','PredictedProbability']
#get unique patient ID
patientIDGroup = df_truncated['ID'].unique().tolist()
#for each patient id, write a csv file for utility calculation
for patient in patientIDGroup:
    df_truncated.loc[df_truncated.ID==patient].to_csv("C:\\Temp\\Spilt_Evaluation_Data\\"+str(patient) + ".csv",index=False)