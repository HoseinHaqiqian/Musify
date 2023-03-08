// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
        maven(Mavens.sharedElement)
    }
    dependencies {
        classpath(Plugins.androidTools)
        classpath(Plugins.kotlin)
        classpath(Plugins.dagger)
    }
}

task("clean") {
    delete {
        rootProject.buildDir
    }
}
