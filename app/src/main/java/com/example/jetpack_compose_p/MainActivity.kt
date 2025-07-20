package com.example.jetpack_compose_p

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_p.navigation.AppNavigation
import com.example.jetpack_compose_p.ui.theme.Jetpack_Compose_PTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Text(text = "Hello World")
            //Greeting()
            //TextInput()
            Jetpack_Compose_PTheme {
                AppNavigation()
            }

        }
    }
}


@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
private fun PreviewGreeting() {
//    Image(
//        painter = painterResource(R.drawable.ic_launcher_foreground),
//        contentDescription = "Dummy image",
//        colorFilter = ColorFilter.tint(Color.Red),
//        contentScale = ContentScale.Crop
//    )
    Button(onClick = {
        Log.d("check_preview","button clicked")
    }) {
        Text(text = "my button")
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "demo button"
        )
    }
}

@Composable
private fun Greeting() {
//    Image(
//        painter = painterResource(R.drawable.ic_launcher_foreground),
//        contentDescription = "Dummy image",
//        colorFilter = ColorFilter.tint(Color.Red),
//        contentScale = ContentScale.Crop
//    )
//    Button(onClick = {
//        Log.d("check_preview","button clicked")
//    }) {
//        Text(text = "my button")
//        Image(
//            painter = painterResource(R.drawable.ic_launcher_foreground),
//            contentDescription = "demo button"
//        )
//    }
}

@Composable
fun TextInput() {
    val state = remember{ mutableStateOf("") }
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
            Log.d("check_","new value -> ${it}")

        },
        label = { Text(text = "Enter Message") }
    )
}