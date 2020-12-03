package BloomFilterPackage
import scala.util.hashing.{MurmurHash3, ByteswapHashing, Hashing}
import MurmurHash3.ArrayHashing
import scala.util.hashing.MurmurHash3
import scala.collection.mutable.MutableList


class BloomFilter(sizeOfHashTable: Int, sizeOfData:Int, listOfSeeds: List[Int]){

  private var hashTable = hashIni

  private def hashIni: MutableList[Int] = {
    MutableList.fill(sizeOfHashTable)(0)
    }

  def falsePositiveProbability(): Double = {
    val hashFunctionsNumber = listOfSeeds.length
    Math.pow((1.0-Math.exp(-hashFunctionsNumber*sizeOfData/sizeOfHashTable)), hashFunctionsNumber)
  }

  def hash(intToHash: String, variant: Int): Int = {
    val hash = MurmurHash3.stringHash(intToHash, variant).toInt%sizeOfHashTable
    if (hash >= 0) hash else hash+sizeOfHashTable
  }

  def add(newElement: String): Unit = {
    for (i <- listOfSeeds){
      val hashIndex = hash(newElement, i)
      hashTable(hashIndex) = 1
    }
  }

  def check(elementToCheck: String): Boolean = {
    for (i <- listOfSeeds){
      val hashIndex = hash(elementToCheck, i)
      if (hashTable(hashIndex) == 0){
        return false
      }
    }
    return true
  }

}
