package com.example.examenandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examenandroid.Model.PerfilResponse
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIrFeed.setOnClickListener {
            val intentFeed = Intent(this, FeedActivity::class.java)
            startActivity(intentFeed)
        }

        btnIrAmigos.setOnClickListener {
            val intentFriend = Intent(this, FriendActivity::class.java)
            startActivity(intentFriend)
        }

        callObtenerPerfilService()
    }

    private  fun callObtenerPerfilService() {
        var service = PerfilRepository.RetrofitRepository.opObtenerPerfil()
        CoroutineScope(Dispatchers.IO).launch {
            val responseService = service.opObtenerPerfil()
            withContext(Dispatchers.Main) {
                try {
                    if (responseService.isSuccessful) {
                        val objPersona: PerfilResponse? = responseService.body()
                        if (objPersona != null) {
                            profile_fullname.text = String.format("%s %s", objPersona.name, objPersona.lastname)
                            profile_likes.text = objPersona.social.likes.toString()
                            profile_posts.text = objPersona.social.posts.toString()
                            profile_shares.text = objPersona.social.shares.toString()
                            profile_friends.text = objPersona.social.friends.toString()

                            profile_years.text = objPersona.age
                            profile_email.text = objPersona.email
                            profile_location.text = objPersona.location
                            profile_occupation.text = objPersona.occupation
                            Picasso.get()
                                .load(objPersona.image)
                                .resize(100,100)
                                .centerCrop()
                                .into(profile_image)
                        }
                    }
                } catch (e: HttpException) {
                    Toast.makeText(this@MainActivity, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}