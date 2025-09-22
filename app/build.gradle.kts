plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.modul2_kel64"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.modul2_kel64"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    dependencies {
        // Core
        implementation("androidx.core:core-ktx:1.15.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
        implementation("androidx.activity:activity-compose:1.9.3")

        // Compose
        implementation(platform("androidx.compose:compose-bom:2024.04.01"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")

        // Navigation
        implementation("androidx.navigation:navigation-runtime-android:2.9.3")
        implementation("androidx.navigation:navigation-compose:2.9.4")

        // Material icons
        implementation("androidx.compose.material:material-icons-extended")

        // Coil (image loading)
        implementation("io.coil-kt:coil-compose:2.4.0")

        // Retrofit + Gson
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")

        // Testing
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.01"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")

        // Debugging tools
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }

}