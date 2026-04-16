# Práctica 2  
## Fundamentos de Scala: Funciones, Strings y Tipos de Datos

En la siguiente práctica se desarrollan conceptos básicos del lenguaje Scala, enfocados en la creación de funciones matemáticas, evaluación de condiciones lógicas, manipulación de cadenas de texto y uso de estructuras de datos como tuplas.

Tambien se implementaron funciones para resolver problemas específicos, como el cálculo del radio de un círculo a partir de su área y la verificación de si un número es par. Además, se trabaja con interpolación de strings y extracción de subcadenas.

Por ultimo, se analiza la diferencia entre variables inmutables (`val`) y mutables (`var`), lo cual es un concepto fundamental en la programación con Scala.

```scala
object Practica2 {

  // Funcion para calcular el radio a partir del area
  def calcularRadio(area: Double): Double = {
    math.sqrt(area / math.Pi)
  }

  // Funcion que determina si un numero es par
  def esPar(n: Int): Boolean = {
    n % 2 == 0
  }

  def main(args: Array[String]): Unit = {

    // Ejemplo calculo de radio
    val area = 78.5
    val radio = calcularRadio(area)
    println("El radio es: " + radio)

    // Ejemplo paridad
    println(esPar(4))

    // Interpolacion de strings
    val bird = "tweet"
    println(s"Estoy escribiendo un $bird")

    // Extraccion de subcadena
    val mensaje = "Hola Luke yo soy tu padre!"
    val nombre = mensaje.slice(5,9)
    println(nombre)

    // Tupla
    val datos = (2,4,5,1,2,3,3.1416,23)
    println(datos._7)

    // Ejemplo val vs var

    // val: inmutable (no cambia)
    val numero = 10
    // numero = 20  // Esto daria error

    // var: mutable (si cambia)
    var contador = 10
    contador = 20
    println(contador)

  }

}

```

# Práctica 3
## Manipulación de Estructuras de Datos en Scala

En esta práctica se utilizan colecciones. En cada renglón se explica qué se está haciendo. 
Se generan listas, se utiliza foreach para imprimir cada elemento del arreglo y se filtran resultados mediante slicing. 
También se utilizan mapas mutables, se extrae la llave única y se inserta un nuevo registro.

```scala
import scala.collection.mutable

object Practica_3 {

  //1. Gestion de colecciones inmutables

  def main(args: Array[String]): Unit = {

 println("1. Gestion de colecciones inmutables")
    // lista inicial
     println("Generacion de lista de colores:")
    val lista = List("rojo", "blanco", "negro")
    lista.foreach(println)

    // Agregar colores
    println("Insercion de nuevos colores:")
    val nuevaLista = lista ++ List("verde","amarillo","azul","naranja","perla")
    nuevaLista.foreach(println)

    // Seleccionar elementos
    println("Slicing/Filtrado de colores:")
    val seleccion = nuevaLista.slice(3,6)
    seleccion.foreach(println)


    //2. Rangos y Análisis de Conjuntos

    // generar numeros del 1 al 1000 de 5 en 5

    println("2. Rangos y Análisis de Conjuntos")
    println("Numeros del 1 al 1000 de 5 en 5:")
    
    (1 to 1000 by 5).foreach(println)

    // lista con valores repetidos
    val listaNumeros = List(1,3,3,4,6,7,3,7)

    // convertir a Set para obtener valores únicos
    val ElementoUnico = listaNumeros.toSet

    println("Elementos únicos:")
    println(ElementoUnico)

    //3. Mapas Mutables y Operaciones de Diccionario

     println("3. Mapas Mutables y Operaciones de Diccionario")

     val nombres: mutable.Map[String, Int] = mutable.Map(
     "Jose" -> 20,
     "Luis" -> 24,
     "Ana" -> 23,
     "Susana" -> 27
    )
    println("Mapa inicial:")
    println(nombres) 

    //Extraccion de las llaves del mapa

    println("6a. Extraccion de las llaves del mapa:")

    for (nombre <- nombres.keys) {
    println(nombre)
    }
    
    //Insercion de nuevo registro
    println("6b. Insercion de registro")


   nombres += ("Miguel" -> 23)

   println("Persistencia en la estructura:")
   println(nombres)
 

  }

}

```

# Práctica 4
## Descripción de la funcionalidad del código correspondiente a la Sesión 6


En la línea 19 se define una función con el nombre listEvens, la cual recibirá una lista de enteros. 
Recorrerá cada elemento, mediante ciclo for, para verificar si el valor es par o impar. 
Utiliza el operador % para obtener residuo de dividir cada elemento entre 2
Imprime mensaje del resultado y al terminar el ciclo devuelve el String Done.

