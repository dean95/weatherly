package weatherly.weather.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import weatherly.weather.domain.model.Location

@Serializable
data class LocationSearchResultItem(
    @SerialName("AdministrativeArea")
    val administrativeArea: AdministrativeArea,
    @SerialName("Country")
    val country: Country,
    @SerialName("Key")
    val key: String,
    @SerialName("LocalizedName")
    val localizedName: String,
    @SerialName("Rank")
    val rank: Int,
    @SerialName("Type")
    val type: String,
    @SerialName("Version")
    val version: Int
) {

    @Serializable
    data class Country(
        @SerialName("ID")
        val id: String,
        @SerialName("LocalizedName")
        val localizedName: String
    )

    @Serializable
    data class AdministrativeArea(
        @SerialName("ID")
        val id: String,
        @SerialName("LocalizedName")
        val localizedName: String
    )

    fun toDomain() = Location(
        id = key,
        name = localizedName
    )
}
