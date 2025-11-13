// path: ui/screen/detail/DetailActivity.kt
package com.project.firstproject.ui.screen.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.firstproject.ui.theme.FirstProjectTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra(EXTRA_TITLE) ?: "Detail"
        val description = intent.getStringExtra(EXTRA_DESCRIPTION) ?: "No description"

        setContent {
            FirstProjectTheme {
                DetailScreen(
                    title = title, description = description
                )
            }
        }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_description"
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    FirstProjectTheme {
        DetailScreen(
            title = "Preview Title", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        )
    }
}

@Composable
fun DetailScreen(
    title: String,
    description: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = title, style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description, style = MaterialTheme.typography.bodyMedium
        )
    }
}
