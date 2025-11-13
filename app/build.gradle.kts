plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.project.app"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        applicationId = "com.project.app"
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
    applicationVariants.all {
        outputs.all {
            val variant = this
            val appName = "FirstProject"
            val versionName = versionName
            val versionCode = variant.versionCode

            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                "${appName}_v${versionName}_(${versionCode})_${variant.name}.apk"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(project(":core"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose + Lifecycle
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Core concurrency & DI
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Retrofit for network layer
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // Chucker interceptor (debug only)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
}