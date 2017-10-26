# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\asFile\as\studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn com.squareup.okhttp3.**
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
-keep class okhttp3.** { *; }

-keep interface okhttp3.** { *; }

-dontwarn okhttp3.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
  # RxJava RxAndroid
-dontwarn sun.misc.**
-dontwarn freemarker.**
-dontwarn rx.internal.util.unsafe.*
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* { long producerIndex; long consumerIndex; }
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef { rx.internal.util.atomic.LinkedQueueNode producerNode; }
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef { rx.internal.util.atomic.LinkedQueueNode consumerNode; }
# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8

#所有实体类不混淆
-keep class com.grass.grass.entity.** { *; }

#java8 Lambad混淆
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*

#Bugly混淆
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
