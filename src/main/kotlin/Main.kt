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
import androidx.compose.ui.window.*


fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState()
    val secondWindowState = rememberWindowState()
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
@Preview
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
                "Esta es la ventana principal"
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(onClick = onButtonClick) {
                Text("Abrir Ventana Secundaria y cerrar esta")
            }
        }
    }
}

@Composable
@Preview
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
                "Esta es la ventana secundaria."
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(onClick = onButtonClick) {

                Text("Abrir ventana principal y cerrar esta")
            }
        }

    }
}


    /**
     * Window(
     *         onCloseRequest = { showMainWindow = false },
     *         title = "Ventana Principal",
     *         icon = icon,
     *         state = mainWindowState
     *     ) {
     *         Column(
     *             modifier = Modifier
     *                 .fillMaxSize(),
     *             verticalArrangement = Arrangement.Center,
     *             horizontalAlignment = Alignment.CenterHorizontally,
     *
     *             ) {
     *             Text(
     *                 "Esta es la ventana principal"
     *             )
     *
     *             Spacer(modifier = Modifier.height(100.dp))
     *
     *             Button(onClick = {
     *                 showMainWindow = false
     *                 showSecondWindow = true
     *             }) {
     *                 Text("Abrir Ventana Secundaria y cerrar esta")
     *             }
     *         }
     */