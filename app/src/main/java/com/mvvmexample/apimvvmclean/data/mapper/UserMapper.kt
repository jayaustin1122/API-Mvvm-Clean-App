package com.mvvmexample.apimvvmclean.data.mapper

import com.mvvmexample.apimvvmclean.data.model.Address2Dto
import com.mvvmexample.apimvvmclean.data.model.AddressDto
import com.mvvmexample.apimvvmclean.data.model.BankDto
import com.mvvmexample.apimvvmclean.data.model.CompanyDto
import com.mvvmexample.apimvvmclean.data.model.Coordinates2Dto
import com.mvvmexample.apimvvmclean.data.model.CoordinatesDto
import com.mvvmexample.apimvvmclean.data.model.CryptoDto
import com.mvvmexample.apimvvmclean.data.model.HairDto
import com.mvvmexample.apimvvmclean.data.model.LoginResponseDto
import com.mvvmexample.apimvvmclean.data.model.UserDto
import com.mvvmexample.apimvvmclean.domain.model.Address
import com.mvvmexample.apimvvmclean.domain.model.Address2
import com.mvvmexample.apimvvmclean.domain.model.Bank
import com.mvvmexample.apimvvmclean.domain.model.Company
import com.mvvmexample.apimvvmclean.domain.model.Coordinates
import com.mvvmexample.apimvvmclean.domain.model.Coordinates2
import com.mvvmexample.apimvvmclean.domain.model.Crypto
import com.mvvmexample.apimvvmclean.domain.model.Hair
import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.model.User

fun UserDto.toDomainModel(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        maidenName = maidenName,
        age = age,
        gender = gender,
        email = email,
        phone = phone,
        username = username,
        password = password,
        birthDate = birthDate,
        image = image,
        bloodGroup = bloodGroup,
        height = height,
        weight = weight,
        eyeColor = eyeColor,
        hair = hair.toDomainModel(),
        ip = ip,
        address = address.toDomainModel(),
        macAddress = macAddress,
        university = university,
        bank = bank.toDomainModel(),
        company = company.toDomainModel(),
        ein = ein,
        ssn = ssn,
        userAgent = userAgent,
        crypto = crypto.toDomainModel(),
        role = role
    )
}

fun HairDto.toDomainModel(): Hair {
    return Hair(
        color = color,
        type = type
    )
}

fun AddressDto.toDomainModel(): Address {
    return Address(
        address = address,
        city = city,
        state = state,
        stateCode = stateCode,
        postalCode = postalCode,
        coordinates = coordinates.toDomainModel(),
        country = country
    )
}

fun CoordinatesDto.toDomainModel(): Coordinates {
    return Coordinates(
        lat = lat,
        lng = lng
    )
}

fun BankDto.toDomainModel(): Bank {
    return Bank(
        cardExpire = cardExpire,
        cardNumber = cardNumber,
        cardType = cardType,
        currency = currency,
        iban = iban
    )
}

fun CompanyDto.toDomainModel(): Company {
    return Company(
        department = department,
        name = name,
        title = title,
        address = address.toDomainModel()
    )
}

fun Address2Dto.toDomainModel(): Address2 {
    return Address2(
        address = address,
        city = city,
        state = state,
        stateCode = stateCode,
        postalCode = postalCode,
        coordinates = coordinates.toDomainModel(),
        country = country
    )
}

fun Coordinates2Dto.toDomainModel(): Coordinates2 {
    return Coordinates2(
        lat = lat,
        lng = lng
    )
}

fun CryptoDto.toDomainModel(): Crypto {
    return Crypto(
        coin = coin,
        wallet = wallet,
        network = network
    )
}
fun LoginResponseDto.toDomainModel(): LoginResponse {
    return LoginResponse(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image,
        token = accessToken
    )
}
