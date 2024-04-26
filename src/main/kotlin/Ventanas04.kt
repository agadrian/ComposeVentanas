import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState



fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState()
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if (showMainWindow) {
        Window(
            onCloseRequest = { showMainWindow = false },
            title = "Ventana Principal",
            icon = icon,
            state = mainWindowState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(
                    "Esta es la ventana principal"
                )

                Spacer(modifier = Modifier.height(100.dp))

                Button(onClick = {
                    showMainWindow = false
                    showSecondWindow = true
                }) {
                    Text("Abrir Ventana Secundaria y cerrar esta")
                }
            }




        }
    }

    if (showSecondWindow) {
        SecondaryWindow(onClose = { showSecondWindow = false })
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}

@Composable
@Preview
fun SecondaryWindow(onClose: () -> Unit) {
    val secondaryWindowState = rememberWindowState()
    Window(
        onCloseRequest = onClose,
        title = "Ventana Secundaria",
        state = secondaryWindowState
    ) {
        Text("Este es el contenido de la ventana secundaria.")
    }
}