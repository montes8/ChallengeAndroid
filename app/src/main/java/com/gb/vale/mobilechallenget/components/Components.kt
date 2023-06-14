package com.gb.vale.mobilechallenget.components

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation


@Composable
fun CircleAvatar(image: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .transformations(CircleCropTransformation())
            .build(),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ProgressDialog(
) {
    Dialog(
        onDismissRequest = { },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .background(Color.Transparent)
                .wrapContentSize(),
            color = Color.Magenta
        )

    }

}


@Composable
fun DialogGeneric(
    onClickButton: ((Boolean) -> Unit)? = null
) {
    Dialog(
        onDismissRequest = {
            onClickButton?.invoke(false)
        },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {

        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {


                Text(
                    text = "Lorem Ipsum is simply dummy text",
                    modifier = Modifier.padding(8.dp), fontSize = 20.sp
                )

                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard",
                    modifier = Modifier.padding(8.dp)
                )

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onClickButton?.invoke(false) },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Cancel")
                    }


                    Button(
                        onClick = { onClickButton?.invoke(true) },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = "Exit")
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogGeneric(
    title: String? = null,
    text: String = "error general",
    showDialog: Boolean,
    confirmButton: String = "OK",
    dismissButton: String? = null,
    onConfirmButton: (() -> Unit)? = null,
    onDismissButton: (() -> Unit)? = null,
    dismissDialog: () -> Unit
) {

    if (showDialog) {
        AlertDialog(onDismissRequest = { dismissDialog() },
            title = title?.let {
                @Composable { Text(text = title) }
            },
            text = {
                Text(
                    text = text, style = MaterialTheme.typography.body1.copy(
                        lineHeight = 25.sp
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmButton?.invoke()
                        dismissDialog()
                    }
                ) {
                    Text(confirmButton, fontWeight = FontWeight.Medium)
                }
            },
            dismissButton = {
                dismissButton?.let {
                    TextButton(
                        onClick = {
                            onDismissButton?.invoke()
                            dismissDialog()
                        }
                    ) {
                        Text(dismissButton)
                    }
                }
            }
        )
    }
}

fun Context.toast( value: String) =
    Toast.makeText(this,value, Toast.LENGTH_SHORT).show()