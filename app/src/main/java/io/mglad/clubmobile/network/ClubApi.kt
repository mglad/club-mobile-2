package io.mglad.clubmobile.network

import io.mglad.clubmobile.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ClubApi {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    @GET("clubs")
    fun getClubs(@Header("x-session-id") sessionId: String): Observable<List<Club>>

    @GET("clubs/{id}")
    fun getClubDetails(@Header("x-session-id") sessionId: String, @Path("id") clubId: Int): Observable<ClubDetails>

    @GET("clubs/all")
    fun getAllClubs(@Header("x-session-id") sessionId: String): Observable<List<Club>>

    @PUT("clubs/join/{id}")
    fun joinClub(@Header("x-session-id") sessionId: String, @Path("id") clubId: Int): Observable<Response<Void>>

    @DELETE("clubs/leave/{id}")
    fun leaveClub(@Header("x-session-id") sessionId: String, @Path("id") clubId: Int): Observable<Response<Void>>

    @POST("register")
    fun register(@Body registrationRequest: RegistrationRequest): Observable<Response<Void>>
}
