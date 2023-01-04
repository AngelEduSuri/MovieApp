package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieTopBar(
    modifier: Modifier = Modifier,
    title: String,
    isGridViewEnabled: Boolean,
    onGenreClick: () -> Unit,
    onViewClick: () -> Unit
) {

    val iconView = if (isGridViewEnabled) Icons.Default.List else Icons.Default.GridView

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onGenreClick) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }
        Text(modifier = Modifier.weight(weight = 1f),text = title, style = MaterialTheme.typography.h5)
        IconButton(onClick = onViewClick) {
            Icon(imageVector = iconView, contentDescription = null)
        }
    }
}