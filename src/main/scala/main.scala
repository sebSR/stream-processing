import BloomFilterPackage.BloomFilter
import MirsaGriesPackage.MirsaGries
import HyperLogLogPackage.HyperLogLog
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
    else if(args(0) == "HyperLogLog"){
      runHyperLogLog(args, streamFile)
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
    for (lines <- Source.fromFile(streamFile).getLines) bloom.add(lines)
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

  def runHyperLogLog(args: Array[String], streamFile: String): Unit = {
    val b = args(1).toInt
    val hll = new HyperLogLog(b)
    for (lines <- Source.fromFile(streamFile).getLines) hll.add(lines)
    println(s"Number of different items: ${hll.cardinality()} \n")
  }

  def falsePositiveProbabilityPrint(bloom: BloomFilter): Unit = {
    val p = bloom.falsePositiveProbability()
    val cast = p - (p % 0.01)
    println(f"Probability of false positive: ${cast}")
  }

  def elementsCheckPrint(bloom: BloomFilter, args: Array[String]): Unit = {
    for(indexInArgs <- 3 to args.length - 1){
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
