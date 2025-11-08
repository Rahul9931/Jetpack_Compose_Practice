package com.example.jetpack_compose_p

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_p.navigation.AppNavigation
import com.example.jetpack_compose_p.ui.theme.Jetpack_Compose_PTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_Compose_PTheme {
                //AppNavigation()
                //greetings("")
                BasicEditText()
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
private fun PreviewFunction() {
    //greetings("rahul")
    BasicEditText()
}

@Composable
private fun BasicEditText(){
    val text1 = remember { mutableStateOf("") }
    val text2 = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var isPasswordVisible = remember { mutableStateOf(false) }
    var isShowError = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .systemBarsPadding()
    ) {
        TextField(
            value = text1.value,
            onValueChange = { text1.value = it },
            label = { Text(text = "Enter Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            trailingIcon = {Icon(Icons.Default.Check, contentDescription = null)}

        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = text2.value,
            onValueChange = {

                text2.value = it
                if(text2.value.length > 0){
                    isShowError.value = false
                }
                            },
            label = { Text(text = "Enter Name") },
            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.Green,
//                unfocusedContainerColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Magenta,
                unfocusedIndicatorColor = Color.Cyan,
            ),
            isError = isShowError.value
        )

        if (isShowError.value){
            Text(text="This field is required", color = Color.Red)
        }

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Enter Password")},
            visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                var image = if (isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = {isPasswordVisible.value = !isPasswordVisible.value}) {
                    Icon(image, contentDescription = null)
                }
            }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedButton(onClick = {
            if (text2.value.isBlank()){
                isShowError.value = true
            }
        }) {
            Text(text="Submit")
        }

    }

}


