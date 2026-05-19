# 📊 Práctica 2: Regresión Logística con Apache Spark

## 📌 Descripción general

En esta práctica se desarrolló un modelo de clasificación utilizando **Regresión Logística en Apache Spark MLlib**.  
El objetivo es predecir si un usuario hace clic en un anuncio publicitario a partir de variables de comportamiento digital.

Se utilizó el dataset `advertising.csv`, el cual contiene información sobre el comportamiento de usuarios en un sitio web.

---

## 🧠 Objetivo de la práctica

Construir un modelo de Machine Learning capaz de clasificar si un usuario hará clic en un anuncio (1 = sí, 0 = no), utilizando variables como:

- Tiempo en el sitio web
- Edad
- Ingreso del área
- Uso de internet
- Género
- Hora del clic

---

## ⚙️ Fases del desarrollo

### 1. 🔧 Configuración del entorno

Se inicializa una sesión de Spark y se configuran librerías necesarias para Machine Learning.

Se desactiva el logging para evitar mensajes innecesarios durante la ejecución.

---

### 2. 📥 Carga de datos

Se carga el archivo `advertising.csv` utilizando Spark DataFrame API.

- Se habilita inferencia de tipos (`inferSchema`)
- Se leen encabezados del archivo (`header=true`)

📌 Resultado: Se obtiene un DataFrame estructurado listo para análisis.

---

### 3. 🔍 Exploración de datos

Se imprimen:

- El esquema del dataset (`printSchema`)
- Un registro de ejemplo

Esto permite entender la estructura de las variables y su tipo de datos antes del modelado.

---

### 4. 🧹 Preparación de datos

En esta fase se transforma el dataset para Machine Learning:

- La columna `Clicked on Ad` se convierte en la etiqueta (`label`)
- Se seleccionan variables predictoras:
  - Daily Time Spent on Site  
  - Age  
  - Area Income  
  - Daily Internet Usage  
  - Male  
- Se extrae la hora del clic desde `Timestamp`

📌 Esto permite convertir datos “crudos” en variables útiles para el modelo.

---

### 5. 🧱 Construcción de features

Se utiliza `VectorAssembler` para combinar todas las variables predictoras en un solo vector llamado:

👉 `features`

Esto es necesario porque Spark ML trabaja con vectores de entrada.

---

### 6. ✂️ División de datos

El dataset se divide en:

- 70% entrenamiento
- 30% prueba

📌 Esto permite evaluar el modelo con datos que nunca ha visto.

---

### 7. 🤖 Entrenamiento del modelo

Se crea un pipeline que contiene:

- VectorAssembler
- Logistic Regression

El modelo se entrena usando el conjunto de entrenamiento.

---

### 8. 📊 Predicción

El modelo entrenado se aplica al conjunto de prueba para generar predicciones.

Se obtiene un DataFrame con:

- predicción
- probabilidad
- etiqueta real

---

### 9. 📈 Evaluación del modelo

Se utiliza `MulticlassMetrics` para evaluar el rendimiento:

- Matriz de confusión
- Accuracy (precisión del modelo)

📌 Esto permite medir qué tan bien clasifica el modelo.

---

## 📌 Resultados

- Modelo basado en regresión logística implementado correctamente
- Se obtiene matriz de confusión para analizar errores
- Se calcula precisión general del modelo

---

## 💡 Conclusión

Esta práctica permite comprender el flujo completo de Machine Learning en Spark:

**Carga → Preparación → Features → Modelo → Evaluación**

Es una base fundamental para modelos más avanzados en Big Data y Data Science.

---

## 👨‍💻 Tecnologías usadas

- Apache Spark
- Spark MLlib
- Scala
- Logistic Regression

---