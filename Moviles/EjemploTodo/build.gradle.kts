// Fichero raíz: solo declara los plugins (apply false). Las versiones salen de gradle/libs.versions.toml
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.kotlinCompose) apply false
}
