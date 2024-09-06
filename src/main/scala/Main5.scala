package test2

import org.apache.fury.*
import org.apache.fury.config.CompatibleMode
import org.apache.fury.serializer.Serializer
import org.apache.fury.memory.MemoryBuffer

import izumi.reflect.Tag
import izumi.reflect.macrortti.LightTypeTag

import scala.collection.immutable.ArraySeq

object Main5 {

  def main(args: Array[String]): Unit = {

    val fury = org.apache.fury.Fury
      .builder()
      .withScalaOptimizationEnabled(true)
      .requireClassRegistration(false)
      .withRefTracking(true)
      .withCompatibleMode(CompatibleMode.SCHEMA_CONSISTENT)
      .build()

    fury.registerSerializer(classOf[LightTypeTag], classOf[LightTypeTagSerializer])

    val tag: LightTypeTag = Tag[Int].tag

    val x =
      fury.deserialize(fury.serialize(tag))

    println(x)
  }
}

final class LightTypeTagSerializer(fury: Fury)
    extends Serializer[LightTypeTag](
      fury,
      classOf[LightTypeTag],
      false,
      false,
    ) {

  override def read(buffer: MemoryBuffer): LightTypeTag = {
    val hash            = buffer.readInt32()
    val refLength       = buffer.readByte()
    val refBytes        = buffer.readBytes(refLength)
    val ref             = new String(refBytes)
    val databasesLength = buffer.readByte()
    val databasesBytes  = buffer.readBytes(databasesLength)
    val databases       = new String(databasesBytes)
    val version         = buffer.readInt32()
    LightTypeTag.parse(LightTypeTag.Serialized(hash, ref, databases, version))
  }

  override def write(buffer: MemoryBuffer, value: LightTypeTag): Unit = {
    val serialized = value.serialize()
    buffer.writeInt32(serialized.hash)
    val refBytes = serialized.ref.getBytes()
    buffer.writeByte(refBytes.length.toByte)
    buffer.writeBytes(refBytes)
    val databasesBytes = serialized.databases.getBytes()
    buffer.writeByte(databasesBytes.length.toByte)
    buffer.writeBytes(databasesBytes)
    buffer.writeInt32(serialized.version)
  }
}


