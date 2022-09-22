// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.androidTools)
        classpath(Plugins.kotlin)
        classpath(Plugins.dagger)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

task("clean") {
    delete {
        rootProject.buildDir
    }
}
