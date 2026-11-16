# RESUMEN DE TODO — Móviles (DAM2)

Cada apartado tiene su Activity en `app/src/main/java/com/example/ejemplotodo/`.
La app abre un índice (MainActivity) desde el que se entra a cada tema.

| # | Tema | Fichero | Viene del proyecto |
|---|------|---------|--------------------|
| 1 | Ciclo de vida | `CicloVidaActivity.kt` | CicloDeVida, 2Activity |
| 2 | Layouts Compose | `LayoutsActivity.kt` | Layout, Layout1, Layout12 |
| 3 | Estado + Calculadora | `EstadoActivity.kt` | KotlinConJetpack, MyApplication |
| 4 | Intents explícitos | `IntentsExplicitosActivity.kt` + `DestinoActivity.kt` | 2Activity |
| 5 | Intents implícitos + permisos | `IntentsImplicitosActivity.kt` | Alarma |
| 6 | Vistas XML | `VistasXmlActivity.kt` + `res/layout/activity_vistas_xml.xml` | Kotlin_Prueba1, CicloDeVida |
| 7 | ListView simple | `ListViewSimpleActivity.kt` | ListViewSimple |
| 8 | ListView personalizado | `ListViewPersonalizadoActivity.kt` + `Ejemplo.kt` | ListViewPersonalizado |
| 9 | GridView personalizado | `GridViewActivity.kt` | GridViewPersonalizado |
| 10 | Menú de opciones | `MenuOpcionesActivity.kt` + `res/menu/menu_opciones.xml` | Menus |
| 11 | Menú contextual | `MenuContextualActivity.kt` | Menu2 |
| 12 | Lo mismo en Compose | `ComposeAvanzadoActivity.kt` | (extra) |

---

## 0. Estructura de un proyecto
```
app/build.gradle.kts         -> plugins, compileSdk, dependencias
gradle/libs.versions.toml    -> VERSIONES (catálogo)  libs.androidx.xxx
app/src/main/AndroidManifest.xml -> Activities + permisos
app/src/main/java/...        -> código Kotlin
app/src/main/res/            -> layout/ values/strings.xml drawable/ menu/ mipmap/
```
**Toda Activity nueva se declara en el Manifest:**
```xml
<activity android:name=".OtraActivity" android:exported="false" />
```
La principal lleva `exported="true"` + `intent-filter` MAIN / LAUNCHER.

## 1. Ciclo de vida
`onCreate → onStart → onResume → (app en uso) → onPause → onStop → onDestroy`
- Volver desde Home: `onRestart → onStart → onResume`
- **Girar la pantalla destruye y recrea la Activity** (se pierde lo que no esté en `rememberSaveable`/ViewModel)
```kotlin
override fun onStart() { super.onStart(); Log.d("CICLO", "onStart") }
```

## 2. Compose: layouts
```kotlin
Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) { ... }
Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) { ... }
Box(contentAlignment = Alignment.Center) { Text("x", Modifier.align(Alignment.BottomEnd)) }
Modifier.weight(1f)      // reparte espacio (solo dentro de Row/Column)
Modifier.fillMaxWidth().height(60.dp).padding(8.dp).background(Color.Green).border(2.dp, Color.Black, RoundedCornerShape(10.dp))
```
- Arrangement = eje principal · Alignment = eje cruzado
- El orden de los Modifier importa.
- Scroll: `Modifier.verticalScroll(rememberScrollState())`

## 3. Compose: estado
```kotlin
var texto by remember { mutableStateOf("") }          // imports getValue / setValue
var n by rememberSaveable { mutableIntStateOf(0) }     // sobrevive a girar
OutlinedTextField(value = texto, onValueChange = { texto = it }, label = { Text("Nombre") })
Button(onClick = { n++ }) { Text("Sumar") }
Image(painterResource(R.drawable.logo), contentDescription = null, Modifier.size(100.dp))
Icon(Icons.Default.Star, contentDescription = null)
```
Composable reutilizable con lambda: `fun Boton(texto: String, onClick: () -> Unit)`

## 4. Intents explícitos
```kotlin
// IR
val i = Intent(this, DestinoActivity::class.java)
i.putExtra("nombre", "Jorge"); i.putExtra("numero", 1)
startActivity(i)
// RECIBIR (en DestinoActivity.onCreate)
val nombre = intent.getStringExtra("nombre")
val numero = intent.getIntExtra("numero", 0)
// CON RESULTADO
private val lanzador = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { r ->
    if (r.resultCode == RESULT_OK) r.data?.getStringExtra("respuesta")
}
lanzador.launch(i)
// en el destino:
setResult(RESULT_OK, Intent().putExtra("respuesta", "ok")); finish()
```
Dentro de `setContent { }` el contexto es `this@MiActivity` (o `LocalContext.current` en un @Composable).

