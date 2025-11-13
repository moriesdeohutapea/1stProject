package com.project.firstproject.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.domain.model.AddressEntity
import com.project.core.domain.model.CompanyEntity
import com.project.core.domain.model.GeoEntity
import com.project.core.domain.model.UserEntity
import com.project.firstproject.ui.component.UserItem
import com.project.firstproject.ui.screen.home.event.MainEvent
import com.project.firstproject.ui.screen.home.state.MainState

@Composable
fun HomeScreen(
    state: MainState,
    innerPadding: PaddingValues,
    action: (MainEvent) -> Unit,
    onClickItem: (UserEntity) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        when {
            state.isLoading -> Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            state.error != null -> Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = state.error)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { action.invoke(MainEvent.Retry) }) {
                        Text("Coba Lagi")
                    }
                }
            }

            else -> LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.data) { user ->
                    UserItem(
                        user = user,
                        onClick = { onClickItem(user) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    val sampleUsers = listOf(
        UserEntity(
            id = 1, name = "Leanne Graham", username = "Bret", email = "Sincere@april.biz", address = AddressEntity(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = GeoEntity("-37.3159", "81.1496")
            ), phone = "1-770-736-8031 x56442", website = "hildegard.org", company = CompanyEntity(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        )
    )

    LazyColumn {
        items(sampleUsers) { user ->
            UserItem(
                user = user,
                onClick = {}
            )
        }
    }
}