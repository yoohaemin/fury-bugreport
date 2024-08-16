package test2

import org.apache.fury.Fury
import org.apache.fury.config.CompatibleMode

import scala.collection.immutable.ArraySeq


object B {
  case object Query

  case object Entity

  type Payload = Query.type | Entity.type

  case class OK(c: ArraySeq[Payload])

  case class KO(c: Array[Payload])
}

object Main {

  def main(args: Array[String]): Unit = {

    val fury = org.apache.fury.Fury
      .builder()
      .withScalaOptimizationEnabled(true)
      .requireClassRegistration(false)
      .withRefTracking(true)
      .withCompatibleMode(CompatibleMode.COMPATIBLE)
      .build()

    val x =
      fury.deserialize(
        fury.serialize(
          B.OK(ArraySeq(B.Query))))

    println(x)
    // OK(ArraySeq(Query))


    val y =
      fury.deserialize(
        fury.serialize(
          B.KO(Array(B.Query))))

    println(y)
    // Exception in thread "main" java.lang.ClassCastException: class [Ljava.io.Serializable; cannot be cast to class [Lscala.deriving.Mirror$Singleton; ([Ljava.io.Serializable; is in module java.base of loader 'bootstrap'; [Lscala.deriving.Mirror$Singleton; is in unnamed module of loader 'app')
    //	at test2.Main$.main(Main2.scala:43)
    //	at test2.Main.main(Main2.scala)

  }

}
