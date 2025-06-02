package ru.dmitryskor.receipt_ofd_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import ru.dmitryskor.receipt_ofd_android.ui.root.RootContent
import ru.dmitryskor.receipt_ofd_android.ui.theme.Receipt_ofd_androidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val rootComponent = (application as ReceiptOFDApp).appComponent
            .rootComponentFactory(defaultComponentContext())


        setContent {
            Receipt_ofd_androidTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars),
                ) {
                    RootContent(component = rootComponent, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
