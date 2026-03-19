# Práctica 2  
## Fundamentos de Scala: Funciones, Strings y Tipos de Datos

En esta práctica se desarrollan conceptos básicos del lenguaje Scala, enfocados en la creación de funciones matemáticas, evaluación de condiciones lógicas, manipulación de cadenas de texto y uso de estructuras de datos como tuplas.

Se implementan funciones para resolver problemas específicos, como el cálculo del radio de un círculo a partir de su área y la verificación de si un número es par. Además, se trabaja con interpolación de strings y extracción de subcadenas.

Finalmente, se analiza la diferencia entre variables inmutables (`val`) y mutables (`var`), lo cual es un concepto fundamental en la programación con Scala.

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