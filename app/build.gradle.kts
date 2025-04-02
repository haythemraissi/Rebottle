plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services") version "4.4.2" apply true
}

android {
    namespace = "com.example.rebottle"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rebottle"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}



dependencies {
    implementation ("androidx.fragment:fragment-ktx:1.5.5")
    implementation ("androidx.recyclerview:recyclerview:1.3.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4") // Pour await()

    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.7.2")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // QR Scanner
    implementation("com.journeyapps:zxing-android-embedded:4.3.0") {
        exclude(group = "com.android.support")
    }
    implementation("com.google.zxing:core:3.5.2")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}