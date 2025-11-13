package com.project.firstproject.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.project.firstproject.ui.screen.home.HomeScreen
import com.project.firstproject.ui.screen.home.event.MainEvent
import com.project.firstproject.ui.screen.home.viewmodel.MainViewModel
import com.project.firstproject.ui.theme.FirstProjectTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstProjectTheme {
                val viewModel: MainViewModel = koinViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(Unit) {
                    viewModel.onEvent(MainEvent.Refresh)
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        state = state, innerPadding = innerPadding, action = viewModel::onEvent
                    )
                }
            }
        }
    }
}
