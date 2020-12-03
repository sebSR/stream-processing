package MirsaGriesPackage
import scala.collection.mutable.{Map => mutableMap}


class MirsaGries(list: List[String], k: Int){
  private var counters = MirsaGriesAlgorithm

  private def MirsaGriesAlgorithm: mutableMap[String, Int] = {
    var counters: mutableMap[String, Int] = mutableMap()
    for (x <- list){
      if(checkElement(x,counters)){
        val winner = find(x,counters)
        counters(winner) += 1
      }
      else if(counters.size < k-1){
        counters += (x -> 1)
      }
      else{
        val result = counters.keys
        for(y <- result){
          counters(y)-=1
          if (counters(y) == 0){
            counters -= y
          }
        }
      }
    }
    counters
  }


  def checkElement(x: String, counters: mutableMap[String,Int]): Boolean = {
    val result = counters.keys
    for (countersElement <- result) if(x == countersElement) return true
    return false
  }

  def find(x: String, counters: mutableMap[String,Int]): String = {
    val result = counters.keys
    for (countersElement <- result) if(x == countersElement) countersElement
    x
  }

  def getCounters(): mutableMap[String,Int] = {
    counters
  }

}
