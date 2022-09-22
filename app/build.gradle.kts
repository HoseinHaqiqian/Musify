plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}


android {
    compileSdk = GradleConfigs.compileSdkVersion

    defaultConfig {
        applicationId = GradleConfigs.applicationId
        minSdk = GradleConfigs.minSdkVersion
        targetSdk = GradleConfigs.targetSdkVersion
        versionCode = GradleConfigs.versionCode
        versionName = GradleConfigs.versionName
        multiDexEnabled = true
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha02"
    }
    kapt {
        correctErrorTypes = true
    }
    packagingOptions.resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
}

dependencies {
    implementation(project(":musiclist"))
    implementation(Dependencies.ktx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.multiDex)
    implementation(Dependencies.material)
    implementation(Dependencies.dagger)
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    kapt(Dependencies.daggerCompiler)
    implementation(Dependencies.daggerHiltCompose)

    implementation(Dependencies.composeCompiler)
//    implementation(Dependencies.ktorCore)
//    implementation(Dependencies.ktorAndroid)
//    implementation(Dependencies.ktorOkhttp)
    implementation(Dependencies.compose)
//    implementation(Dependencies.composeTooling)
//    implementation(Dependencies.composeFoundation)
//    implementation(Dependencies.composeLifeCycle)
    implementation(Dependencies.composeMaterial)
//    implementation(Dependencies.composeMaterialIcons)
//    implementation(Dependencies.composeMaterialIconsExtended)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.navigationCompose)


}
kapt {
    correctErrorTypes = true
}