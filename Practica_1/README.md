# 📊 Práctica 1 - Regresión Lineal con Apache Spark

## 📌 Descripción

Esta práctica consiste en la implementación de un modelo de **Regresión Lineal** utilizando Apache Spark MLlib.

El objetivo principal es analizar datos de clientes de comercio electrónico y predecir el gasto anual de los clientes (`Yearly Amount Spent`) a partir de variables relacionadas con su comportamiento digital.

---

## ⚙️ Tecnologías utilizadas

- Apache Spark  
- Scala  
- Spark MLlib  
- Git y GitHub  
- Visual Studio Code  

---

## 📂 Dataset utilizado

Archivo:
Clean-Ecommerce.csv


### 📊 Variables del dataset

- Avg Session Length  
- Time on App  
- Time on Website  
- Length of Membership  
- Yearly Amount Spent  

---

## 🎯 Objetivo del modelo

Predecir el gasto anual de los clientes utilizando técnicas de Machine Learning con **Regresión Lineal**.

---

## 🧠 Variables del modelo

### 🎯 Variable objetivo (label)
- Yearly Amount Spent  

### 📈 Variables predictoras (features)
- Avg Session Length  
- Time on App  
- Time on Website  
- Length of Membership  

---

## 🔄 Proceso realizado

1. Lectura del archivo CSV con Spark  
2. Exploración del esquema de datos  
3. Preparación de datos para Machine Learning  
4. Conversión de variables a vector con `VectorAssembler`  
5. Entrenamiento del modelo de Regresión Lineal  
6. Evaluación del modelo  

---

## 📊 Métricas utilizadas

- RMSE (Root Mean Squared Error)  
- MSE (Mean Squared Error)  
- R² (Coeficiente de determinación)  

---

## 📌 Resultados

El modelo fue entrenado correctamente utilizando Spark MLlib y permitió obtener:

- Coeficientes de regresión  
- Intercepto  
- Residuales  
- Métricas de desempeño del modelo  

---

## 🧾 Conclusión

La práctica permitió comprender el flujo completo de Machine Learning en Spark:

**Carga → Preparación → Entrenamiento → Evaluación**

Este tipo de modelos es fundamental para predecir variables continuas en problemas reales de negocio.