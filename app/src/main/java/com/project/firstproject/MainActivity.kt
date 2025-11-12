package com.project.firstproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.network.Address
import com.project.core.network.Company
import com.project.core.network.Geo
import com.project.core.network.UserResponse
import com.project.firstproject.component.UserItem
import com.project.firstproject.screen.viewmodel.MainEvent
import com.project.firstproject.screen.viewmodel.MainViewModel
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when {
                            state.isLoading -> Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }

                            state.error != null -> Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = state.error ?: "Terjadi kesalahan")
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Button(onClick = { viewModel.onEvent(MainEvent.Retry) }) {
                                        Text("Coba Lagi")
                                    }
                                }
                            }

                            else -> LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(state.data) { user ->
                                    UserItem(user = user)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    val sampleUsers = listOf(
        UserResponse(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = Geo("-37.3159", "81.1496")
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        )
    )

    LazyColumn {
        items(sampleUsers) { user ->
            UserItem(user = user)
        }
    }
}