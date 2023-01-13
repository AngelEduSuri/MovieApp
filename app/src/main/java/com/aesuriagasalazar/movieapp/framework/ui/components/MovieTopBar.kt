package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MovieTopBar(
    modifier: Modifier = Modifier,
    title: String,
    iconNav: ImageVector = Icons.Default.Menu,
    textStyle: TextStyle = MaterialTheme.typography.h5,
    onNavClick: () -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onNavClick) {
            Icon(imageVector = iconNav, contentDescription = null)
        }
        Text(modifier = Modifier.weight(weight = 1f),text = title, style = textStyle)
        actions?.let { it() }
    }
}