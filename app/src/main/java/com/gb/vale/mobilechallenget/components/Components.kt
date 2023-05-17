package com.gb.vale.mobilechallenget.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.gb.vale.mobilechallenget.R

@Composable
fun CircleAvatar(modifier: Modifier = Modifier.size(50.dp), image: String?) {
    Image(
        modifier = modifier
            .clip(CircleShape)
            .clipToBounds()
            .background(colorResource(id = R.color.gray_300)),
        painter = image?.let { rememberImagePainter(data = image) } ?: run {
            painterResource(R.drawable.bg_round_default)
        },
        contentScale = image?.let { ContentScale.Crop } ?: ContentScale.Inside,
        contentDescription = null,
        colorFilter = if (image == null) ColorFilter.tint(color = Color.White) else null
    )
}