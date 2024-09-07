import org.apache.fury.*
import org.apache.fury.config.CompatibleMode
import org.apache.fury.serializer.Serializer
import org.apache.fury.memory.MemoryBuffer

import izumi.reflect.Tag
import izumi.reflect.macrortti.LightTypeTag

import scala.collection.immutable.ArraySeq

object Main6 {

  def main(args: Array[String]): Unit = {

    val fury = org.apache.fury.Fury
      .builder()
      .withScalaOptimizationEnabled(true)
      .requireClassRegistration(false)
      .withRefTracking(true)
      .build()

    val scalaList = List(1, 2, 3)

    val x = fury.deserialize(fury.serialize(scalaList))

    println(x)

    val javaList = new java.util.ArrayList[Int]()
    javaList.add(1)
    javaList.add(2)
    javaList.add(3)

    val y = fury.deserialize(fury.serialize(javaList))

    println(y)
  }
}

/*
[info] running Main6
2024-09-07 10:39:52 WARN  FuryBuilder:375 [sbt-bg-threads-9] - Class registration isn't forced, unknown classes can be deserialized. If the environment isn't secure, please enable class registration by `FuryBuilder#requireClassRegistration(true)` or configure ClassChecker by `ClassResolver#setClassChecker`
2024-09-07 10:39:52 INFO  Fury:160 [sbt-bg-threads-9] - Created new fury org.apache.fury.Fury@b6b2914
2024-09-07 10:39:52 INFO  CompileUnit:55 [sbt-bg-threads-9] - Generate code for scala.collection.immutable._colon_colonFuryRefCodec_1_1940850673_861493145 took 19 ms.
2024-09-07 10:39:52 INFO  JaninoUtils:121 [sbt-bg-threads-9] - Compile [_colon_colonFuryRefCodec_1_1940850673_861493145] take 73 ms
2024-09-07 10:39:52 WARN  ObjectStreamSerializer:92 [sbt-bg-threads-9] - class scala.collection.generic.DefaultSerializationProxy customized jdk serialization, which is inefficient. Please replace it with a org.apache.fury.serializer.Serializer or implements java.io.Externalizable
2024-09-07 10:39:52 INFO  CompileUnit:55 [sbt-bg-threads-9] - Generate code for scala.collection.generic.DefaultSerializationProxyFuryRefCompatibleCodec_1_1940850673_1644367479 took 5 ms.
2024-09-07 10:39:52 INFO  JaninoUtils:121 [sbt-bg-threads-9] - Compile [DefaultSerializationProxyFuryRefCompatibleCodec_1_1940850673_1644367479] take 13 ms
2024-09-07 10:39:52 INFO  CompileUnit:55 [sbt-bg-threads-9] - Generate code for scala.collection.IterableFactory_ToFactoryFuryRefCodec_1_1940850673_127029895 took 1 ms.
2024-09-07 10:39:52 INFO  JaninoUtils:121 [sbt-bg-threads-9] - Compile [IterableFactory_ToFactoryFuryRefCodec_1_1940850673_127029895] take 6 ms
2024-09-07 10:39:52 WARN  ObjectStreamSerializer:92 [sbt-bg-threads-9] - class scala.collection.generic.DefaultSerializationProxy customized jdk serialization, which is inefficient. Please replace it with a org.apache.fury.serializer.Serializer or implements java.io.Externalizable
List(1, 2, 3)
[1, 2, 3]
 */
