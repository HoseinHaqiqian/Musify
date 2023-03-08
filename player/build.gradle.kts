plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "hosein.haqiqian.player"
    compileSdk = GradleConfigs.compileSdkVersion

    defaultConfig {
        minSdk = GradleConfigs.minSdkVersion
        targetSdk = GradleConfigs.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    kapt {
        correctErrorTypes = true
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
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(Dependencies.ktx)
    implementation(Dependencies.material)
    implementation(Dependencies.compose)
    implementation(Dependencies.composeCompiler)
    implementation(Dependencies.dagger)
    implementation("androidx.core:core-ktx:+")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.0.0")
    kapt(Dependencies.daggerCompiler)
    implementation(Dependencies.kotlinCoroutine)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeLifeCycle)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeMaterialIcons)
    implementation(Dependencies.composeMaterialIconsExtended)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.glide)
    implementation(Dependencies.coil)
    implementation(Dependencies.sharedElement)
}