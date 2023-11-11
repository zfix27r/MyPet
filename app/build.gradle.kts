plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")

    // Secrets Gradle Plugin to hide API keys
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    val compileSdkVersion: String by System.getProperties()

    namespace = "com.example.mypet.app"
    compileSdk = compileSdkVersion.toInt()

    defaultConfig {
        val minSdkVersion: String by System.getProperties()
        val targetSdkVersion: String by System.getProperties()

        applicationId = "com.example.mypet"
        minSdk = minSdkVersion.toInt()
        targetSdk = targetSdkVersion.toInt()
        versionCode = 1
        versionName = "1.0"

        // enables access to hidden API key
        android.buildFeatures.buildConfig = true
   }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
}

dependencies {
    val hiltVersion: String by System.getProperties()

    val androidxAppCompatVersion: String by System.getProperties()
    val androidxMaterialVersion: String by System.getProperties()
    val androidxConstraintlayoutVersion: String by System.getProperties()
    val androidxLifecycleVersion: String by System.getProperties()
    val androidxNavigationVersion: String by System.getProperties()
    val androidxRoomVersion: String by System.getProperties()

    val viewBindingDelegateVersion: String by System.getProperties()

    val retrofit2Version: String by System.getProperties()

    val glideVersion: String by System.getProperties()

    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    implementation("androidx.appcompat:appcompat:$androidxAppCompatVersion")
    implementation("com.google.android.material:material:$androidxMaterialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$androidxConstraintlayoutVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidxLifecycleVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$androidxNavigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$androidxNavigationVersion")

    implementation("androidx.room:room-runtime:$androidxRoomVersion")
    ksp("androidx.room:room-compiler:$androidxRoomVersion")
    implementation("androidx.room:room-ktx:$androidxRoomVersion")

    implementation("com.squareup.retrofit2:retrofit:$retrofit2Version")
    implementation("com.squareup.retrofit2:converter-simplexml:$retrofit2Version")

    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:$viewBindingDelegateVersion")

    // yandex maps
    implementation("com.yandex.android:maps.mobile:4.4.0-full")
}