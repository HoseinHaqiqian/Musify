object Dependencies {
    val composeCompiler by lazy{"androidx.compose.compiler:compiler:${Versions.composeCompiler}"}
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val dagger by lazy { "com.google.dagger:hilt-android:${Versions.dagger}" }
    val daggerCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.dagger}" }
    val daggerHiltCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.daggerNavCompose}" }

    val ktx by lazy { "androidx.core:core-ktx:${Versions.ktx}" }
    val ktorCore by lazy { "io.ktor:ktor-client-core:${Versions.ktor}" }
    val ktorAndroid by lazy { "io.ktor:ktor-client-core:${Versions.ktor}" }
    val ktorOkhttp by lazy { "io.ktor:ktor-client-okhttp:${Versions.ktor}" }
    val compose by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val composeTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
    val composeFoundation by lazy { "androidx.compose.foundation:foundation:${Versions.compose}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val composeMaterialIcons by lazy { "androidx.compose.material:material-icons-core:${Versions.compose}" }
    val composeMaterialIconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.compose}" }
    val composeLifeCycle by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifeCycle}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigation}" }
    val kotlinCoroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}" }
    val multiDex by lazy { "androidx.multidex:multidex:${Versions.multiDex}" }
    val glide by lazy { "com.github.skydoves:landscapist-glide:${Versions.glide}"}

}