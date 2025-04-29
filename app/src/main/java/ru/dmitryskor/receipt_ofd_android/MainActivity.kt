package ru.dmitryskor.receipt_ofd_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import ru.dmitryskor.receipt_ofd_android.ui.navigation.RootComponent
import ru.dmitryskor.receipt_ofd_android.ui.navigation.RootContent
import ru.dmitryskor.receipt_ofd_android.ui.theme.Receipt_ofd_androidTheme

class MainActivity : ComponentActivity() {

    private lateinit var rootComponent: RootComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            rootComponent = RootComponent(
                componentContext = defaultComponentContext(),
                applicationContext = applicationContext
            )

            setContent {
                RootContent(rootComponent)
            }
//            Receipt_ofd_androidTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Receipt_ofd_androidTheme {
        Greeting("Android")
    }
}