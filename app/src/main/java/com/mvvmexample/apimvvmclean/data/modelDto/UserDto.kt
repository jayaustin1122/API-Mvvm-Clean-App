package com.mvvmexample.apimvvmclean.data.modelDto

data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val maidenName: String,
    val age: Long,
    val gender: String,
    val email: String,
    val phone: String,
    val username: String,
    val password: String,
    val birthDate: String,
    val image: String,
    val bloodGroup: String,
    val height: Double,
    val weight: Double,
    val eyeColor: String,
    val hair: HairDto,
    val ip: String,
    val address: AddressDto,
    val macAddress: String,
    val university: String,
    val bank: BankDto,
    val company: CompanyDto,
    val ein: String,
    val ssn: String,
    val userAgent: String,
    val crypto: CryptoDto,
    val role: String,
)
data class UsersResponse(
    val users: List<UserDto>
)
data class HairDto(
    val color: String,
    val type: String,
)

data class AddressDto(
    val address: String,
    val city: String,
    val state: String,
    val stateCode: String,
    val postalCode: String,
    val coordinates: CoordinatesDto,
    val country: String,
)

data class CoordinatesDto(
    val lat: Double,
    val lng: Double,
)

data class BankDto(
    val cardExpire: String,
    val cardNumber: String,
    val cardType: String,
    val currency: String,
    val iban: String,
)

data class CompanyDto(
    val department: String,
    val name: String,
    val title: String,
    val address: Address2Dto,
)

data class Address2Dto(
    val address: String,
    val city: String,
    val state: String,
    val stateCode: String,
    val postalCode: String,
    val coordinates: Coordinates2Dto,
    val country: String,
)

data class Coordinates2Dto(
    val lat: Double,
    val lng: Double,
)

data class CryptoDto(
    val coin: String,
    val wallet: String,
    val network: String,
)


