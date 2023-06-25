package stepic.imgresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.ResourceLoader
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.awt.FileDialog
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

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
                    delay(100)
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


@OptIn(ExperimentalComposeUiApi::class)
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
                    painter = painterResource(model.image.toString(), FileSystemResourceLoader),
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

@OptIn(ExperimentalComposeUiApi::class)
object FileSystemResourceLoader : ResourceLoader {
    override fun load(resourcePath: String): InputStream {
        return Files.newInputStream(Paths.get(resourcePath))
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
