package com.project.firstproject.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.core.domain.model.UserEntity
import com.project.firstproject.ui.component.ReusableScaffold
import com.project.firstproject.utils.DummyUser

@Composable
fun DetailScreen(
    onNavigationClick: (() -> Unit),
    userEntity: UserEntity,
) {
    ReusableScaffold(
        title = userEntity.name,
        showTopBar = true,
        onNavigationClick = {
            onNavigationClick.invoke()
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Contact info
            Card(
                modifier = Modifier.defaultMinSize(), colors = CardDefaults.cardColors()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Contact", style = MaterialTheme.typography.titleMedium
                    )
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                    InfoRow(label = "Email", value = userEntity.email)
                    InfoRow(label = "Phone", value = userEntity.phone)
                    InfoRow(label = "Website", value = userEntity.website)
                }
            }

            // Address
            Card(
                modifier = Modifier.defaultMinSize(), colors = CardDefaults.cardColors()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Address", style = MaterialTheme.typography.titleMedium
                    )
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                    val address = userEntity.address
                    InfoRow(label = "Street", value = address.street)
                    InfoRow(label = "Suite", value = address.suite)
                    InfoRow(label = "City", value = address.city)
                    InfoRow(label = "Zipcode", value = address.zipcode)
                }
            }

            // Company
            Card(
                modifier = Modifier.fillMaxSize(), colors = CardDefaults.cardColors()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Company", style = MaterialTheme.typography.titleMedium
                    )
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                    val company = userEntity.company
                    InfoRow(label = "Name", value = company.name)
                    InfoRow(label = "Tagline", value = company.catchPhrase)
                    InfoRow(label = "Business", value = company.bs)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
) {
    Column {
        Text(
            text = label, style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = value, style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        onNavigationClick = {},
        userEntity = DummyUser.users.first()
    )
}