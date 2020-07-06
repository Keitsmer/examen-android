package com.example.examenandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenandroid.Adapter.CommentAdapter
import com.example.examenandroid.Adapter.FeedAdapter
import com.example.examenandroid.Model.Comment
import com.example.examenandroid.Model.FeedResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_comentarios.*
import kotlinx.android.synthetic.main.activity_feed.*

class ComentariosActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentarios)

        val objFeed = Gson().fromJson(intent.getStringExtra("Feed"), FeedResponse::class.java)

        var lstComment = objFeed.comment

        adapter = CommentAdapter(lstComment)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewComentarios.layoutManager = linearLayoutManager
        recyclerViewComentarios.adapter = adapter

        btnAgregarComentario.setOnClickListener {
            lstComment+=Comment(1,"Harold Caballero",
                "https://www.nj.com/resizer/h8MrN0-Nw5dB5FOmMVGMmfVKFJo=/450x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg",
                txtComentario.text.toString())
            txtComentario.setText("")
            opActualizarListaComentarios(lstComment)
        }
    }
    private  fun opActualizarListaComentarios(lstComentarios: List<Comment>) {
        adapter.opActualizarListaComentarios(lstComentarios)
    }
}