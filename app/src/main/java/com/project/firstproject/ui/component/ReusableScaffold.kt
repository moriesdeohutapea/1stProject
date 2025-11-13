package com.project.firstproject.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * Reusable scaffold untuk screen di aplikasi.
 *
 * Dipakai supaya struktur layout (top bar + content) konsisten di semua screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableScaffold(
    modifier: Modifier = Modifier,
    title: String? = null,
    showTopBar: Boolean = true,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    content: @Composable (innerPadding: PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(), topBar = {
            if (showTopBar && title != null) {
                TopAppBar(
                    title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
                    navigationIcon = {
                        if (onNavigationClick != null) {
                            IconButton(onClick = onNavigationClick) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    tint = Color.White,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    actions = {
                        actions?.invoke()
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            }
        }) { innerPadding ->
        content(innerPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun ReusableScaffoldPreview() {
    ReusableScaffold(
        title = "Preview Title", showTopBar = true, onNavigationClick = {

        }) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Content Area")
        }
    }
}
