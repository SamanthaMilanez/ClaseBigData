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

        val numero = 10
        // numero = 20  // Esto daria error porque val es inmutable

        var contador = 10
        contador = 20
        println(contador)

    }

}