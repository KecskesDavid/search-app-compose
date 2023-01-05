package com.example.searchappcompose.app.core.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.searchappcompose.R
import com.example.searchappcompose.app.core.ui.button.PrimaryButton
import com.example.searchappcompose.app.core.ui.button.SecondaryButton
import com.example.searchappcompose.app.theme.SearchAppComposeTheme

@Composable
fun AppDialog(
    title: String,
    subTitle: String,
    confirmButtonText: String,
    cancelButtonText: String,
    onConfirmButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit
) {
    Dialog(onDismissRequest = onCancelButtonClick) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16))
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = subTitle,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    SecondaryButton(text = cancelButtonText, onClick = onCancelButtonClick)
                    PrimaryButton(text = confirmButtonText, onClick = onConfirmButtonClick)
                }
            }
        }
    }
}

@Preview
@Composable
fun AppDialogPreview() {
    SearchAppComposeTheme {
        AppDialog(title = stringResource(id = R.string.remove_favorites_dialog_title),
            subTitle = stringResource(id = R.string.remove_favorites_dialog_subtitle),
            confirmButtonText = stringResource(id = R.string.remove_favorites_dialog_confirm_button),
            cancelButtonText = stringResource(id = R.string.remove_favorites_dialog_cancel_button),
            onConfirmButtonClick = {},
            onCancelButtonClick = {})
    }
}