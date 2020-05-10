import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 23
    const val compileSdk = 28
    const val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8
    const val buildTools = "28.0.3"
}

object Versions {
    const val javaxAnnotationVersion = "1.0"
    const val javaxInjectVersion = "1"

    const val kotlinVersion = "1.3.61"
    const val gradleAndroid = "3.5.0"

    const val junit = "4.12"
    const val androidxEspresso = "3.1.0"
    const val androidxTesting = "1.1.1"

    const val appCompatVersion = "1.1.0"
    const val materialVersion = "1.0.0"
    const val constraintLayoutVersion = "1.1.3"
    const val recyclerViewVersion = "1.1.0"
    const val swipeRefreshVersion = "1.0.0"

    const val roomVersion = "2.2.3"
    const val lifecycleVersion= "2.2.0"

    const val glideVersion = "4.10.0"

    const val retrofitVersion = "2.6.1"
    const val okhttp3Version = "3.12.0"

    const val multilineCollapsingVersion= "27.1.1"

    const val materialLoadingButtonVersion= "0.0.1"

    const val chuckVersion= "1.1.0"

    const val daggerVersion = "2.25.3"
}

object Deps {

    const val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotationVersion}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"

    // androidx
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val androidxSwipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshVersion}"

    // dagger
//    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
//    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
//    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
//    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    // lifecycle
    const val androidxLifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val androidxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val androidxLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"

    // room
    const val androidxRoom = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val androidxRoomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // test
    const val testLibJunit = "junit:junit:${Versions.junit}"
    const val testAndroidxRules = "androidx.test:rules:${Versions.androidxTesting}"
    const val testAndroidxRunner = "androidx.test:runner:${Versions.androidxTesting}"
    const val testAndroidxEspressoCore = "androidx.test.espresso:espresso-core:${Versions.androidxEspresso}"

    // google material
    const val googleMaterial = "com.google.android.material:material:${Versions.materialVersion}"

    // glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    // retrofit
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3Version}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"

    // others
    const val materialLoadingButton = "com.github.anangkur:Material-Loading-Button:${Versions.materialLoadingButtonVersion}"
    const val multilineCollapsingToolbar = "net.opacapp:multiline-collapsingtoolbar:${Versions.multilineCollapsingVersion}"

    // chuck
    const val chuck = "com.readystatesoftware.chuck:library:${Versions.chuckVersion}"
    const val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:${Versions.chuckVersion}"

    // tools
    const val toolsGradleAndroid = "com.android.tools.build:gradle:${Versions.gradleAndroid}"
    const val toolsKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}