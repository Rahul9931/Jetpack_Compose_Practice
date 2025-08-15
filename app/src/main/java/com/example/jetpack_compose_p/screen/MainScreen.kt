package com.example.jetpack_compose_p.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_p.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onQrScannerClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                onSearchClick = onSearchClick,
                onQrScannerClick = onQrScannerClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Main Screen Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onSearchClick: () -> Unit,
    onQrScannerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("My App") },
        actions = {

            ToolbarButton(imageName = "qr_code_scanner", onClick = onQrScannerClick)
            ToolbarButton(imageName = "search", onClick = onSearchClick)
            IconButton(onClick = onQrScannerClick) {
                Icon(
                    imageVector = Icons.Default.QrCode,
                    contentDescription = "Scan QR Code"
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        },
        modifier = modifier
    )
}

@Composable
private fun ToolbarButton(imageName: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        elevation = null,
        modifier = Modifier.padding(0.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(
            text = imageName,
            fontFamily = FontFamily(Font(R.font.material_symbols)),
            fontSize = 24.sp, // adjust size as needed
            style = TextStyle(fontFeatureSettings = "liga"),
            modifier = Modifier.padding(0.dp)
        )
    }
}
