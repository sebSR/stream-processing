import BloomFilterPackage.BloomFilter
import MirsaGriesPackage.MirsaGries
import java.io.FileNotFoundException
import scala.collection.mutable.ArrayBuffer
import scala.io.Source


object MainBloomFilter{

  def main(args: Array[String]){
    val streamFile = "./src/main/resources/stream.txt"
    if (args(0) == "BloomFilter"){
      runBloomFilter(args, streamFile)
    }
    else if(args(0) == "MirsaGries"){
      runMirsaGries(args, streamFile)
    }
    else{
      println("Wrong command")
    }
  }

  def runBloomFilter(args: Array[String], streamFile: String): Unit = {
    val sizeOfHashTable = args(1)
    val listOfSeeds = args(2).split(",").map(_.toInt).toList
    val numberOfElements = Source.fromFile(streamFile).getLines.size
    val bloom = new BloomFilter(sizeOfHashTable.toInt, numberOfElements, listOfSeeds)
    addElements(bloom, streamFile)
    falsePositiveProbabilityPrint(bloom)
    elementsCheckPrint(bloom, args)
  }

  def runMirsaGries(args: Array[String], streamFile: String): Unit = {
    val k = args(1).toInt
    val listOfItems = MirsaGriesStream(streamFile,k).toList
    val frequency = new MirsaGries(listOfItems, k)
    println(s"Mirsa-Gries Algorithm for parameter k equal to $k")
    println(frequency.getCounters())
  }

  def addElements(bloom: BloomFilter, filename: String): Unit = {
    for (lines <- Source.fromFile(filename).getLines) bloom.add(lines)
  }

  def falsePositiveProbabilityPrint(bloom: BloomFilter): Unit = {
    val p = bloom.falsePositiveProbability()
    val cast = p-(p%0.01)
    println(f"Probability of false positive: ${cast}")
  }

  def elementsCheckPrint(bloom: BloomFilter, args: Array[String]): Unit = {
    for(indexInArgs <- 3 to args.length-1){
      val element = args(indexInArgs)
      val result = bloom.check(element)
      println(s"element: $element -> $result")
    }
  }

  def MirsaGriesStream(filename: String,k: Int): ArrayBuffer[String] = {
    var streamOfItems: ArrayBuffer[String] = ArrayBuffer.empty[String]
    for (i <- Source.fromFile(filename).getLines) streamOfItems = (streamOfItems :+ i)
    streamOfItems
   }
}
