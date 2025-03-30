package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.data.model.Address2Dto
import com.mvvmexample.apimvvmclean.data.model.AddressDto
import com.mvvmexample.apimvvmclean.data.model.BankDto
import com.mvvmexample.apimvvmclean.data.model.CompanyDto
import com.mvvmexample.apimvvmclean.data.model.Coordinates2Dto
import com.mvvmexample.apimvvmclean.data.model.CoordinatesDto
import com.mvvmexample.apimvvmclean.data.model.CryptoDto
import com.mvvmexample.apimvvmclean.data.model.HairDto
import com.mvvmexample.apimvvmclean.data.model.UserDto
import com.mvvmexample.apimvvmclean.data.remote.ApiService
import com.mvvmexample.apimvvmclean.domain.model.Address
import com.mvvmexample.apimvvmclean.domain.model.Address2
import com.mvvmexample.apimvvmclean.domain.model.Bank
import com.mvvmexample.apimvvmclean.domain.model.Company
import com.mvvmexample.apimvvmclean.domain.model.Coordinates
import com.mvvmexample.apimvvmclean.domain.model.Coordinates2
import com.mvvmexample.apimvvmclean.domain.model.Crypto
import com.mvvmexample.apimvvmclean.domain.model.Hair
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override fun getUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getUsers()
            val users = response.users.map { it.toDomainModel() }
            emit(Resource.Success(users))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }

    override fun getUserById(id: Int): Flow<Resource<User>> = flow {
        emit(Resource.Loading())

        try {
            val user = api.getUserById(id).toDomainModel()
            emit(Resource.Success(user))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }

    private fun UserDto.toDomainModel(): User {
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
            hair = hair,
            ip = ip,
            address = address,
            macAddress = macAddress,
            university = university,
            bank = bank,
            company = company,
            ein = ein,
            ssn = ssn,
            userAgent = userAgent,
            crypto = crypto,
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
            coordinates = coordinates,
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
            address = address
        )
    }

    fun Address2Dto.toDomainModel(): Address2 {
        return Address2(
            address = address,
            city = city,
            state = state,
            stateCode = stateCode,
            postalCode = postalCode,
            coordinates = coordinates,
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

}