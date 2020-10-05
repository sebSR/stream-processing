package MirsaGriesPackage

trait Item


case class Car(carType: String, year: Int) extends Item {
    override def toString = carType + year
}


class MirsaGries(list: List[Item], k: Int){
  private var counters = MirsaGries

  private def MirsaGries: scala.collection.mutable.Map[Item, Int] = {
    var counters: scala.collection.mutable.Map[Item, Int] = scala.collection.mutable.Map()
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

  def checkElement(x: Item, counters: scala.collection.mutable.Map[Item,Int]): Boolean = {
    val result = counters.keys
    for (countersElement <- result){
      if(x == countersElement){ return true }
    }
    return false
  }

  def find(x: Item, counters: scala.collection.mutable.Map[Item,Int]): Item = {
    val result = counters.keys
    for (countersElement <- result){
      if(x == countersElement){ return countersElement }
    }
    return x
  }

  def get(): Unit = {
    println(counters)
  }

}
