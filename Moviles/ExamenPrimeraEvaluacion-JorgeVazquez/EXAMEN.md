# EXAMEN — proyecto listo para empezar

Ábrelo en Android Studio (File > Open > carpeta `Examen`), espera al Gradle Sync y dale a Run.
Ya compila. Busca los `// TODO` (en Android Studio: View > Tool Windows > TODO).

## Qué hay ya hecho
| Fichero | Para qué |
|---|---|
| `MainActivity.kt` | Compose: Scaffold + TopAppBar + menú de 3 puntos + botones a las otras pantallas + lanzador de resultado + lanzador de permiso |
| `SegundaActivity.kt` | Compose: recibe extra (`EXTRA_DATO`) y devuelve resultado (`EXTRA_RESPUESTA`) |
| `XmlActivity.kt` + `res/layout/activity_xml.xml` | Pantalla con layout XML (ConstraintLayout + EditText + Button + TextView) |
| `ListaActivity.kt` | Clásica: Toolbar + ListView con adaptador personalizado + menú opciones (código y XML) + menú contextual (ver/borrar) |
| `Elemento.kt` | data class del modelo + datos de prueba |
| `Utils.kt` | `toast("..")`, `dp(16)`, `TAG` para Log |
| `AndroidManifest.xml` | Activities declaradas + permisos comentados listos para descomentar |
| `ui/theme/` | Tema Compose (`ExamenTheme`) |

## Checklist rápida
1. ¿Nueva Activity? → crear clase + **declararla en el Manifest**.
2. ¿Pasar datos? → `putExtra` / `getStringExtra` (claves en `companion object`).
3. ¿Permiso peligroso? → descomentar en Manifest + usar `comprobarPermiso(Manifest.permission.X)` de MainActivity.
4. ¿Lista? → Compose: `LazyColumn` · Clásico: copiar `ListaActivity`.
5. ¿Menú? → Compose: `DropdownMenu` en `TopAppBar` · Clásico: `onCreateOptionsMenu`.
6. ¿Pantalla XML? → copiar `XmlActivity` + su layout.
7. ¿Activity clásica (`android.app.Activity` + `Toolbar`)? → en el Manifest `android:theme="@style/Theme.Examen.Clasico"`.

Chuleta completa: `../EjemploTodo/RESUMEN.md`

## Cambiar nombre / paquete (si lo piden)
- `settings.gradle.kts` → `rootProject.name`
- `app/build.gradle.kts` → `namespace` y `applicationId`
- `res/values/strings.xml` → `app_name`
- Paquete: clic derecho en `com.example.examen` > Refactor > Rename
