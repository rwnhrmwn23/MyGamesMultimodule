@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt") version "1.8.0"
}

android {
    namespace = "com.onedev.mygamesmultimodule"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.onedev.mygamesmultimodule"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    implementation(project(":core"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.core.testing)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.inline)
    androidTestImplementation(libs.core.testing)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // Lifecycle
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Coil
    implementation(libs.coil)

    // Skeleton
    implementation(libs.skeleton)

    // Read More Text
    implementation(libs.readmore.text)

    // Room components
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.kapt)
    androidTestImplementation(libs.room.test)
}