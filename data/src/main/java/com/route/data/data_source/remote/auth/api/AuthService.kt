package com.route.data.data_source.remote.auth.api

import com.route.data.dto.auth.request.ForgetPasswordRequestDto
import com.route.data.dto.auth.request.LoginRequestDto
import com.route.data.dto.auth.request.RegisterRequestDto
import com.route.data.dto.auth.request.ResetPasswordRequestDto
import com.route.data.dto.auth.request.UpdatePasswordRequestDto
import com.route.data.dto.auth.request.UpdateUserRequestDto
import com.route.data.dto.auth.request.VerifyResetCodeRequestDto
import com.route.data.dto.auth.response.AuthResponseDto
import com.route.data.dto.auth.response.ForgetPasswordResponseDto
import com.route.data.dto.auth.response.ResetPasswordResponseDto
import com.route.data.dto.auth.response.UpdateUserResponseDto
import com.route.data.dto.auth.response.VerifyResetCodeResponseDto
import com.route.data.dto.auth.response.VerifyTokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthService {
    @POST("auth/signin")
    suspend fun login(@Body requestDto: LoginRequestDto): Response<AuthResponseDto?>

    @POST("auth/signup")
    suspend fun register(@Body requestDto: RegisterRequestDto): Response<AuthResponseDto?>

    @POST("auth/forgotPasswords")
    suspend fun forgetPassword(@Body requestDto: ForgetPasswordRequestDto): Response<ForgetPasswordResponseDto?>

    @POST("auth/verifyResetCode")
    suspend fun verifyResetCode(@Body requestDto: VerifyResetCodeRequestDto): Response<VerifyResetCodeResponseDto?>

    @POST("auth/resetPassword")
    suspend fun resetPassword(@Body requestDto: ResetPasswordRequestDto): Response<ResetPasswordResponseDto?>

    @GET("auth/verifyToken")
    suspend fun verifyToken(@Header("token") token: String): Response<VerifyTokenResponseDto?>

    @PUT("users/changeMyPassword")
    suspend fun updatePassword(
        @Body requestDto: UpdatePasswordRequestDto,
        @Header("token") token: String
    ): Response<AuthResponseDto?>

    @PUT("users/updateMe")
    suspend fun updateUser(
        @Body requestDto: UpdateUserRequestDto,
        @Header("token") token: String
    ): Response<UpdateUserResponseDto?>
}
