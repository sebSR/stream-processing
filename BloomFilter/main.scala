import java.io.FileNotFoundException
import BloomFilterPackage.BloomFilter
import scala.io.Source


object MainBloomFilter{

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

  def main(args: Array[String]){

    if (args.length != 0){
        val sizeOfHashTable = args(0)
        val listOfSeeds = args(1).split(",").map(_.toInt).toList
        val streamFileName = args(2)
        val numberOfElements = Source.fromFile(streamFileName).getLines.size

        val bloom = new BloomFilter(sizeOfHashTable.toInt, numberOfElements, listOfSeeds)

        addElements(bloom, streamFileName)
        falsePositiveProbabilityPrint(bloom)
        elementsCheckPrint(bloom, args)
    }
  }

}
