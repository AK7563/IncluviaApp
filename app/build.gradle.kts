plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.incluviaapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.incluviaapp"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
            buildConfigField("String", "BASE_URL", "\"https://exampleurl.com\"")
        }
        debug {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
            buildConfigField("String", "BASE_URL", "\"https://aviationweather.gov\"")
        }
        create("dev") {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
            buildConfigField("String", "BASE_URL", "\"https://exampleurl.com\"")
        }
        create("apiTest") {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
            buildConfigField("String", "BASE_URL", "\"https://aviationweather.gov\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}