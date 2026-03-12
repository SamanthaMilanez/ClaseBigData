object Practica2 {

    def calcularRadio(area: Double): Double = {
        math.sqrt(area / math.Pi)
    }

    def esPar(n: Int): Boolean = {
        n % 2 == 0
    }

    def main(args: Array[String]): Unit = {

        val area = 78.5
        val radio = calcularRadio(area)
        println("Radio: " + radio)

        println(esPar(4))

        val bird = "tweet"
        println(s"Estoy escribiendo un $bird")

        val mensaje = "Hola Luke yo soy tu padre!"
        val nombre = mensaje.slice(5, 9)
        println(nombre)

        val datos = (2, 4, 5, 1, 2, 3, 3.1416, 23)
        println(datos._7)

    }

}