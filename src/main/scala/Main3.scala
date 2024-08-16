package test3

import org.apache.fury.Fury
import org.apache.fury.config.CompatibleMode

import scala.collection.immutable.ArraySeq


object B {
  case object Query

  //case object Entity

  type Payload = Query.type //| Entity.type

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
    // Exception in thread "main" java.lang.RuntimeException: Create sequential serializer failed, 
    //class: class test3.B$KO
    //	at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:52)
    //	at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$4(ClassResolver.java:997)
    //	at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
    //	at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:993)
    //	at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
    //	at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
    //	at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
    //	at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
    //	at org.apache.fury.Fury.write(Fury.java:343)
    //	at org.apache.fury.Fury.serialize(Fury.java:267)
    //	at org.apache.fury.Fury.serialize(Fury.java:221)
    //	at test3.Main$.main(Main3.scala:45)
    //	at test3.Main.main(Main3.scala)
    //Caused by: java.lang.IllegalArgumentException
    //	at org.apache.fury.util.Preconditions.checkArgument(Preconditions.java:46)
    //	at org.apache.fury.builder.CodecBuilder.getFieldOffset(CodecBuilder.java:328)
    //	at org.apache.fury.builder.CodecBuilder.unsafeAccessField(CodecBuilder.java:287)
    //	at org.apache.fury.builder.CodecBuilder.getFieldValue(CodecBuilder.java:232)
    //	at org.apache.fury.builder.ObjectCodecBuilder.lambda$serializeGroup$ce689565$1(ObjectCodecBuilder.java:210)
    //	at org.apache.fury.builder.ObjectCodecBuilder.serializeGroup(ObjectCodecBuilder.java:219)
    //	at org.apache.fury.builder.ObjectCodecBuilder.addGroupExpressions(ObjectCodecBuilder.java:191)
    //	at org.apache.fury.builder.ObjectCodecBuilder.buildEncodeExpression(ObjectCodecBuilder.java:168)
    //	at org.apache.fury.builder.BaseObjectCodecBuilder.genCode(BaseObjectCodecBuilder.java:203)
    //	at org.apache.fury.codegen.CompileUnit.getCode(CompileUnit.java:53)
    //	at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:81)
    //	at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:73)
    //	at org.apache.fury.codegen.CodeGenerator.compile(CodeGenerator.java:145)
    //	at org.apache.fury.builder.CodecUtils.loadOrGenCodecClass(CodecUtils.java:110)
    //	at org.apache.fury.builder.CodecUtils.loadOrGenObjectCodecClass(CodecUtils.java:43)
    //	at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:49)
    //	... 12 more

  }

}
