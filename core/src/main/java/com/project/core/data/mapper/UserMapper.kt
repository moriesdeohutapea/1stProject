import com.project.core.domain.model.AddressEntity
import com.project.core.domain.model.CompanyEntity
import com.project.core.domain.model.GeoEntity
import com.project.core.domain.model.UserEntity
import com.project.core.network.model.Address
import com.project.core.network.model.Company
import com.project.core.network.model.Geo
import com.project.core.network.model.UserResponse

fun UserResponse.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address.toEntity(),
        phone = phone,
        website = website,
        company = company.toEntity()
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        street = street, suite = suite, city = city, zipcode = zipcode, geo = geo.toEntity()
    )
}

fun Geo.toEntity(): GeoEntity {
    return GeoEntity(
        lat = lat, lng = lng
    )
}

fun Company.toEntity(): CompanyEntity {
    return CompanyEntity(
        name = name, catchPhrase = catchPhrase, bs = bs
    )
}