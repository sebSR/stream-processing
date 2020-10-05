import MirsaGriesPackage.MirsaGries
import MirsaGriesPackage.Item
import MirsaGriesPackage.Car
import scala.collection.immutable.Stream.cons
import scala.io.Source


object MainMirsaGries{

   def StremOfCars(filename: String,k: Int): Stream[Item] = {
     var streamOfItems: Stream[Item] = Stream.empty[Item]
     try{
          for (lines <- Source.fromFile(filename).getLines){
            val word = lines.split(" ")
            val newItem = new Car(word(0),word(1).toInt)
            streamOfItems = (streamOfItems :+ newItem)}
     }catch{case ex: ArrayIndexOutOfBoundsException=>{println("There is empty line in cars.txt")}}
     println(s"Mirsa-Gries Algorithm for $filename and parameter k equal to $k")
     streamOfItems
   }


   def main(args: Array[String]) {
     if (args.length == 2){
         var filename = ""
         filename = args(0)
         val k = args(1).toInt
         if (filename == "cars.txt"){
           val listOfItems = StremOfCars(filename,k).toList
           val frequency = new MirsaGries(listOfItems,k)
           frequency.get()
         }
         else{
           println("Wrong name of file")
         }
       }
       else{println("To few parameters")}
   }
}