```sh
scala> def listEvens(list:List[Int]): String ={
     |     for(n <- list){(s"$n is even")
     |         if(n%2==0){(s"$n is even")
     |             println(s"$n is even")
     |         }else{intln(s"$n is odd")
     |             println(s"$n is odd")
     |         }n "Done"
     |     }eturn "Done"
     |     return "Done"
     | }
def listEvens(list: List[Int]): String

#En las líneas 20 y 21, se declaran variables de tipo lista de números enteros.
#La lista l con 8 elementos. La lista l2 con 6 elementos 
scala> val l = List(1,2,3,4,5,6,7,8)
val l: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)

scala> val l2 = List(4,3,22,55,7,8)
val l2: List[Int] = List(4, 3, 22, 55, 7, 8)

#En las líneas 22 y 23, se ejecuta la función listEvens y se le envía la lista l y l2
#Se imprime el valor de cada elemento de la lista y si es impar o par.
#Al terminar de recorrer cada lista retorna el Strign o texto Done.
scala> listEvens(l)
1 is odd
2 is even
3 is odd
4 is even
5 is odd
6 is even
7 is odd
8 is even
val res1: String = Done

scala> listEvens(l2)
4 is even
3 is odd  
22 is even
55 is odd 
7 is odd
8 is even
val res2: String = Done

#En la línea 27, se declara una función con el nombre afortunado, la cual recibirá una lista de enteros. 
#Al recorrer con el ciclo for cada elemento de la lista va sumando el valor de cada elemento. 
#Mediante condición if valida si algún valor es 7 y si lo es, en vez de sumar 7 sumará 14.
#Acumula la suma en la variable res, y al terminar de recorrer la lista devuelve valor de res que es de tipo entero.
scala> def afortunado(list:List[Int]): Int={
     |     var res=0
     |     for(n <- list){
     |         if(n==7){
     |             res = res + 14
     |         }else{
     |             res = res + n
     |         }
     |     }
     |     return res
     | }
def afortunado(list: List[Int]): Int

#En la línea 39, se declara una lista de enteros, con tres elementos. De nombre af.
scala> val af= List(1,7,7)
val af: List[Int] = List(1, 7, 7)
#A continuación, se imprime el resultado de ejecutar la función afortunado, se le envía la lista af. Y de resultado imprime 29.
scala> println(afortunado(af))
29

#En la línea 42, se define una función de nombre balance, la cual recibe una lista de enteros y devuelve un valor boleano. 
#El objetivo es determinar si la suma de los elementos de un lado es igual a la suma de los elementos de la otra parte. Si se cumple devuelve true.
#En la variable primera va sumando los elementos recorridos.
#En la variable segunda se le asigna la suma de todos los elementos de la lista. 
#Conforme va avanzando el ciclo a esa suma (de la variable segunda) le va restando el valor de los elementos recorridos,
#si se cumple la condición de que el valor en la variable primera sea igual al valor la variable segunda , entonces devuelve true y termina la función.
#Si no se cumple la condición, se termina de recorrer el ciclo, devuelve false y termina la función.
scala> def balance(list:List[Int]): Boolean={
     |     var primera = 0
     |     var segunda = 0
     | 
     |     segunda = list.sum
     | 
     |     for(i <- Range(0,list.length)){
     |         primera = primera + list(i)
     |         segunda = segunda - list(i)
     | 
     |         if(primera == segunda){
     |             return true
     |         }
     |     }
     |     return false
     | }
def balance(list: List[Int]): Boolean

#A continuación se declaran tres listas de enteros: bl, bl2 y bl3
scala> val bl = List(3,2,1)
val bl: List[Int] = List(3, 2, 1)

scala> val bl2 = List(2,3,3,2)
val bl2: List[Int] = List(2, 3, 3, 2)

scala> val bl3 = List(10,30,90)
val bl3: List[Int] = List(10, 30, 90)

#Se ejecuta la función balance, con cada una de las listas anteriores. 
#Para las dos primeras listas se cumple y para la tercer lista no se cumple.
scala> balance(bl)
val res4: Boolean = true

scala> val bl2 = List(2,3,3,2)
val bl2: List[Int] = List(2, 3, 3, 2)

scala> balance(bl2)
val res5: Boolean = true

scala> balance(bl3)
val res6: Boolean = false

#En la línea 67 se declara la función palindromo, la cual recibe un string y devuelve un valor boleano. 
#Esta función evalua si el texto que recibe, se lee igual de izquierda a derecha y de derecha a izquierda, 
#mediante el uso del método reverse el cual invierte la cadena(texto). Si se cumple devuelve true.
scala> def palindromo(palabra:String):Boolean ={
     |     return (palabra == palabra.reverse)
     | }
def palindromo(palabra: String): Boolean

#En las siguientes tres líneas de código, se declaran tres variables de tipo string.
scala> val palabra = "OSO"
val palabra: String = OSO

scala> val palabra2 = "ANNA"
val palabra2: String = ANNA

scala> val palabra3 = "JUAN"
val palabra3: String = JUAN

#A continuación se ejecuta la función palindromo con cada una de las variables anteriores. 
#Para la primera palabra (OSO) se cumple que se lee igual en ambias direcciones.
#Para la segunda palabra (ANNA) también se cumple. Y en ambos casos la función devuelve true. 
Para la tercer palabra (JUAN) no se cumple por lo que devuelve false.
scala> println(palindromo(palabra))
true

scala> println(palindromo(palabra2))
true

scala> println(palindromo(palabra3))
false
```
# Práctica Evaluatoria Unidad 1

