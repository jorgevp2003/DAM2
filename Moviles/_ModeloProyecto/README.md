# Modelo de proyecto (versiones y dependencias comunes)

Plantilla Gradle compatible con **todos** los proyectos de `Moviles/` (Compose, vistas XML/AppCompat,
Activity clásicas con ListView/GridView/Menús). La usan `EjemploTodo` y `Examen`, y los dos compilan
(`assembleDebug` y tests OK).

## Versiones
| Pieza | Versión | Antes (proyectos de clase) |
|---|---|---|
| Gradle (wrapper) | 8.9 (ya descargado en `~/.gradle`) | 8.4 / 8.9 |
| AGP | 8.7.3 | 8.3.2 |
| Kotlin | 2.0.21 + plugin `kotlin.plugin.compose` | 1.9.0 y 2.1.20 mezclados |
| Compose BOM | 2024.12.01 | 2023.08.00 / 2024.06.00 |
| compileSdk / targetSdk / minSdk | 35 / 35 / 24 | 34 / 34 / 21-24 |
| Java | 11 | 1.8 |

Por qué había problemas: algunos proyectos tenían Kotlin 2.1.20 con `composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }`
(eso solo vale para Kotlin 1.9.0). Además GridViewPersonalizado y MyApplication pedían `core-ktx 1.19.1` y `activity-compose 1.13.0`,
que necesitan compileSdk/AGP más nuevos que los configurados.

## Ficheros
```
gradle/libs.versions.toml         <- catálogo de versiones (lo importante)
gradle/wrapper/                   <- Gradle 8.9
build.gradle.kts                  <- plugins (apply false)
settings.gradle.kts               <- repos + nombre del proyecto
gradle.properties
app/build.gradle.kts              <- android { } + dependencias (Compose + XML + tests)
```

## Crear un proyecto nuevo a partir del modelo
1. Copiar la carpeta `_ModeloProyecto` y renombrarla.
2. `settings.gradle.kts` → `rootProject.name`.
3. `app/build.gradle.kts` → `namespace` y `applicationId`.
4. Crear `app/src/main/AndroidManifest.xml`, `java/...` y `res/` (o copiar los de `Examen`).

## Pasar un proyecto antiguo al modelo
1. Sustituir `gradle/libs.versions.toml` y `gradle/wrapper/gradle-wrapper.properties` por los de aquí.
2. En el `build.gradle.kts` raíz añadir `alias(libs.plugins.kotlinCompose) apply false`.
3. En `app/build.gradle.kts`:
   - añadir `alias(libs.plugins.kotlinCompose)` en `plugins { }`
   - **quitar** el bloque `composeOptions { ... }`
   - `compileSdk = 35`, `targetSdk = 35`, Java/jvmTarget 11
   - en el catálogo, `lifecycleRuntimeKtx` / `activityCompose` pasan a llamarse `lifecycle` / `activity`
     (los alias de las librerías son los mismos: `libs.androidx.lifecycle.runtime.ktx`, `libs.androidx.activity.compose`...)
4. Proyectos con `Kotlin_Prueba1/app/build.gradle` (Groovy): usar `app/build.gradle.kts` de aquí.
5. Si el contenido se mete bajo la barra de estado en Android 15: copiar `res/values-v35/themes.xml` de `Examen`
   (cambiando el nombre del tema).
