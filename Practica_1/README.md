# Práctica 1 - Regresión Lineal con Apache Spark

## Descripción

Esta práctica consiste en la implementación de un modelo de Regresión Lineal utilizando Apache Spark MLlib.

El objetivo principal es analizar datos de clientes de comercio electrónico y predecir el gasto anual de los clientes (`Yearly Amount Spent`) utilizando distintas variables relacionadas con su comportamiento digital.

---

## Tecnologías utilizadas

- Apache Spark
- Scala
- Spark MLlib
- Git y GitHub
- Visual Studio Code

---

## Dataset utilizado

Archivo:

```text
Clean-Ecommerce.csv


## Informacion de dataset

Avg Session Length
Time on App
Time on Website
Length of Membership
Yearly Amount Spent

## Objetivo del modelo

Predecir el gasto anual de los clientes utilizando técnicas de Machine Learning y Regresión Lineal.

## Variables utilizadas

Variable objetivo (label)
Yearly Amount Spent
Variables predictoras (features)
Avg Session Length
Time on App
Time on Website
Length of Membership

## Proceso realizado

Lectura del archivo CSV con Spark
Exploración del esquema de datos
Preparación de datos para Machine Learning
Conversión de variables a vector con VectorAssembler
Entrenamiento del modelo de Regresión Lineal
Evaluación del modelo utilizando métricas:
RMSE
MSE
R²

## Resultados

El modelo fue entrenado correctamente utilizando Spark MLlib y permitió obtener:

Coeficientes de regresión
Intercepto
Residuales
Métricas de desempeño del modelo