## 5. Intents implícitos y permisos
| Acción | Código | Permiso |
|---|---|---|
| Alarma | `Intent(AlarmClock.ACTION_SET_ALARM)` + `EXTRA_HOUR`, `EXTRA_MINUTES`, `EXTRA_MESSAGE` | `SET_ALARM` (normal) |
| Llamar | `Intent(Intent.ACTION_CALL, Uri.parse("tel:600..."))` | `CALL_PHONE` (**peligroso**) |
| Marcar | `Intent(Intent.ACTION_DIAL, Uri.parse("tel:600..."))` | — |
| Web | `Intent(Intent.ACTION_VIEW, Uri.parse("https://..."))` | — |
| Mapa | `Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.4,-3.7?q=Sol"))` | — |
| Compartir | `Intent(ACTION_SEND).apply{ type="text/plain"; putExtra(EXTRA_TEXT,"..") }` + `Intent.createChooser` | — |
| Email | `Intent(ACTION_SENDTO, Uri.parse("mailto:a@b.com"))` | — |

Permiso peligroso en tiempo de ejecución:
```kotlin
private val pedir = registerForActivityResult(ActivityResultContracts.RequestPermission()) { ok -> if (ok) llamar() }
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) llamar()
else pedir.launch(Manifest.permission.CALL_PHONE)
```
Manifest: `<uses-permission android:name="android.permission.CALL_PHONE" />`

## 6. Vistas XML
```kotlin
class VistasXmlActivity : AppCompatActivity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        setContentView(R.layout.activity_vistas_xml)
        val btn = findViewById<Button>(R.id.btnSaludar)
        btn.setOnClickListener { ... }
    }
}
```
- ConstraintLayout: `app:layout_constraintTop_toBottomOf="@id/otra"`, `..Start_toStartOf="parent"`, `layout_width="0dp"` = ocupar hasta las restricciones.
- Textos en `res/values/strings.xml` → `getString(R.string.saludo, nombre)` con `%1$s`.

## 7-9. ListView / GridView
```kotlin
// Simple
lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
lista.setOnItemClickListener { _, _, pos, _ -> ... }
adaptador.notifyDataSetChanged()   // tras cambiar la lista

// Personalizado
class MiAdaptador(private val ctx: Context, private val datos: List<Ejemplo>)
    : ArrayAdapter<Ejemplo>(ctx, 0, datos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val e = datos[position]
        val fila = LinearLayout(ctx) ; /* añadir ImageView, TextView... */
        return fila
    }
}
// GridView
grid.numColumns = GridView.AUTO_FIT; grid.columnWidth = dp(150)
```
Interfaz por código: `LinearLayout(this).apply{ orientation = VERTICAL }`, `addView(vista, LayoutParams(MATCH_PARENT, WRAP_CONTENT))`, `setContentView(raiz)`.

## 10. Menú de opciones
```kotlin
companion object { const val OPCION_1 = 1 }
override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menu.add(0, OPCION_1, 0, "Opción 1")
    menu.addSubMenu("Más").add(0, 10, 0, "Sub")
    menuInflater.inflate(R.menu.menu_opciones, menu)   // o desde XML
    return true
}
override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    OPCION_1 -> { Toast.makeText(this, "1", Toast.LENGTH_SHORT).show(); true }
    else -> super.onOptionsItemSelected(item)
}
```
Con `android.app.Activity` y tema NoActionBar: `val tb = Toolbar(this); setActionBar(tb)`.

## 11. Menú contextual (mantener pulsado)
```kotlin
registerForContextMenu(lista)
override fun onCreateContextMenu(menu: ContextMenu, v: View, info: ContextMenu.ContextMenuInfo?) {
    val pos = (info as AdapterView.AdapterContextMenuInfo).position
    menu.setHeaderTitle(datos[pos]); menu.add(Menu.NONE, ID, Menu.NONE, "Texto")
}
override fun onContextItemSelected(item: MenuItem): Boolean { ... }
```

## 12. Equivalentes en Compose
| Clásico | Compose |
|---|---|
| ListView | `LazyColumn { items(lista) { e -> Fila(e) } }` |
| GridView | `LazyVerticalGrid(GridCells.Adaptive(150.dp)) { items(lista) { ... } }` |
| `notifyDataSetChanged` | lista `mutableStateListOf()` / `toMutableStateList()` → automático |
| Menú opciones | `TopAppBar(actions = { IconButton{..}; DropdownMenu(expanded, onDismissRequest){ DropdownMenuItem(..) } })` |
| Menú contextual | `Modifier.combinedClickable(onClick, onLongClick)` + `DropdownMenu` |
| Toast | `Toast.makeText(LocalContext.current, "..", Toast.LENGTH_SHORT).show()` |
| Snackbar | `Scaffold(snackbarHost = { SnackbarHost(state) })` + `scope.launch { state.showSnackbar("..") }` |
| AlertDialog | `if (mostrar) AlertDialog(onDismissRequest, confirmButton, dismissButton, title, text)` |

## Errores típicos
- *ActivityNotFoundException: Unable to find explicit activity* → falta en el Manifest.
- *SecurityException* al llamar → falta pedir el permiso en ejecución.
- `by remember` en rojo → faltan imports `getValue` y `setValue`.
- `TopAppBar` en rojo → `@OptIn(ExperimentalMaterial3Api::class)`.
- Kotlin 2.x + Compose → plugin `kotlinCompose` (no `composeOptions`).
- Contenido debajo de la barra de estado en Android 15 → `values-v35/themes.xml` con `windowOptOutEdgeToEdgeEnforcement` (ya incluido).
