package com.route.data.data_source.remote.auth

import com.route.data.base.BaseRemoteDataSource
import com.route.data.data_source.remote.auth.api.AuthService
import com.route.data.dto.auth.request.ForgetPasswordRequestDto
import com.route.data.dto.auth.request.LoginRequestDto
import com.route.data.dto.auth.request.RegisterRequestDto
import com.route.data.dto.auth.request.ResetPasswordRequestDto
import com.route.data.dto.auth.request.UpdatePasswordRequestDto
import com.route.data.dto.auth.request.UpdateUserRequestDto
import com.route.data.dto.auth.request.VerifyResetCodeRequestDto
import com.route.domain.base.Resource
import com.route.domain.base.mapResource
import com.route.domain.repo.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : BaseRemoteDataSource(), AuthRemoteDataSource {

    override suspend fun login(email: String, password: String): Flow<Resource<String?>> {
        return safeAPICall {
            authService.login(LoginRequestDto(password = password, email = email))
        }.map { it.mapResource { responseDto -> responseDto?.token } }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        rePassword: String,
        phone: String
    ): Flow<Resource<String?>> = safeAPICall {
        authService.register(
            requestDto = RegisterRequestDto(
                name = name,
                email = email,
                password = password,
                rePassword = rePassword,
                phone = phone
            )
        )
    }.map { it.mapResource { responseDto -> responseDto?.token } }

    override suspend fun forgetPassword(email: String): Flow<Resource<String?>> =
        safeAPICall { authService.forgetPassword(ForgetPasswordRequestDto(email = email)) }
            .map { it.mapResource { responseDto -> responseDto?.message } }

    override suspend fun verifyResetCode(resetCode: String): Flow<Resource<String?>> =
        safeAPICall { authService.verifyResetCode(VerifyResetCodeRequestDto(resetCode = resetCode)) }
            .map { it.mapResource { responseDto -> responseDto?.status } }

    override suspend fun resetPassword(
        email: String,
        newPassword: String
    ): Flow<Resource<String?>> = safeAPICall {
        authService.resetPassword(
            requestDto = ResetPasswordRequestDto(
                email = email,
                newPassword = newPassword
            )
        )
    }.map { it.mapResource { responseDto -> responseDto?.token } }

    override suspend fun updatePassword(
        token: String,
        currentPassword: String,
        password: String,
        rePassword: String
    ): Flow<Resource<String?>> = safeAPICall {
        authService.updatePassword(
            requestDto = UpdatePasswordRequestDto(
                currentPassword = currentPassword,
                password = password,
                rePassword = rePassword
            ),
            token = token
        )
    }.map { it.mapResource { responseDto -> responseDto?.token } }

    override suspend fun updateUser(
        token: String,
        name: String?,
        email: String?,
        phone: String?
    ): Flow<Resource<Unit>> =
        safeAPICall {
            val updateUser = authService.updateUser(
                requestDto = UpdateUserRequestDto(
                    name = name,
                    email = email,
                    phone = phone
                ),
                token = token
            )
            updateUser
        }.map { it.mapResource { } }
}
