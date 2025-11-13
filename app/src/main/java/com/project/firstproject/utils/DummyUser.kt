package com.project.firstproject.utils

import com.project.core.domain.model.AddressEntity
import com.project.core.domain.model.CompanyEntity
import com.project.core.domain.model.GeoEntity
import com.project.core.domain.model.UserEntity

object DummyUser {
    val users = listOf(
        UserEntity(
            id = 1, name = "Leanne Graham", username = "Bret", email = "Sincere@april.biz", address = AddressEntity(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = GeoEntity(
                    lat = "-37.3159", lng = "81.1496"
                )
            ), phone = "1-770-736-8031 x56442", website = "hildegard.org", company = CompanyEntity(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time"
            )
        ), UserEntity(
            id = 2, name = "Ervin Howell", username = "Antonette", email = "Shanna@melissa.tv", address = AddressEntity(
                street = "Victor Plains",
                suite = "Suite 879",
                city = "Wisokyburgh",
                zipcode = "90566-7771",
                geo = GeoEntity(
                    lat = "-43.9509", lng = "-34.4618"
                )
            ), phone = "010-692-6593", website = "anastasia.net", company = CompanyEntity(
                name = "Deckow-Crist",
                catchPhrase = "Proactive didactic contingency",
                bs = "synergize scalable supply-chains"
            )
        )
    )
}