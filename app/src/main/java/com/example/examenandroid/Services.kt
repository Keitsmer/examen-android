package com.example.examenandroid

import com.example.examenandroid.Model.FeedResponse
import com.example.examenandroid.Model.Friend
import com.example.examenandroid.Model.PerfilResponse
import retrofit2.Response
import retrofit2.http.GET

interface Services {
    @GET("profile")
    suspend fun opObtenerPerfil() : Response<PerfilResponse>

    @GET("posts")
    suspend fun  opObtenerPost() : Response<List<FeedResponse>>

    @GET("users")
    suspend fun opObtenerListaAmigos() : Response<List<Friend>>
}