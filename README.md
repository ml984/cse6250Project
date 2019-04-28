**`Project Title : Early Prediction of Sepsis
                from Clinical Data`**
                
                
**Overview**


This repository contains code for the final project of CSE6250. In this project, we design and implement a machine learning algorithm that can automatically identify a patient's risk of sepsis and make prediction of sepsis at different time intervals, offering a way to catch sepsis earlier than is possible with standard processes.
Please refer to the paper for further explanation on the data source, methodology, model architecture and results.

The project consists of 2 stages:

Stage 1. Data ingestion and Feature Engineering using Apache Spark

Stage 2. Machine Learning using Python 
                
     
                
**Setup**

This project uses Scala Build Tool(SBT) and python virtual environment. To setup this environment, run:


    1. Install SBT
    2. Run "sbt compile run" in code/scala folder


      
**The directory structure of this repository is as followings:**


        code/scala               : relevant spark code 
        code/scala/data/*.psv    : sub-dataset sample from 20th PhysioNet/Computing Challenge in Cardiology)
                                   (https://physionet.org/pnw/challenge-2019-request-access to download the complete training database, consisting of two parts: training set A (20,336 subjects) and B (20,000 subjects).
        code/scala/output/*.csv  : feature construction output data:
                
                
**Stage 1. Data ingestion and Feature Engineering using Apache Spark**


    Hardware Environment: deployed using Apache Spark 2.3.0. on Gatech GPU Tesla P40 cluster.
    
    Software requirements:
        
        * SBT (sbt will download Scala for you)
    
    Execution:
    
        run "sbt compile run" in code/scala folder
    
    

**Stage 2. Machine Learning using Python** 

    Hardware Environment: on Local Machine
    
    Software requirements:
    
        * Python
        
    Execution:





Results:

