# Práctica 4
# Descripción de la funcionalidad del código correspondiente a la Sesión 6

```sh
# En la línea 19 se define una función con el nombre listEvens, la cual recibirá una lista de enteros. 
# Recorrerá cada elemento, mediante ciclo for, para verificar si el valor es par o impar. 
# Utiliza el operador % para obtener residuo de dividir cada elemento entre 2
# Imprime mensaje del resultado y al terminar el ciclo devuelve el String Done.
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