plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    // 1. Aplicar el plugin de Google Services
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.exampleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.exampleapp"
        minSdk = 26
        targetSdk = 34
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

    // 2. Unificar las compileOptions
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Firebase es compatible con 1.8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8" // Firebase es compatible con 1.8
    }

    buildFeatures {
        compose = true
    }
    composeOptions { // 3. Corregido el nombre de 'composeCompiler' a 'composeOptions'
        kotlinCompilerExtensionVersion = "1.5.8" // Asegúrate que esta versión sea compatible con tu Kotlin
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // 4. Dependencias de Firebase (BOM - Bill of Materials)
    // El BoM maneja las versiones de las librerías de Firebase por ti.
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth-ktx") // Autenticación
    implementation("com.google.firebase:firebase-firestore-ktx") // Firestore (si lo usas)

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00") // BOM de Compose
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.navigation:navigation-compose:2.7.7") // Versión estable
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
    implementation("androidx.compose.material:material-icons-extended")

    // Coil (Imágenes)
    implementation(libs.coil.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // 5. Eliminadas las dependencias duplicadas de Firebase y Compose
}