# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

-keepattributes Signature
-keepattributes InnerClasses
-keepattributes InnerClasses,EnclosingMethod
-keepattributes EnclosingMethod

-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-dontnote **ILicensingService

#-keep class com.google.**
-keep class com.android.**

# Preserve all fundamental application classes.
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.view.View
-keep public class * extends android.preference.Preference
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Preserve ActionBarSherlock and Android support libraries` classes and interfaces
-keep class android.support.** { *; }
-keep interface android.support.** { *; }
-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }

# Original
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# No debugging : remove debug logs:
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# For annotations use
-keepattributes *Annotation*

# For crashreporting utility
-keepattributes SourceFile,LineNumberTable

# For custom exceptions
-keep public class * extends java.lang.Exception

# http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
-keep class android.support.v7.widget.RoundRectDrawable { *; }

#android support-v7
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

#android support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

#androidx
-dontwarn com.google.android.material.**
-keep class com.google.android.material.** { *; }

-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# Retrofit 2.X
## https://square.github.io/retrofit/ ##
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#OkHttp
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keepattributes Signature
-keepattributes Annotation
-keep class okhttp3.** { *; }
-keep class okio** { *; }
-keep interface okio** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-keep interface com.google.gson.** { *; }
-dontwarn com.google.gson.**
-dontwarn okio.**

-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**
-keep class com.google.gson.** { *; }
-keep class com.wdullaer.** { *; }
-keep class com.journeyapps.** { *; }
-dontwarn com.journeyapps.**
-keep class com.google.zxing.** { *; }
-keep public class com.google.zxing.** { *; }
-dontwarn com.zxing.**

-keep public class com.google.protobuf.* { public *; }
-dontwarn com.google.protobuf.**

-dontwarn com.google.android.gms.**
-keep class com.google.android.** { *; }
-keepclasseswithmembers class * {
    @com.google.android.gms.* <methods>;
}

# Apache
-dontwarn org.apache.**
-keep class org.apache.** { *; }
-keepclasseswithmembers class * {
    @org.apache.* <methods>;
}

# SimpleFramework
-dontwarn org.simpleframework.**
-keep class org.simpleframework.** { *; }
-keepclasseswithmembers class * {
    @org.simpleframework.* <methods>;
}
-dontwarn javax.xml.**
-keep class javax.xml.** { *; }
-keepclasseswithmembers class * {
    @javax.xml.* <methods>;
}

# coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keep public class com.google.android.gms.ads.** {
    public *;
}

-keep public class com.google.ads.** {
    public *;
}

-ignorewarnings