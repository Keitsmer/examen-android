package com.example.examenandroid.Model

import com.example.examenandroid.Model.Comment

data class FeedResponse (
    val id:Int,
    val user_id:Int,
    val username:String,
    val user_image:String,
    val body:String,
    val image:String,
    val likes:Int,
    val comment: List<Comment>
)