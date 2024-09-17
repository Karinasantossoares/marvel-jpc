plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.project.desafio_jpc.commom"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    //compose
    api(libs.koin.androidx.compose)
    api(libs.coil.compose)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.compiler)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.navigation.compose)


    //defaults
    api(libs.material)
    api(libs.androidx.appcompat)
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.material3)

    //Coroutines
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)


    //Retrofit
    api(libs.retrofit)
    api(libs.logging.interceptor)
    api(libs.converter.gson)

    //canary
    debugImplementation (libs.leakcanary.android)
    releaseImplementation (libs.leakcanary.android.no.op)


}