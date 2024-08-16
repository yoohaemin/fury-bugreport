package test

object A {
  object B {
    class C(val value: String) {
      override def toString: String = value
    }
  }
}

object Main {

 def main(args: Array[String]): Unit = {

  val fury = org.apache.fury.Fury.builder()
  .withScalaOptimizationEnabled(true)
  .requireClassRegistration(false)
  .withRefTracking(true)
  .build()

  val x = new A.B.C("hello, world!")
  val bytes = fury.serialize(x)
  val result = fury.deserialize(bytes)

  println(result)
}

}



/*
[info] running test.main
2024-08-16 06:58:41 WARN  FuryBuilder:372 [sbt-bg-threads-5] - Class registration isn't forced, unknown classes can be deserialized. If the environment isn't secure, please enable class registration by `FuryBuilder#requireClassRegistration(true)` or configure ClassChecker by `ClassResolver#setClassChecker`
2024-08-16 06:58:41 INFO  Fury:158 [sbt-bg-threads-5] - Created new fury org.apache.fury.Fury@b725894
2024-08-16 06:58:41 INFO  CompileUnit:55 [sbt-bg-threads-5] - Generate code for test.A_B_CFuryRefCodec_1_2053432556_904350673 took 11 ms.
java.lang.RuntimeException: Create sequential serializer failed,
class: class test.A$B$C
        at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:52)
        at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$2(ClassResolver.java:985)
        at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
        at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:983)
        at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
        at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
        at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
        at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
        at org.apache.fury.Fury.write(Fury.java:343)
        at org.apache.fury.Fury.serialize(Fury.java:267)
        at org.apache.fury.Fury.serialize(Fury.java:221)
        at test.Main$package$.main(Main.scala:20)
        at test.main.main(Main.scala:11)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
Caused by: org.apache.fury.codegen.CodegenException: Compile error:
test.A_B_CFuryRefCodec_1_2053432556_904350673:
/* 0001 */ package test;
/* 0002 */
/* 0003 */ import org.apache.fury.Fury;
/* 0004 */ import org.apache.fury.memory.MemoryBuffer;
/* 0005 */ import org.apache.fury.resolver.MapRefResolver;
/* 0006 */ import org.apache.fury.memory.Platform;
/* 0007 */ import org.apache.fury.resolver.ClassInfo;
/* 0008 */ import org.apache.fury.resolver.ClassInfoHolder;
/* 0009 */ import org.apache.fury.resolver.ClassResolver;
/* 0010 */ import org.apache.fury.builder.Generated;
/* 0011 */ import org.apache.fury.serializer.CodegenSerializer.LazyInitBeanSerializer;
/* 0012 */ import org.apache.fury.serializer.EnumSerializer;
/* 0013 */ import org.apache.fury.serializer.Serializer;
/* 0014 */ import org.apache.fury.serializer.StringSerializer;
/* 0015 */ import org.apache.fury.serializer.ObjectSerializer;
/* 0016 */ import org.apache.fury.serializer.CompatibleSerializer;
/* 0017 */ import org.apache.fury.serializer.collection.AbstractCollectionSerializer;
/* 0018 */ import org.apache.fury.serializer.collection.AbstractMapSerializer;
/* 0019 */ import org.apache.fury.builder.Generated.GeneratedObjectSerializer;
/* 0020 */
/* 0021 */ public final class A_B_CFuryRefCodec_1_2053432556_904350673 extends GeneratedObjectSerializer {
/* 0022 */
/* 0023 */   private final MapRefResolver refResolver;
/* 0024 */   private final ClassResolver classResolver;
/* 0025 */   private final StringSerializer strSerializer;
/* 0026 */   private Fury fury;
/* 0027 */
/* 0028 */   public A_B_CFuryRefCodec_1_2053432556_904350673(Fury fury, Class classType) {
/* 0029 */       super(fury, classType);
/* 0030 */       this.fury = fury;
/* 0031 */       fury.getClassResolver().setSerializerIfAbsent(classType, this);
/* 0032 */
/* 0033 */       org.apache.fury.resolver.RefResolver refResolver0 = fury.getRefResolver();
/* 0034 */       refResolver = ((MapRefResolver)refResolver0);
/* 0035 */       classResolver = fury.getClassResolver();
/* 0036 */       strSerializer = fury.getStringSerializer();
/* 0037 */   }
/* 0038 */
/* 0039 */   @Override public final void write(MemoryBuffer buffer, Object obj) {
/* 0040 */       test.A.B$.C c1 = (test.A.B$.C)obj;
/* 0041 */       Object object1 = Platform.getObject(c1, 12L);
/* 0042 */       String value = (String)object1;
/* 0043 */       if ((value == null)) {
/* 0044 */           buffer.writeByte(((byte)-3));
/* 0045 */       } else {
/* 0046 */           buffer.writeByte(((byte)0));
/* 0047 */           StringSerializer.writeBytesString(buffer, value);
/* 0048 */       }
/* 0049 */   }
/* 0050 */
/* 0051 */   @Override public final Object read(MemoryBuffer buffer) {
/* 0052 */       Object instance = Platform.newInstance(test.A.B$.C.class);
/* 0053 */       test.A.B$.C c2 = (test.A.B$.C)instance;
/* 0054 */       refResolver.reference(c2);
/* 0055 */       if ((buffer.readByte() != ((byte)-3))) {
/* 0056 */           String string = strSerializer.readBytesString(buffer);
/* 0057 */           Platform.putObject(c2, 12L, string);
/* 0058 */       } else {
/* 0059 */           Platform.putObject(c2, 12L, null);
/* 0060 */       }
/* 0061 */       return c2;
/* 0062 */   }
/* 0063 */
/* 0064 */ }
        at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:133)
        at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:73)
        at org.apache.fury.codegen.CodeGenerator.compile(CodeGenerator.java:145)
        at org.apache.fury.builder.CodecUtils.loadOrGenCodecClass(CodecUtils.java:110)
        at org.apache.fury.builder.CodecUtils.loadOrGenObjectCodecClass(CodecUtils.java:43)
        at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:49)
        at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$2(ClassResolver.java:985)
        at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
        at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:983)
        at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
        at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
        at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
        at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
        at org.apache.fury.Fury.write(Fury.java:343)
        at org.apache.fury.Fury.serialize(Fury.java:267)
        at org.apache.fury.Fury.serialize(Fury.java:221)
        at test.Main$package$.main(Main.scala:20)
        at test.main.main(Main.scala:11)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
