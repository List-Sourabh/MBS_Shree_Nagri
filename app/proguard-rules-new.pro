-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
public <init>(android.content.Context);
public <init>(android.content.Context, android.util.AttributeSet);
public <init>(android.content.Context, android.util.AttributeSet, int);
public void set*(...);
}

-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet);
 }

 -keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet, int);
 }

-keepclassmembers class * extends android.content.Context {
 public void *(android.view.View);
 public void *(android.view.MenuItem);
 }
-keep class org.xmlpull.v1.** { *;}
 -dontwarn org.xmlpull.v1.**
