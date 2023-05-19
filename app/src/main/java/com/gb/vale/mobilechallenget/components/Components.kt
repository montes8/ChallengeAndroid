package com.gb.vale.mobilechallenget.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
        modifier  =  Modifier.size(80.dp).clip(CircleShape)
    )
}