plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.app.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.weatherapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.logging.interceptor)

    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.lottie)

    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    // Retrofit
    implementation(libs.bundles.retrofit)
    //Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // Room
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
}