Caused by: org.apache.fury.shaded.org.codehaus.commons.compiler.CompileException: File 'test/A_B_CFuryRefCodec_1_2053432556_904350673.java', Line 40, Column 17: "test.A$B$" declares no member type "C"
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compileError(UnitCompiler.java:13080)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.getReferenceType(UnitCompiler.java:6904)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.getType2(UnitCompiler.java:6857)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.access$14800(UnitCompiler.java:237)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$24.visitReferenceType(UnitCompiler.java:6755)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$24.visitReferenceType(UnitCompiler.java:6752)
        at org.apache.fury.shaded.org.codehaus.janino.Java$ReferenceType.accept(Java.java:4289)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.getType(UnitCompiler.java:6752)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.getLocalVariable(UnitCompiler.java:2816)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.buildLocalVariableMap(UnitCompiler.java:3981)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.access$6000(UnitCompiler.java:237)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$12.visitLocalVariableDeclarationStatement(UnitCompiler.java:3864)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$12.visitLocalVariableDeclarationStatement(UnitCompiler.java:3835)
        at org.apache.fury.shaded.org.codehaus.janino.Java$LocalVariableDeclarationStatement.accept(Java.java:3842)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.buildLocalVariableMap(UnitCompiler.java:3834)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.buildLocalVariableMap(UnitCompiler.java:3804)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compile2(UnitCompiler.java:3582)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compile(UnitCompiler.java:3330)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compileDeclaredMethods(UnitCompiler.java:1448)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compileDeclaredMethods(UnitCompiler.java:1421)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compile2(UnitCompiler.java:830)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compile2(UnitCompiler.java:443)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.access$400(UnitCompiler.java:237)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$3.visitPackageMemberClassDeclaration(UnitCompiler.java:423)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$3.visitPackageMemberClassDeclaration(UnitCompiler.java:419)
        at org.apache.fury.shaded.org.codehaus.janino.Java$PackageMemberClassDeclaration.accept(Java.java:1688)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compile(UnitCompiler.java:419)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compile2(UnitCompiler.java:393)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.access$000(UnitCompiler.java:237)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$2.visitCompilationUnit(UnitCompiler.java:364)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler$2.visitCompilationUnit(UnitCompiler.java:362)
        at org.apache.fury.shaded.org.codehaus.janino.Java$CompilationUnit.accept(Java.java:371)
        at org.apache.fury.shaded.org.codehaus.janino.UnitCompiler.compileUnit(UnitCompiler.java:362)
        at org.apache.fury.shaded.org.codehaus.janino.Compiler.compile2(Compiler.java:271)
        at org.apache.fury.shaded.org.codehaus.janino.Compiler.compile(Compiler.java:213)
        at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:115)
        at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:73)
        at org.apache.fury.codegen.CodeGenerator.compile(CodeGenerator.java:145)
        at org.apache.fury.builder.CodecUtils.loadOrGenCodecClass(CodecUtils.java:110)
        at org.apache.fury.builder.CodecUtils.loadOrGenObjectCodecClass(CodecUtils.java:43)
        at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:49)
        at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$2(ClassResolver.java:985)
        at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
        at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:983)
        at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
        at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
        at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
        at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
        at org.apache.fury.Fury.write(Fury.java:343)
        at org.apache.fury.Fury.serialize(Fury.java:267)
        at org.apache.fury.Fury.serialize(Fury.java:221)
        at test.Main$package$.main(Main.scala:20)
        at test.main.main(Main.scala:11)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)


 */
