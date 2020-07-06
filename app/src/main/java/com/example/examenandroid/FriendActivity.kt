package com.example.examenandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenandroid.Adapter.FriendAdapter
import com.example.examenandroid.Model.FeedResponse
import com.example.examenandroid.Model.Friend
import kotlinx.android.synthetic.main.activity_friend.*
import kotlinx.android.synthetic.main.item_friend.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FriendActivity : AppCompatActivity(), FriendAdapter.FriendHolder.OnAdapterListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)

        adapter = FriendAdapter(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewFriend.layoutManager = linearLayoutManager
        recyclerViewFriend.adapter = adapter

        callObtenerListaAmigos()
    }
    private fun opActualizarListaAmigo(lstFriend: List<Friend>) {
        adapter.opActualizarListaAmigos(lstFriend)
    }
    private fun callObtenerListaAmigos() {
        var service = FriendRepository.RetrofitRepository.opObtenerListaAmigos()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.opObtenerListaAmigos()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val friend: List<Friend>? = response.body()
                        if (friend != null) {
                            opActualizarListaAmigo(friend)
                        }
                    }
                } catch (e: HttpException) {

                }
            }
        }
    }

    override fun onItemClickListener(item: Friend) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${item.phone}")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}