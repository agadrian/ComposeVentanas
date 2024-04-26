import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*


fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState(width = 900.dp, height = 600.dp)
    val secondWindowState = rememberWindowState(width = 500.dp, height = 300.dp)
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if (showMainWindow) {
        MainWindow(
            icon = icon,
            windowSize = mainWindowState,
            onClose = {
                showMainWindow = false
            },
            onButtonClick = {
                showSecondWindow = true
                showMainWindow = false
            }
        )
    }

    if (showSecondWindow) {
        SecondaryWindow(
            icon = icon,
            windowSize = secondWindowState,
            onClose = { showSecondWindow = false },
            onButtonClick = {
                showSecondWindow = false
                showMainWindow = true
            }

        )
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}



@Composable
fun MainWindow(
    icon: BitmapPainter,
    windowSize: WindowState,
    onClose: () -> Unit,
    onButtonClick: () -> Unit
){
    Window(
        onCloseRequest = onClose,
        title = "Ventana Principal",
        icon = icon,
        state = windowSize
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                "Esta es la ventana principal.",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(onClick = onButtonClick) {
                Text("Abrir Ventana Secundaria y cerrar esta")
            }
        }
    }
}

@Composable
fun SecondaryWindow(
    icon: BitmapPainter,
    windowSize: WindowState,
    onClose: () -> Unit,
    onButtonClick: () -> Unit
) {
    Window(
        onCloseRequest = onClose,
        title = "Ventana Secundaria",
        icon = icon,
        state = windowSize
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Esta es la ventana secundaria.",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(onClick = onButtonClick) {

                Text("Abrir ventana principal y cerrar esta")
            }
        }
    }
}
