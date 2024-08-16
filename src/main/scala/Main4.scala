package test4

import org.apache.fury.Fury
import org.apache.fury.config.CompatibleMode

import zio.Chunk

object B {
  case object Query

  case object Entity

  type Payload = Query.type | Entity.type

  case class KO(c: Chunk[Payload])
}

object Main {

  def main(args: Array[String]): Unit = {

    val fury = org.apache.fury.Fury
      .builder()
      .withScalaOptimizationEnabled(true)
      .requireClassRegistration(false)
      .suppressClassRegistrationWarnings(true)
      .withRefTracking(true)
      .withCompatibleMode(org.apache.fury.config.CompatibleMode.COMPATIBLE)
      .withLanguage(org.apache.fury.config.Language.JAVA)
      .build()


    val x =
      fury.deserialize(
        fury.serialize(
          B.KO(Chunk(B.Query))
        )
      )

    println(x)
    //2024-08-16 11:28:47 WARN  FuryBuilder:372 [main] - Class registration isn't forced, unknown classes can be deserialized. If the environment isn't secure, please enable class registration by `FuryBuilder#requireClassRegistration(true)` or configure ClassChecker by `ClassResolver#setClassChecker`
    //2024-08-16 11:28:47 INFO  Fury:158 [main] - Created new fury org.apache.fury.Fury@2c039ac6
    //2024-08-16 11:28:47 INFO  CompileUnit:55 [main] - Generate code for test4.B_OKFuryRefCodec_1_414493378_2143437117 took 49 ms.
    //2024-08-16 11:28:47 INFO  JaninoUtils:121 [main] - Compile [B_OKFuryRefCodec_1_414493378_2143437117] take 84 ms
    //2024-08-16 11:28:47 INFO  CompileUnit:55 [main] - Generate code for zio.Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938 took 15 ms.
    //2024-08-16 11:28:47 INFO  JaninoUtils:121 [main] - Compile [Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938] take 21 ms
    //2024-08-16 11:28:47 INFO  CompileUnit:55 [main] - Generate code for scala.reflect.ManifestFactory_ObjectManifestFuryRefCompatibleCodec_1_414493378_1282287470 took 9 ms.
    //2024-08-16 11:28:47 INFO  JaninoUtils:121 [main] - Compile [ManifestFactory_ObjectManifestFuryRefCompatibleCodec_1_414493378_1282287470] take 24 ms
    //2024-08-16 11:28:47 INFO  CompileUnit:55 [main] - Generate code for test4.B_OKFuryRefMetaShared7940478628247867472Codec_1_414493378_2143437117 took 2 ms.
    //2024-08-16 11:28:47 INFO  JaninoUtils:121 [main] - Compile [B_OKFuryRefMetaShared7940478628247867472Codec_1_414493378_2143437117] take 9 ms
    //Exception in thread "main"
    //Exception: java.lang.NullPointerException thrown from the UncaughtExceptionHandler in thread "main"

    //////////////////// Digging in...

    // java.lang.ArrayIndexOutOfBoundsException: Index 179 out of bounds for length 5
    //	at org.apache.fury.resolver.MetaStringBytes.decode(MetaStringBytes.java:69)
    //	at org.apache.fury.resolver.ClassResolver.populateBytesToClassInfo(ClassResolver.java:1708)
    //	at org.apache.fury.resolver.ClassResolver.loadBytesToClassInfo(ClassResolver.java:1699)
    //	at org.apache.fury.resolver.ClassResolver.readClassInternal(ClassResolver.java:1588)
    //	at org.apache.fury.serializer.ReplaceResolveSerializer.readObject(ReplaceResolveSerializer.java:310)
    //	at org.apache.fury.serializer.ReplaceResolveSerializer.read(ReplaceResolveSerializer.java:305)
    //	at zio.Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938.readField$(Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938.java:153)
    //	at zio.Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938.readSeparateTypesHashFields$(Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938.java:193)
    //	at zio.Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938.read(Chunk_SingletonFuryRefCompatibleCodec_1_414493378_1392906938.java:289)
    //	at org.apache.fury.serializer.collection.CollectionSerializers$DefaultJavaCollectionSerializer.read(CollectionSerializers.java:715)
    //	at test4.B_KOFuryRefMetaShared6541939900001207120Codec_1_414493378_2143437117.read(B_KOFuryRefMetaShared6541939900001207120Codec_1_414493378_2143437117.java:103)
    //	at org.apache.fury.Fury.readDataInternal(Fury.java:955)
    //	at org.apache.fury.Fury.readRef(Fury.java:857)
    //	at org.apache.fury.Fury.deserialize(Fury.java:789)
    //	at org.apache.fury.Fury.deserialize(Fury.java:711)
    //	at test4.Main$.main(Main4.scala:36)
    //	at test4.Main.main(Main4.scala)
  }
}
