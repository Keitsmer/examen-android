package com.example.examenandroid.Model

import com.example.examenandroid.Model.Social

data class PerfilResponse (
    val id:String,
    val username:String,
    val name:String,
    val lastname:String,
    val image:String,
    val occupation:String,
    val age:String,
    val email:String,
    val location:String,
    val phone:String,
    val social: Social
)