A continuacion se explican los reactivos de la practica
```sh
#1. Comienza una simple sesión Spark.
#2. Cargue el archivo Netflix Stock CSV en un Dataframe llamado df, haga que Spark infiera los tipos de datos.
scala> import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession

scala> val spark = SparkSession.builder().getOrCreate()
val spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.classic.SparkSession@6980d3b3

scala> val df = spark.read.option("header","true").option("inferSchema","true")csv("C:/Users/Samantha/Documents/projects/ClaseBigData/Practica_evaluatoria/Netflix_2011_2016.csv")
val df: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 5 more fields]
#3.3. ¿Cuáles son los nombres de las columnas? Date,Open,High,Low,Close,Volume,Adj,Close
scala> df.show()
+----------+-----------------+------------------+----------+-----------------+---------+------------------+
|      Date|             Open|              High|       Low|            Close|   Volume|         Adj Close|
+----------+-----------------+------------------+----------+-----------------+---------+------------------+
|2011-10-24|       119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|
|2011-10-25|        74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|
|2011-10-26|            78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|
|2011-10-27|        82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|
|2011-10-28|        80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|
|2011-10-31|83.63999799999999|         84.090002| 81.450002|        82.080003| 39653600|         11.725715|
|2011-11-01|        80.109998|         80.999998|     78.74|        80.089997| 33016200|         11.441428|
|2011-11-02|        80.709998|         84.400002| 80.109998|        83.389999| 41384000|         11.912857|
|2011-11-03|        84.130003|         92.600003| 81.800003|        92.290003| 94685500|13.184285999999998|
|2011-11-04|91.46999699999999| 92.89000300000001| 87.749999|        90.019998| 84483700|             12.86|
|2011-11-07|             91.0|         93.839998| 89.979997|        90.830003| 47485200|         12.975715|
|2011-11-08|91.22999899999999|         92.600003| 89.650002|        90.470001| 31906000|         12.924286|
|2011-11-09|        89.000001|         90.440001| 87.999998|        88.049999| 28756000|         12.578571|
|2011-11-10|        89.290001| 90.29999699999999| 84.839999|85.11999899999999| 39614400|             12.16|
|2011-11-11|        85.899997|         87.949997|      83.7|        87.749999| 38140200|         12.535714|
|2011-11-14|        87.989998|              88.1|     85.45|        85.719999| 21811300|         12.245714|
|2011-11-15|            85.15|         87.050003| 84.499998|        86.279999| 21372400|         12.325714|
|2011-11-16|        86.460003|         86.460003| 80.890002|        81.180002| 34560400|11.597142999999999|
|2011-11-17|            80.77|         80.999998| 75.789999|        76.460001| 52823400|         10.922857|
|2011-11-18|             76.7|         78.999999| 76.039998|        78.059998| 34729100|         11.151428|
+----------+-----------------+------------------+----------+-----------------+---------+------------------+
only showing top 20 rows
#4. ¿Cómo es el esquema?
scala> df.printSchema()
root
 |-- Date: date (nullable = true)
 |-- Open: double (nullable = true)
 |-- High: double (nullable = true)
 |-- Low: double (nullable = true)
 |-- Close: double (nullable = true)
 |-- Volume: integer (nullable = true)
 |-- Adj Close: double (nullable = true)
 #5. Imprime las primeras 5 renglones.
 scala> df.show(5)
+----------+----------+------------------+----------+-----------------+---------+------------------+
|      Date|      Open|              High|       Low|            Close|   Volume|         Adj Close|
+----------+----------+------------------+----------+-----------------+---------+------------------+
|2011-10-24|119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|
|2011-10-25| 74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|
|2011-10-26|     78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|
|2011-10-27| 82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|
|2011-10-28| 80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|
+----------+----------+------------------+----------+-----------------+---------+------------------+
only showing top 5 rows
#6. Usa el método describe() para aprender sobre el DataFrame.
scala> df.describe().show()
26/04/15 19:56:38 WARN SparkStringUtils: Truncated the string representation of a plan since it was too large. This behavior can be adjusted by setting 'spark.sql.debug.maxToStringFields'.
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|summary|              Open|              High|               Low|             Close|              Volume|         Adj Close|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|  count|              1259|              1259|              1259|              1259|                1259|              1259|
|   mean|230.39351086656092|233.97320872915006|226.80127876251044|  230.522453845909|2.5634836060365368E7|55.610540036536875|
| stddev|164.37456353264244| 165.9705082667129| 162.6506358235739|164.40918905512854| 2.306312683388607E7|35.186669331525486|
|    min|         53.990001|         55.480001|             52.81|              53.8|             3531300|          7.685714|
|    max|        708.900017|        716.159996|        697.569984|        707.610001|           315541800|        130.929993|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
#7. Crea un nuevo Dataframe con una columna nueva llamada “HV Ratio” que es la relación que existe entre el precio de la columna “High” frente a la columna “Volumen” de acciones negociadas por un día. Hint - Es una operación
scala> val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
val df2: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 6 more fields]

scala> df2.show()
+----------+-----------------+------------------+----------+-----------------+---------+------------------+--------------------+
|      Date|             Open|              High|       Low|            Close|   Volume|         Adj Close|            HV Ratio|
+----------+-----------------+------------------+----------+-----------------+---------+------------------+--------------------+
|2011-10-24|       119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|9.985040951285156E-7|
|2011-10-25|        74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|2.515989989281927E-7|
|2011-10-26|            78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|5.474206014903126E-7|
|2011-10-27|        82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|1.161960907430818...|
|2011-10-28|        80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|1.465476686700271...|
|2011-10-31|83.63999799999999|         84.090002| 81.450002|        82.080003| 39653600|         11.725715|2.120614572195210...|
|2011-11-01|        80.109998|         80.999998|     78.74|        80.089997| 33016200|         11.441428|2.453341026526372E-6|
|2011-11-02|        80.709998|         84.400002| 80.109998|        83.389999| 41384000|         11.912857|2.039435578967717E-6|
|2011-11-03|        84.130003|         92.600003| 81.800003|        92.290003| 94685500|13.184285999999998| 9.77974483949496E-7|
|2011-11-04|91.46999699999999| 92.89000300000001| 87.749999|        90.019998| 84483700|             12.86|1.099502069629999...|
|2011-11-07|             91.0|         93.839998| 89.979997|        90.830003| 47485200|         12.975715|1.976194645910725...|
|2011-11-08|91.22999899999999|         92.600003| 89.650002|        90.470001| 31906000|         12.924286|2.902275528113834...|
|2011-11-09|        89.000001|         90.440001| 87.999998|        88.049999| 28756000|         12.578571|3.145082800111281E-6|
|2011-11-10|        89.290001| 90.29999699999999| 84.839999|85.11999899999999| 39614400|             12.16|2.279474054889131E-6|
|2011-11-11|        85.899997|         87.949997|      83.7|        87.749999| 38140200|         12.535714|2.305965805108520...|
|2011-11-14|        87.989998|              88.1|     85.45|        85.719999| 21811300|         12.245714|4.039190694731629...|
|2011-11-15|            85.15|         87.050003| 84.499998|        86.279999| 21372400|         12.325714|4.073010190713256...|
|2011-11-16|        86.460003|         86.460003| 80.890002|        81.180002| 34560400|11.597142999999999|2.501707242971725E-6|
|2011-11-17|            80.77|         80.999998| 75.789999|        76.460001| 52823400|         10.922857|1.533411291208063...|
|2011-11-18|             76.7|         78.999999| 76.039998|        78.059998| 34729100|         11.151428|2.274749388841058...|
+----------+-----------------+------------------+----------+-----------------+---------+------------------+--------------------+
only showing top 20 rows
#8. ¿Qué día tuvo el pico más alto en la columna “Open”?
'''