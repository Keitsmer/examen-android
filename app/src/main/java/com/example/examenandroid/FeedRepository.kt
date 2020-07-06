package com.example.examenandroid

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FeedRepository {
    object RetrofitPost {
        const val BASE_URL = "https://my-json-server.typicode.com/rzkbrian/public_db/"
        fun opObtenerListaFeed() : Services {
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(Services::class.java)
        }
    }
}