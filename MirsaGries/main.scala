import MirsaGriesPackage.MirsaGries
import scala.collection.mutable.ArrayBuffer
import scala.io.Source


object MainMirsaGries{

  def main(args: Array[String]) {
    if (args.length == 2) runAlgorithm(args) else println("To few parameters")
  }

  def runAlgorithm(args: Array[String]): Unit = {
    val filename = args(0)
    val k = args(1).toInt
    val listOfItems = StremOfCars(filename,k).toList
    val frequency = new MirsaGries(listOfItems, k)
    println(s"Mirsa-Gries Algorithm for $filename and parameter k equal to $k")
    println(frequency.getCounters())
  }

  def StremOfCars(filename: String,k: Int): ArrayBuffer[String] = {
    var streamOfItems: ArrayBuffer[String] = ArrayBuffer.empty[String]
    for (i <- Source.fromFile(filename).getLines) streamOfItems = (streamOfItems :+ i)
    streamOfItems
   }

}
