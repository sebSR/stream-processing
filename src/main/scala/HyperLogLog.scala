package HyperLogLogPackage
import scala.math
import scala.util.hashing.MurmurHash3
import scala.collection.mutable.ArrayBuffer


class HyperLogLogBase(numberOfFirstBits: Int){

  val registersNumber: Int = math.pow(2, numberOfFirstBits).toInt
  val registers: ArrayBuffer[Int] = ArrayBuffer.fill(registersNumber)(0)

  val approximatedAlpha: Double = registersNumber match {
    case 16 => 0.673
    case 32 => 0.697
    case 64 => 0.709
    case _  => 0.7213/(1 + 1.079/registersNumber)
  }

  def indicatorFunction(): Double = {
    math.pow(registers.foldLeft(0.toDouble)((sum, item) => sum + math.pow(2, -item)), -1)
  }

  def add(value: String): Unit = {
    val hashValue = MurmurHash3.stringHash(value).toBinaryString
    val registerAdress = Integer.parseInt(hashValue.takeRight(numberOfFirstBits), 2)
    val leftMostOneBitPosition = hashValue.dropRight(numberOfFirstBits).reverse.takeWhile(_ == '0').length + 1
    registers(registerAdress) = scala.math.max(registers(registerAdress), leftMostOneBitPosition)
  }

  def cardinality(): Int = {
    (approximatedAlpha * math.pow(registersNumber, 2) * indicatorFunction()).toInt
  }

}


class HyperLogLog(numberOfFirstBits: Int) extends HyperLogLogBase(numberOfFirstBits){

  override def cardinality(): Int = {
    val cardinality = (approximatedAlpha * math.pow(registersNumber, 2) * indicatorFunction()).toInt
    correctCardinality(cardinality)
  }

  def correctCardinality(estimate: Double): Int = {
    val correctCardinality = estimate match {

      case a if estimate < (5/2 * registersNumber) => {
        val numberOfRegistersEqualToZero = registers.count(_ == 0)
        if(numberOfRegistersEqualToZero > 0){
          registersNumber * math.log(registersNumber.toDouble/numberOfRegistersEqualToZero)
        }
        else{
          estimate
        }
      }

      case b if estimate <= (1/30 * math.pow(2, 32)) => estimate

      case c if estimate > (1/30 * math.pow(2, 32)) => {
        -1 * math.pow(2, 32) * math.log(1 - estimate/math.pow(2, 32))
      }

      case _ => estimate
    }
    correctCardinality.toInt
  }

}
