// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false // Actualizada versión (opcional)
    alias(libs.plugins.kotlin.compose.compiler) apply false

    // 1. Plugin de Google Services
    id("com.google.gms.google-services") version "4.4.1" apply false // Usa una versión reciente
}

// 2. Eliminado el 'buildscript' y 'allprojects' legacy. 
// Las dependencias de plugins se manejan arriba.
// Los repositorios se configuran en settings.gradle (moderno) o se heredan.