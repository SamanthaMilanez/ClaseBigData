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
