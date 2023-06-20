package stepic.imgresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.awt.FileDialog
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun main() = application {
    var openUploadWindow by remember { mutableStateOf(false) }
    val model = remember { ViewModel() }
    val ctx = rememberCoroutineScope()
    Window(
        onCloseRequest = ::exitApplication,
    ) {
        MaterialTheme {
            MainLayout(model) {
                openUploadWindow = true
            }
        }
        if (openUploadWindow) {
            FileDialog("Choose image") {
                if (it != null) {
                    model.image(it)
                }
                ctx.launch {
                    delay(50)
                    openUploadWindow = false
                }
            }
        }
    }
//    // FilePicker lib to use native dialog
//    FilePicker(openUploadWindow) {
//        openUploadWindow = false
//        if (it != null) {
//            model.image(Paths.get(it.path))
//        }
//    }
}


@Composable
fun MainLayout(
    model: ViewModel,
    onImageUploadClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.weight(1.0F)
        ) {
            if (model.image != null) {
                Image(
                    painter = BitmapPainter(Files.newInputStream(model.image).use { loadImageBitmap(it) }),
                    contentDescription = model.image?.fileName?.toString(),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onImageUploadClick()
            }
        ) {
            Text("Get image")
        }
    }
}

@Composable
fun FrameWindowScope.FileDialog(
    title: String,
    onResult: (result: Path?) -> Unit
) = AwtWindow(
    create = {
        val isLoad = true
        object : FileDialog(window, "Choose a file", if (isLoad) LOAD else SAVE) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    if (file != null) {
                        onResult(File(directory).resolve(file).toPath())
                    } else {
                        onResult(null)
                    }
                }
            }
        }.apply {
            this.title = title
            this.isMultipleMode = false
        }
    },
    dispose = FileDialog::dispose
)

class ViewModel {

    private val _image = mutableStateOf<Path?>(null)
    val image by _image

    fun image(image: Path) {
        _image.value = image
    }

}
