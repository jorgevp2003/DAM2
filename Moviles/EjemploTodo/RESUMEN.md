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
| — | 2º índice: un Box por tema (sin lista) | `MainBoxActivity.kt` | (extra) |

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

Box clickable (botón hecho a mano, ver `MainBoxActivity.kt`):
```kotlin
Box(
    modifier = Modifier.weight(1f).height(110.dp)
        .clip(RoundedCornerShape(16.dp))     // 1º recortar
        .background(Color(0xFF4FC3F7))       // 2º color
        .clickable { context.startActivity(Intent(context, OtraActivity::class.java)) }
        .padding(8.dp),                      // al final, si no el color no llega al borde
    contentAlignment = Alignment.Center
) { Text("Ir", color = Color.White) }
```

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

## 7. ListView simple
**Piezas:** `ListView` (la vista) + **Adapter** (convierte cada dato en una fila) + la lista de datos.
```kotlin
class ListViewSimpleActivity : Activity() {
    private val datos = mutableListOf("Ejemplo 1", "Ejemplo 2", "Ejemplo 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lista = ListView(this)                     // o findViewById<ListView>(R.id.lista)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
        lista.adapter = adaptador
        setContentView(lista)

        // Click corto
        lista.setOnItemClickListener { _, _, posicion, _ ->
            Toast.makeText(this, "Seleccionado: ${datos[posicion]}", Toast.LENGTH_SHORT).show()
        }
        // Click largo -> borrar (devuelve true = evento consumido)
        lista.setOnItemLongClickListener { _, _, posicion, _ ->
            datos.removeAt(posicion)
            adaptador.notifyDataSetChanged()           // ¡SIEMPRE tras cambiar la lista!
            true
        }
    }
}
```
- `android.R.layout.simple_list_item_1` = fila del sistema con **un** TextView.
- Añadir: `datos.add("Nuevo"); adaptador.notifyDataSetChanged()`.
- Lambda del listener: `(parent, view, position, id)` → los que no uses, `_`.
- Si usas XML: `<ListView android:id="@+id/lista" android:layout_width="match_parent" android:layout_height="match_parent"/>`.

## 8. ListView personalizado
**Pasos:** 1) `data class` con los datos · 2) adaptador propio que hereda de `ArrayAdapter<T>` · 3) sobrescribir `getView` para construir la fila · 4) `lista.adapter = MiAdaptador(this, datos)`.
```kotlin
data class Ejemplo(var titulo: String = "", var subtitulo: String = "", var numeroEjemplo: Int = 0)

class MiAdaptadorEjemplo(private val ctx: Context, private val datos: List<Ejemplo>)
    : ArrayAdapter<Ejemplo>(ctx, 0, datos) {               // 0 = no usamos layout del sistema

    private fun dp(v: Int) = (v * ctx.resources.displayMetrics.density).toInt()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val e = datos[position]                            // elemento que se pinta

        val fila = LinearLayout(ctx)                       // imagen | columna de textos
        fila.orientation = LinearLayout.HORIZONTAL
        fila.setPadding(dp(16), dp(8), dp(16), dp(8))

        val foto = ImageView(ctx)
        foto.setImageResource(R.mipmap.ic_launcher)
        fila.addView(foto, LinearLayout.LayoutParams(dp(79), dp(76)))

        val columna = LinearLayout(ctx); columna.orientation = LinearLayout.VERTICAL
        fila.addView(columna)

        val titulo = TextView(ctx); titulo.setTypeface(null, Typeface.BOLD)
        val subtitulo = TextView(ctx)
        val numero = TextView(ctx)
        columna.addView(titulo); columna.addView(subtitulo); columna.addView(numero)

        titulo.text = e.titulo
        subtitulo.text = e.subtitulo
        numero.text = e.numeroEjemplo.toString()          // Int -> String, si no peta
        return fila
    }
}
```
Con layout XML de fila (`res/layout/fila.xml`) en vez de por código:
```kotlin
val fila = convertView ?: LayoutInflater.from(ctx).inflate(R.layout.fila, parent, false)
fila.findViewById<TextView>(R.id.txtTitulo).text = e.titulo
return fila
```
`convertView` = fila reciclada (si no es null, reutilízala → más rápido).

## 9. GridView
Igual que ListView (mismo adaptador), pero en cuadrícula:
```kotlin
val grid = GridView(this)
grid.numColumns = GridView.AUTO_FIT     // o un número fijo: 2
grid.columnWidth = dp(150)
grid.adapter = MiAdaptadorEjemplo(this, datos)
grid.setOnItemClickListener { _, _, pos, _ -> ... }
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
- Contenido debajo de la barra de estado en Android 15 → con `targetSdk = 34` no pasa (edge-to-edge solo es obligatorio con targetSdk 35).
