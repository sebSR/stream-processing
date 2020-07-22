import java.io.FileNotFoundException
import BloomFilter.BloomFilter
import scala.io.Source


object Main{

  def addElements(bloom: BloomFilter, filename: String): Unit = {
    for (lines <- Source.fromFile(filename).getLines){
      bloom.add(lines)
    }
  }

  def falsePositiveProbabilityPrint(bloom: BloomFilter): Unit = {
    val p = bloom.falsePositiveProbability()
    println(s"Probability of false positive: $p")
  }

  def main(args: Array[String]){

    def elementsCheckPrint(bloom: BloomFilter): Unit = {
      for(indexInArgs <- 3 to args.length-1){
        val element = args(indexInArgs)
        val result = bloom.check(element)
        println(s"element $element -> $result")
      }
    }

    if (args.length != 0){
        val sizeOfHashTable = args(0)
        val numberOfHashFunction = args(1)
        val filename = args(2)
        val numberOfElements = Source.fromFile(filename).getLines.size

        val bloom = new BloomFilter(sizeOfHashTable.toInt, numberOfElements, numberOfHashFunction.toInt)

        addElements(bloom, filename)
        falsePositiveProbabilityPrint(bloom)
        elementsCheckPrint(bloom)
    }
  }
}
