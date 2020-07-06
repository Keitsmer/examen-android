package com.example.examenandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenandroid.Adapter.FeedAdapter
import com.example.examenandroid.Model.Comment
import com.example.examenandroid.Model.FeedResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.item_list_feed.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FeedActivity : AppCompatActivity(), FeedAdapter.FeedHolder.OnAdapterListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FeedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)


        adapter = FeedAdapter(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        callObtenerFeed()


    }
    private fun opActualizarLista(lstFeed: List<FeedResponse>) {
        adapter.opActualizarLista(lstFeed)
    }
    private fun callObtenerFeed() {
        var service = FeedRepository.RetrofitPost.opObtenerListaFeed()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.opObtenerPost()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val feed: List<FeedResponse>? = response.body()
                        if (feed != null) {
                            opActualizarLista(feed)
                        }
                    }
                } catch (e: HttpException) {

                }
            }
        }
    }

    override fun onItemClickListener(item: FeedResponse) {
        val objComentarios: String = Gson().toJson(item, FeedResponse::class.java)
        val intent = Intent(this, ComentariosActivity::class.java)
        intent.putExtra("Feed", objComentarios)
        startActivity(intent)
    }
}