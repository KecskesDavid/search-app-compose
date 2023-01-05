package com.example.searchappcompose.app.core.ui.button

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchappcompose.app.theme.SearchAppComposeTheme

@Composable
fun PrimaryButton(
    text: String, onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = Modifier
            .width(120.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    SearchAppComposeTheme {
        PrimaryButton(text = "Button text", onClick = {})
    }
}