/*
[info] running test2.Main5 test2.Main5
2024-09-07 03:24:48 WARN  FuryBuilder:375 [sbt-bg-threads-1] - Class registration isn't forced, unknown classes can be deserialized. If the environment isn't secure, please enable class registration by `FuryBuilder#requireClassRegistration(true)` or configure ClassChecker by `ClassResolver#setClassChecker`
2024-09-07 03:24:48 INFO  Fury:160 [sbt-bg-threads-1] - Created new fury org.apache.fury.Fury@4d718886
2024-09-07 03:24:49 INFO  CompileUnit:55 [sbt-bg-threads-1] - Generate code for izumi.reflect.macrortti.LightTypeTag_ParsedLightTypeTag230PlusFuryRefCodec_1_1093770338_1107226555 took 41 ms.
[error] java.lang.RuntimeException: Create sequential serializer failed,
[error] class: class izumi.reflect.macrortti.LightTypeTag$ParsedLightTypeTag230Plus
[error]         at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:52)
[error]         at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$2(ClassResolver.java:985)
[error]         at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
[error]         at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:983)
[error]         at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
[error]         at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
[error]         at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
[error]         at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
[error]         at org.apache.fury.Fury.write(Fury.java:345)
[error]         at org.apache.fury.Fury.serialize(Fury.java:269)
[error]         at org.apache.fury.Fury.serialize(Fury.java:223)
[error]         at test2.Main5$.main(Main5.scala:25)
[error]         at test2.Main5.main(Main5.scala)
[error]         at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
[error]         at java.base/java.lang.reflect.Method.invoke(Method.java:580)
[error] Caused by: org.apache.fury.codegen.CodegenException: Compile error:
[error] izumi.reflect.macrortti.LightTypeTag_ParsedLightTypeTag230PlusFuryRefCodec_1_1093770338_1107226555:
[error] /* 0001 */ package izumi.reflect.macrortti;
[error] /* 0002 */
[error] /* 0003 */ import org.apache.fury.Fury;
[error] /* 0004 */ import org.apache.fury.memory.MemoryBuffer;
[error] /* 0005 */ import org.apache.fury.resolver.MapRefResolver;
[error] /* 0006 */ import org.apache.fury.memory.Platform;
[error] /* 0007 */ import org.apache.fury.resolver.ClassInfo;
[error] /* 0008 */ import org.apache.fury.resolver.ClassInfoHolder;
[error] /* 0009 */ import org.apache.fury.resolver.ClassResolver;
[error] /* 0010 */ import org.apache.fury.builder.Generated;
[error] /* 0011 */ import org.apache.fury.serializer.CodegenSerializer.LazyInitBeanSerializer;
[error] /* 0012 */ import org.apache.fury.serializer.EnumSerializer;
[error] /* 0013 */ import org.apache.fury.serializer.Serializer;
[error] /* 0014 */ import org.apache.fury.serializer.StringSerializer;
[error] /* 0015 */ import org.apache.fury.serializer.ObjectSerializer;
[error] /* 0016 */ import org.apache.fury.serializer.CompatibleSerializer;
[error] /* 0017 */ import org.apache.fury.serializer.collection.AbstractCollectionSerializer;
[error] /* 0018 */ import org.apache.fury.serializer.collection.AbstractMapSerializer;
[error] /* 0019 */ import org.apache.fury.builder.Generated.GeneratedObjectSerializer;
[error] /* 0020 */
[error] /* 0021 */ public final class LightTypeTag_ParsedLightTypeTag230PlusFuryRefCodec_1_1093770338_1107226555 extends GeneratedObjectSerializer {
[error] /* 0022 */
[error] /* 0023 */   private final MapRefResolver refResolver;
[error] /* 0024 */   private final ClassResolver classResolver;
[error] /* 0025 */   private final StringSerializer strSerializer;
[error] /* 0026 */   private Fury fury;
[error] /* 0027 */   private ClassInfo lightTypeTagRefClassInfo;
[error] /* 0028 */   private ClassInfo function0ClassInfo;
[error] /* 0029 */   private ClassInfo function02ClassInfo;
[error] /* 0030 */   private ClassInfo mapClassInfo;
[error] /* 0031 */   private ClassInfo map2ClassInfo;
[error] /* 0032 */   private ClassInfo object0ClassInfo;
[error] /* 0033 */   private ClassInfo object4ClassInfo;
[error] /* 0034 */   private final ClassInfoHolder lightTypeTagRef2ClassInfoHolder;
[error] /* 0035 */   private final ClassInfoHolder function04ClassInfoHolder;
[error] /* 0036 */   private final ClassInfoHolder function05ClassInfoHolder;
[error] /* 0037 */   private final ClassInfoHolder map5ClassInfoHolder;
[error] /* 0038 */   private final ClassInfoHolder map6ClassInfoHolder;
[error] /* 0039 */   private final ClassInfoHolder object11ClassInfoHolder;
[error] /* 0040 */   private final ClassInfoHolder object12ClassInfoHolder;
[error] /* 0041 */
[error] /* 0042 */   public LightTypeTag_ParsedLightTypeTag230PlusFuryRefCodec_1_1093770338_1107226555(Fury fury, Class classType) {
[error] /* 0043 */       super(fury, classType);
[error] /* 0044 */       this.fury = fury;
[error] /* 0045 */       fury.getClassResolver().setSerializerIfAbsent(classType, this);
[error] /* 0046 */
[error] /* 0047 */       org.apache.fury.resolver.RefResolver refResolver0 = fury.getRefResolver();
[error] /* 0048 */       refResolver = ((MapRefResolver)refResolver0);
[error] /* 0049 */       classResolver = fury.getClassResolver();
[error] /* 0050 */       strSerializer = fury.getStringSerializer();
[error] /* 0051 */       lightTypeTagRefClassInfo = classResolver.nilClassInfo();
[error] /* 0052 */       function0ClassInfo = classResolver.nilClassInfo();
[error] /* 0053 */       function02ClassInfo = classResolver.nilClassInfo();
[error] /* 0054 */       mapClassInfo = classResolver.nilClassInfo();
[error] /* 0055 */       map2ClassInfo = classResolver.nilClassInfo();
[error] /* 0056 */       object0ClassInfo = classResolver.nilClassInfo();
[error] /* 0057 */       object4ClassInfo = classResolver.nilClassInfo();
[error] /* 0058 */       lightTypeTagRef2ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0059 */       function04ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0060 */       function05ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0061 */       map5ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0062 */       map6ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0063 */       object11ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0064 */       object12ClassInfoHolder = classResolver.nilClassInfoHolder();
[error] /* 0065 */   }
[error] /* 0066 */
[error] /* 0067 */   private void writeFields(izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus parsedLightTypeTag230Plus1, MemoryBuffer memoryBuffer, byte[] arr) {
[error] /* 0068 */       memoryBuffer._unsafeWriteSliInt64(parsedLightTypeTag230Plus1.0bitmap$1);
[error] /* 0069 */       memoryBuffer._unsafeWriteSliInt64(parsedLightTypeTag230Plus1.0bitmap$3);
[error] /* 0070 */       memoryBuffer._unsafeWriteVarInt32(Platform.getInt(parsedLightTypeTag230Plus1, 48L));
[error] /* 0071 */       memoryBuffer._unsafeWriteVarInt32(Platform.getInt(parsedLightTypeTag230Plus1, 52L));
[error] /* 0072 */       memoryBuffer._unsafeWriteVarInt32(Platform.getInt(parsedLightTypeTag230Plus1, 12L));
[error] /* 0073 */   }
[error] /* 0074 */
[error] /* 0075 */   private void writeClassAndObject(izumi.reflect.macrortti.LightTypeTagRef lightTypeTagRef1, MemoryBuffer memoryBuffer1) {
[error] /* 0076 */       ClassResolver classResolver = this.classResolver;
[error] /* 0077 */       Class value = lightTypeTagRefClassInfo.getCls();
[error] /* 0078 */       Class cls = lightTypeTagRef1.getClass();
[error] /* 0079 */       if ((value != cls)) {
[error] /* 0080 */           lightTypeTagRefClassInfo = classResolver.getClassInfo(cls);
[error] /* 0081 */       }
[error] /* 0082 */       classResolver.writeClass(memoryBuffer1, lightTypeTagRefClassInfo);
[error] /* 0083 */       lightTypeTagRefClassInfo.getSerializer().write(memoryBuffer1, lightTypeTagRef1);
[error] /* 0084 */   }
[error] /* 0085 */
[error] /* 0086 */   private void writeClassAndObject1(scala.Function0 function01, MemoryBuffer memoryBuffer2) {
[error] /* 0087 */       ClassResolver classResolver = this.classResolver;
[error] /* 0088 */       Class value0 = function0ClassInfo.getCls();
[error] /* 0089 */       Class cls0 = function01.getClass();
[error] /* 0090 */       if ((value0 != cls0)) {
[error] /* 0091 */           function0ClassInfo = classResolver.getClassInfo(cls0);
[error] /* 0092 */       }
[error] /* 0093 */       classResolver.writeClass(memoryBuffer2, function0ClassInfo);
[error] /* 0094 */       function0ClassInfo.getSerializer().write(memoryBuffer2, function01);
[error] /* 0095 */   }
[error] /* 0096 */
[error] /* 0097 */   private void writeClassAndObject2(MemoryBuffer memoryBuffer3, scala.Function0 function03) {
[error] /* 0098 */       ClassResolver classResolver = this.classResolver;
[error] /* 0099 */       Class value1 = function02ClassInfo.getCls();
[error] /* 0100 */       Class cls1 = function03.getClass();
[error] /* 0101 */       if ((value1 != cls1)) {
[error] /* 0102 */           function02ClassInfo = classResolver.getClassInfo(cls1);
[error] /* 0103 */       }
[error] /* 0104 */       classResolver.writeClass(memoryBuffer3, function02ClassInfo);
[error] /* 0105 */       function02ClassInfo.getSerializer().write(memoryBuffer3, function03);
[error] /* 0106 */   }
[error] /* 0107 */
[error] /* 0108 */   private AbstractMapSerializer writeMapClassInfo(MemoryBuffer memoryBuffer4, scala.collection.immutable.Map map1) {
[error] /* 0109 */       ClassResolver classResolver = this.classResolver;
[error] /* 0110 */       Class value2 = mapClassInfo.getCls();
[error] /* 0111 */       Class cls2 = map1.getClass();
[error] /* 0112 */       if ((value2 != cls2)) {
[error] /* 0113 */           mapClassInfo = classResolver.getClassInfo(cls2);
[error] /* 0114 */       }
[error] /* 0115 */       classResolver.writeClass(memoryBuffer4, mapClassInfo);
[error] /* 0116 */       Serializer serializer = mapClassInfo.getSerializer();
[error] /* 0117 */       return ((AbstractMapSerializer)serializer);
[error] /* 0118 */   }
[error] /* 0119 */
[error] /* 0120 */   private AbstractMapSerializer writeMapClassInfo1(scala.collection.immutable.Map map3, MemoryBuffer memoryBuffer5) {
[error] /* 0121 */       ClassResolver classResolver = this.classResolver;
[error] /* 0122 */       Class value3 = map2ClassInfo.getCls();
[error] /* 0123 */       Class cls3 = map3.getClass();
[error] /* 0124 */       if ((value3 != cls3)) {
[error] /* 0125 */           map2ClassInfo = classResolver.getClassInfo(cls3);
[error] /* 0126 */       }
[error] /* 0127 */       classResolver.writeClass(memoryBuffer5, map2ClassInfo);
[error] /* 0128 */       Serializer serializer0 = map2ClassInfo.getSerializer();
[error] /* 0129 */       return ((AbstractMapSerializer)serializer0);
[error] /* 0130 */   }
[error] /* 0131 */
[error] /* 0132 */   private void writeClassAndObject3(Object object3, MemoryBuffer memoryBuffer7) {
[error] /* 0133 */       ClassResolver classResolver = this.classResolver;
[error] /* 0134 */       Class value4 = object0ClassInfo.getCls();
[error] /* 0135 */       Class cls4 = object3.getClass();
[error] /* 0136 */       if ((value4 != cls4)) {
[error] /* 0137 */           object0ClassInfo = classResolver.getClassInfo(cls4);
[error] /* 0138 */       }
[error] /* 0139 */       classResolver.writeClass(memoryBuffer7, object0ClassInfo);
[error] /* 0140 */       object0ClassInfo.getSerializer().write(memoryBuffer7, object3);
[error] /* 0141 */   }
[error] /* 0142 */
[error] /* 0143 */   private void writeClassAndObject4(MemoryBuffer memoryBuffer8, Object object5) {
[error] /* 0144 */       ClassResolver classResolver = this.classResolver;
[error] /* 0145 */       Class value5 = object4ClassInfo.getCls();
[error] /* 0146 */       Class cls5 = object5.getClass();
[error] /* 0147 */       if ((value5 != cls5)) {
[error] /* 0148 */           object4ClassInfo = classResolver.getClassInfo(cls5);
[error] /* 0149 */       }
[error] /* 0150 */       classResolver.writeClass(memoryBuffer8, object4ClassInfo);
[error] /* 0151 */       object4ClassInfo.getSerializer().write(memoryBuffer8, object5);
[error] /* 0152 */   }
[error] /* 0153 */
[error] /* 0154 */   private void writeClassAndObject5(MemoryBuffer memoryBuffer9, Object object6) {
[error] /* 0155 */       ClassResolver classResolver = this.classResolver;
[error] /* 0156 */       Class value6 = object0ClassInfo.getCls();
[error] /* 0157 */       Class cls6 = object6.getClass();
[error] /* 0158 */       if ((value6 != cls6)) {
[error] /* 0159 */           object0ClassInfo = classResolver.getClassInfo(cls6);
[error] /* 0160 */       }
[error] /* 0161 */       classResolver.writeClass(memoryBuffer9, object0ClassInfo);
[error] /* 0162 */       object0ClassInfo.getSerializer().write(memoryBuffer9, object6);
[error] /* 0163 */   }
[error] /* 0164 */
[error] /* 0165 */   private void writeClassAndObject6(Object object7, MemoryBuffer memoryBuffer10) {
[error] /* 0166 */       ClassResolver classResolver = this.classResolver;
[error] /* 0167 */       Class value7 = object4ClassInfo.getCls();
[error] /* 0168 */       Class cls7 = object7.getClass();
[error] /* 0169 */       if ((value7 != cls7)) {
[error] /* 0170 */           object4ClassInfo = classResolver.getClassInfo(cls7);
[error] /* 0171 */       }
[error] /* 0172 */       classResolver.writeClass(memoryBuffer10, object4ClassInfo);
[error] /* 0173 */       object4ClassInfo.getSerializer().write(memoryBuffer10, object7);
[error] /* 0174 */   }
[error] /* 0175 */
[error] /* 0176 */   private void writeFields1(MemoryBuffer memoryBuffer6, izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus parsedLightTypeTag230Plus2) {
[error] /* 0177 */       MapRefResolver refResolver = this.refResolver;
[error] /* 0178 */       izumi.reflect.macrortti.LightTypeTagRef ref$lzy2 = parsedLightTypeTag230Plus2.ref$lzy2;
[error] /* 0179 */       if ((!refResolver.writeRefOrNull(memoryBuffer6, ref$lzy2))) {
[error] /* 0180 */           this.writeClassAndObject(ref$lzy2, memoryBuffer6);
[error] /* 0181 */       }
[error] /* 0182 */       Object object1 = Platform.getObject(parsedLightTypeTag230Plus2, 24L);
[error] /* 0183 */       scala.Function0 bases = (scala.Function0)object1;
[error] /* 0184 */       if ((!refResolver.writeRefOrNull(memoryBuffer6, bases))) {
[error] /* 0185 */           this.writeClassAndObject1(bases, memoryBuffer6);
[error] /* 0186 */       }
[error] /* 0187 */       Object object22 = Platform.getObject(parsedLightTypeTag230Plus2, 28L);
[error] /* 0188 */       scala.Function0 inheritanceDb = (scala.Function0)object22;
[error] /* 0189 */       if ((!refResolver.writeRefOrNull(memoryBuffer6, inheritanceDb))) {
[error] /* 0190 */           this.writeClassAndObject2(memoryBuffer6, inheritanceDb);
[error] /* 0191 */       }
[error] /* 0192 */       scala.collection.immutable.Map basesdb$lzy1 = parsedLightTypeTag230Plus2.basesdb$lzy1;
[error] /* 0193 */       if ((!refResolver.writeRefOrNull(memoryBuffer6, basesdb$lzy1))) {
[error] /* 0194 */           AbstractMapSerializer abstractMapSerializer = this.writeMapClassInfo(memoryBuffer6, basesdb$lzy1);
[error] /* 0195 */           if (abstractMapSerializer.supportCodegenHook()) {
[error] /* 0196 */               java.util.Map map0 = abstractMapSerializer.onMapWrite(memoryBuffer6, basesdb$lzy1);
[error] /* 0197 */               java.util.Set entrySet = map0.entrySet();
[error] /* 0198 */               java.util.Iterator iter = entrySet.iterator();
[error] /* 0199 */               int i = 0;
[error] /* 0200 */               while (iter.hasNext()) {
[error] /* 0201 */                   Object elemValue = iter.next();
[error] /* 0202 */                   java.util.Map.Entry entry = (java.util.Map.Entry)elemValue;
[error] /* 0203 */                   Object keyObj = entry.getKey();
[error] /* 0204 */                   if ((!refResolver.writeRefOrNull(memoryBuffer6, keyObj))) {
[error] /* 0205 */                       this.writeClassAndObject3(keyObj, memoryBuffer6);
[error] /* 0206 */                   }
[error] /* 0207 */                   Object valueObj = entry.getValue();
[error] /* 0208 */                   if ((!refResolver.writeRefOrNull(memoryBuffer6, valueObj))) {
[error] /* 0209 */                       this.writeClassAndObject4(memoryBuffer6, valueObj);
[error] /* 0210 */                   }
[error] /* 0211 */                   i++;
[error] /* 0212 */               }
[error] /* 0213 */           } else {
[error] /* 0214 */               abstractMapSerializer.write(memoryBuffer6, basesdb$lzy1);
[error] /* 0215 */           }
[error] /* 0216 */       }
[error] /* 0217 */       scala.collection.immutable.Map idb$lzy1 = parsedLightTypeTag230Plus2.idb$lzy1;
[error] /* 0218 */       if ((!refResolver.writeRefOrNull(memoryBuffer6, idb$lzy1))) {
[error] /* 0219 */           AbstractMapSerializer abstractMapSerializer1 = this.writeMapClassInfo1(idb$lzy1, memoryBuffer6);
[error] /* 0220 */           if (abstractMapSerializer1.supportCodegenHook()) {
[error] /* 0221 */               java.util.Map map4 = abstractMapSerializer1.onMapWrite(memoryBuffer6, idb$lzy1);
[error] /* 0222 */               java.util.Set entrySet1 = map4.entrySet();
[error] /* 0223 */               java.util.Iterator iter1 = entrySet1.iterator();
[error] /* 0224 */               int i1 = 0;
[error] /* 0225 */               while (iter1.hasNext()) {
[error] /* 0226 */                   Object elemValue1 = iter1.next();
[error] /* 0227 */                   java.util.Map.Entry entry1 = (java.util.Map.Entry)elemValue1;
[error] /* 0228 */                   Object keyObj1 = entry1.getKey();
[error] /* 0229 */                   if ((!refResolver.writeRefOrNull(memoryBuffer6, keyObj1))) {
[error] /* 0230 */                       this.writeClassAndObject5(memoryBuffer6, keyObj1);
[error] /* 0231 */                   }
[error] /* 0232 */                   Object valueObj1 = entry1.getValue();
[error] /* 0233 */                   if ((!refResolver.writeRefOrNull(memoryBuffer6, valueObj1))) {
[error] /* 0234 */                       this.writeClassAndObject6(valueObj1, memoryBuffer6);
[error] /* 0235 */                   }
[error] /* 0236 */                   i1++;
[error] /* 0237 */               }
[error] /* 0238 */           } else {
[error] /* 0239 */               abstractMapSerializer1.write(memoryBuffer6, idb$lzy1);
[error] /* 0240 */           }
[error] /* 0241 */       }
[error] /* 0242 */   }
[error] /* 0243 */
[error] /* 0244 */   private void readFields(MemoryBuffer memoryBuffer11, byte[] arr1, izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus parsedLightTypeTag230Plus3) {
[error] /* 0245 */       parsedLightTypeTag230Plus3.0bitmap$1 = memoryBuffer11._readSliInt64OnLE();
[error] /* 0246 */       parsedLightTypeTag230Plus3.0bitmap$3 = memoryBuffer11._readSliInt64OnLE();
[error] /* 0247 */       Platform.putInt(parsedLightTypeTag230Plus3, 48L, memoryBuffer11._readVarInt32OnLE());
[error] /* 0248 */       Platform.putInt(parsedLightTypeTag230Plus3, 52L, memoryBuffer11._readVarInt32OnLE());
[error] /* 0249 */       Platform.putInt(parsedLightTypeTag230Plus3, 12L, memoryBuffer11._readVarInt32OnLE());
[error] /* 0250 */   }
[error] /* 0251 */
[error] /* 0252 */   private void readFields1(izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus parsedLightTypeTag230Plus4, MemoryBuffer memoryBuffer12) {
[error] /* 0253 */       MapRefResolver refResolver = this.refResolver;
[error] /* 0254 */       ClassResolver classResolver = this.classResolver;
[error] /* 0255 */       int refId = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0256 */       if ((refId >= ((byte)-1))) {
[error] /* 0257 */           Object object8 = classResolver.readClassInfo(memoryBuffer12, lightTypeTagRef2ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0258 */           refResolver.setReadObject(refId, object8);
[error] /* 0259 */           parsedLightTypeTag230Plus4.ref$lzy2 = ((izumi.reflect.macrortti.LightTypeTagRef)object8);
[error] /* 0260 */       } else {
[error] /* 0261 */           parsedLightTypeTag230Plus4.ref$lzy2 = ((izumi.reflect.macrortti.LightTypeTagRef)refResolver.getReadObject());
[error] /* 0262 */       }
[error] /* 0263 */       int refId1 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0264 */       if ((refId1 >= ((byte)-1))) {
[error] /* 0265 */           Object object9 = classResolver.readClassInfo(memoryBuffer12, function04ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0266 */           refResolver.setReadObject(refId1, object9);
[error] /* 0267 */           Platform.putObject(parsedLightTypeTag230Plus4, 24L, ((scala.Function0)object9));
[error] /* 0268 */       } else {
[error] /* 0269 */           Platform.putObject(parsedLightTypeTag230Plus4, 24L, ((scala.Function0)refResolver.getReadObject()));
[error] /* 0270 */       }
[error] /* 0271 */       int refId2 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0272 */       if ((refId2 >= ((byte)-1))) {
[error] /* 0273 */           Object object10 = classResolver.readClassInfo(memoryBuffer12, function05ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0274 */           refResolver.setReadObject(refId2, object10);
[error] /* 0275 */           Platform.putObject(parsedLightTypeTag230Plus4, 28L, ((scala.Function0)object10));
[error] /* 0276 */       } else {
[error] /* 0277 */           Platform.putObject(parsedLightTypeTag230Plus4, 28L, ((scala.Function0)refResolver.getReadObject()));
[error] /* 0278 */       }
[error] /* 0279 */       int refId3 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0280 */       if ((refId3 >= ((byte)-1))) {
[error] /* 0281 */           Serializer serializer1 = classResolver.readClassInfo(memoryBuffer12, map5ClassInfoHolder).getSerializer();
[error] /* 0282 */           AbstractMapSerializer mapSerializer = (AbstractMapSerializer)serializer1;
[error] /* 0283 */           Object object19;
[error] /* 0284 */           if (mapSerializer.supportCodegenHook()) {
[error] /* 0285 */               java.util.Map map7 = mapSerializer.newMap(memoryBuffer12);
[error] /* 0286 */               int size = mapSerializer.getAndClearNumElements();
[error] /* 0287 */               for (int i0 = 0; i0 < size; i0+=1) {
[error] /* 0288 */                 int refId4 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0289 */                 Object object14;
[error] /* 0290 */                 if ((refId4 >= ((byte)-1))) {
[error] /* 0291 */                     Object object13 = classResolver.readClassInfo(memoryBuffer12, object11ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0292 */                     refResolver.setReadObject(refId4, object13);
[error] /* 0293 */                     object14 = object13;
[error] /* 0294 */                 } else {
[error] /* 0295 */                     object14 = refResolver.getReadObject();
[error] /* 0296 */                 }
[error] /* 0297 */                 int refId5 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0298 */                 Object object16;
[error] /* 0299 */                 if ((refId5 >= ((byte)-1))) {
[error] /* 0300 */                     Object object15 = classResolver.readClassInfo(memoryBuffer12, object12ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0301 */                     refResolver.setReadObject(refId5, object15);
[error] /* 0302 */                     object16 = object15;
[error] /* 0303 */                 } else {
[error] /* 0304 */                     object16 = refResolver.getReadObject();
[error] /* 0305 */                 }
[error] /* 0306 */                 map7.put(object14, object16);
[error] /* 0307 */               }
[error] /* 0308 */               Object object17 = mapSerializer.onMapRead(map7);
[error] /* 0309 */               object19 = object17;
[error] /* 0310 */           } else {
[error] /* 0311 */               Object object18 = mapSerializer.read(memoryBuffer12);
[error] /* 0312 */               object19 = object18;
[error] /* 0313 */           }
[error] /* 0314 */           refResolver.setReadObject(refId3, object19);
[error] /* 0315 */           parsedLightTypeTag230Plus4.basesdb$lzy1 = ((scala.collection.immutable.Map)object19);
[error] /* 0316 */       } else {
[error] /* 0317 */           parsedLightTypeTag230Plus4.basesdb$lzy1 = ((scala.collection.immutable.Map)refResolver.getReadObject());
[error] /* 0318 */       }
[error] /* 0319 */       int refId6 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0320 */       if ((refId6 >= ((byte)-1))) {
[error] /* 0321 */           Serializer serializer2 = classResolver.readClassInfo(memoryBuffer12, map6ClassInfoHolder).getSerializer();
[error] /* 0322 */           AbstractMapSerializer mapSerializer1 = (AbstractMapSerializer)serializer2;
[error] /* 0323 */           Object object27;
[error] /* 0324 */           if (mapSerializer1.supportCodegenHook()) {
[error] /* 0325 */               java.util.Map map8 = mapSerializer1.newMap(memoryBuffer12);
[error] /* 0326 */               int size1 = mapSerializer1.getAndClearNumElements();
[error] /* 0327 */               for (int i2 = 0; i2 < size1; i2+=1) {
[error] /* 0328 */                 int refId7 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0329 */                 Object object21;
[error] /* 0330 */                 if ((refId7 >= ((byte)-1))) {
[error] /* 0331 */                     Object object20 = classResolver.readClassInfo(memoryBuffer12, object11ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0332 */                     refResolver.setReadObject(refId7, object20);
[error] /* 0333 */                     object21 = object20;
[error] /* 0334 */                 } else {
[error] /* 0335 */                     object21 = refResolver.getReadObject();
[error] /* 0336 */                 }
[error] /* 0337 */                 int refId8 = refResolver.tryPreserveRefId(memoryBuffer12);
[error] /* 0338 */                 Object object24;
[error] /* 0339 */                 if ((refId8 >= ((byte)-1))) {
[error] /* 0340 */                     Object object23 = classResolver.readClassInfo(memoryBuffer12, object12ClassInfoHolder).getSerializer().read(memoryBuffer12);
[error] /* 0341 */                     refResolver.setReadObject(refId8, object23);
[error] /* 0342 */                     object24 = object23;
[error] /* 0343 */                 } else {
[error] /* 0344 */                     object24 = refResolver.getReadObject();
[error] /* 0345 */                 }
[error] /* 0346 */                 map8.put(object21, object24);
[error] /* 0347 */               }
[error] /* 0348 */               Object object25 = mapSerializer1.onMapRead(map8);
[error] /* 0349 */               object27 = object25;
[error] /* 0350 */           } else {
[error] /* 0351 */               Object object26 = mapSerializer1.read(memoryBuffer12);
[error] /* 0352 */               object27 = object26;
[error] /* 0353 */           }
[error] /* 0354 */           refResolver.setReadObject(refId6, object27);
[error] /* 0355 */           parsedLightTypeTag230Plus4.idb$lzy1 = ((scala.collection.immutable.Map)object27);
[error] /* 0356 */       } else {
[error] /* 0357 */           parsedLightTypeTag230Plus4.idb$lzy1 = ((scala.collection.immutable.Map)refResolver.getReadObject());
[error] /* 0358 */       }
[error] /* 0359 */   }
[error] /* 0360 */
[error] /* 0361 */   @Override public final void write(MemoryBuffer buffer, Object obj) {
[error] /* 0362 */       izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus parsedLightTypeTag230Plus5 = (izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus)obj;
[error] /* 0363 */       buffer.grow(42);
[error] /* 0364 */       byte[] base = buffer.getHeapMemory();
[error] /* 0365 */       this.writeFields(parsedLightTypeTag230Plus5, buffer, base);
[error] /* 0366 */       Object object280 = Platform.getObject(parsedLightTypeTag230Plus5, 56L);
[error] /* 0367 */       String refString = (String)object280;
[error] /* 0368 */       if ((refString == null)) {
[error] /* 0369 */           buffer.writeByte(((byte)-3));
[error] /* 0370 */       } else {
[error] /* 0371 */           buffer.writeByte(((byte)0));
[error] /* 0372 */           StringSerializer.writeBytesString(buffer, refString);
[error] /* 0373 */       }
[error] /* 0374 */       this.writeFields1(buffer, parsedLightTypeTag230Plus5);
[error] /* 0375 */   }
[error] /* 0376 */
[error] /* 0377 */   @Override public final Object read(MemoryBuffer buffer) {
[error] /* 0378 */       Object instance = Platform.newInstance(izumi.reflect.macrortti.LightTypeTag$ParsedLightTypeTag230Plus.class);
[error] /* 0379 */       izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus parsedLightTypeTag230Plus6 = (izumi.reflect.macrortti.LightTypeTag.ParsedLightTypeTag230Plus)instance;
[error] /* 0380 */       refResolver.reference(parsedLightTypeTag230Plus6);
[error] /* 0381 */       byte[] heapBuffer = buffer.getHeapMemory();
[error] /* 0382 */       this.readFields(buffer, heapBuffer, parsedLightTypeTag230Plus6);
[error] /* 0383 */       if ((buffer.readByte() != ((byte)-3))) {
[error] /* 0384 */           String string = strSerializer.readBytesString(buffer);
[error] /* 0385 */           Platform.putObject(parsedLightTypeTag230Plus6, 56L, string);
[error] /* 0386 */       } else {
[error] /* 0387 */           Platform.putObject(parsedLightTypeTag230Plus6, 56L, null);
[error] /* 0388 */       }
[error] /* 0389 */       this.readFields1(parsedLightTypeTag230Plus6, buffer);
[error] /* 0390 */       return parsedLightTypeTag230Plus6;
[error] /* 0391 */   }
[error] /* 0392 */
[error] /* 0393 */ }
[error]         at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:133)
[error]         at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:73)
[error]         at org.apache.fury.codegen.CodeGenerator.compile(CodeGenerator.java:145)
[error]         at org.apache.fury.builder.CodecUtils.loadOrGenCodecClass(CodecUtils.java:110)
[error]         at org.apache.fury.builder.CodecUtils.loadOrGenObjectCodecClass(CodecUtils.java:43)
[error]         at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:49)
[error]         at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$2(ClassResolver.java:985)
[error]         at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
[error]         at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:983)
[error]         at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
[error]         at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
[error]         at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
[error]         at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
[error]         at org.apache.fury.Fury.write(Fury.java:345)
[error]         at org.apache.fury.Fury.serialize(Fury.java:269)
[error]         at org.apache.fury.Fury.serialize(Fury.java:223)
[error]         at test2.Main5$.main(Main5.scala:25)
[error]         at test2.Main5.main(Main5.scala)
[error]         at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
[error]         at java.base/java.lang.reflect.Method.invoke(Method.java:580)
[error] Caused by: org.apache.fury.shaded.org.codehaus.commons.compiler.CompileException: File 'izumi/reflect/macrortti/LightTypeTag_ParsedLightTypeTag230PlusFuryRefCodec_1_1093770338_1107226555.java', Line 68, Column 67: ')' expected instead of '.0'
[error]         at org.apache.fury.shaded.org.codehaus.janino.TokenStreamImpl.compileException(TokenStreamImpl.java:362)
[error]         at org.apache.fury.shaded.org.codehaus.janino.TokenStreamImpl.read(TokenStreamImpl.java:149)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.read(Parser.java:3802)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseArguments(Parser.java:3662)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parsePrimary(Parser.java:3323)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseUnaryExpression(Parser.java:3124)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseMultiplicativeExpression(Parser.java:3083)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseAdditiveExpression(Parser.java:3062)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseShiftExpression(Parser.java:3041)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseRelationalExpression(Parser.java:2936)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseEqualityExpression(Parser.java:2910)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseAndExpression(Parser.java:2889)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseExclusiveOrExpression(Parser.java:2868)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseInclusiveOrExpression(Parser.java:2847)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseConditionalAndExpression(Parser.java:2826)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseConditionalOrExpression(Parser.java:2805)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseConditionalExpression(Parser.java:2786)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseAssignmentExpression(Parser.java:2767)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseExpressionOrType(Parser.java:2748)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseBlockStatement(Parser.java:1926)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseBlockStatements(Parser.java:1858)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseMethodDeclarationRest(Parser.java:1565)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseClassBodyDeclaration(Parser.java:942)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseClassBody(Parser.java:856)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseClassDeclarationRest(Parser.java:746)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parsePackageMemberTypeDeclarationRest(Parser.java:492)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Parser.parseAbstractCompilationUnit(Parser.java:267)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Compiler.parseAbstractCompilationUnit(Compiler.java:316)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Compiler.compile2(Compiler.java:236)
[error]         at org.apache.fury.shaded.org.codehaus.janino.Compiler.compile(Compiler.java:213)
[error]         at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:115)
[error]         at org.apache.fury.codegen.JaninoUtils.toBytecode(JaninoUtils.java:73)
[error]         at org.apache.fury.codegen.CodeGenerator.compile(CodeGenerator.java:145)
[error]         at org.apache.fury.builder.CodecUtils.loadOrGenCodecClass(CodecUtils.java:110)
[error]         at org.apache.fury.builder.CodecUtils.loadOrGenObjectCodecClass(CodecUtils.java:43)
[error]         at org.apache.fury.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:49)
[error]         at org.apache.fury.resolver.ClassResolver.lambda$getObjectSerializerClass$2(ClassResolver.java:985)
[error]         at org.apache.fury.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
[error]         at org.apache.fury.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:983)
[error]         at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:916)
[error]         at org.apache.fury.resolver.ClassResolver.getSerializerClass(ClassResolver.java:808)
[error]         at org.apache.fury.resolver.ClassResolver.createSerializer(ClassResolver.java:1202)
[error]         at org.apache.fury.resolver.ClassResolver.getOrUpdateClassInfo(ClassResolver.java:1141)
[error]         at org.apache.fury.Fury.write(Fury.java:345)
[error]         at org.apache.fury.Fury.serialize(Fury.java:269)
[error]         at org.apache.fury.Fury.serialize(Fury.java:223)
[error]         at test2.Main5$.main(Main5.scala:25)
[error]         at test2.Main5.main(Main5.scala)
[error]         at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
[error]         at java.base/java.lang.reflect.Method.invoke(Method.java:580)
[error] stack trace is suppressed; run last Compile / run for the full output
[error] (Compile / run) Create sequential serializer failed,
[error] class: class izumi.reflect.macrortti.LightTypeTag$ParsedLightTypeTag230Plus
[error] Total time: 6 s, completed Sep 7, 2024, 3:24:49 AM

 */
