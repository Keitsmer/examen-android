package com.example.examenandroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.examenandroid.Model.FeedResponse
import com.example.examenandroid.Model.Friend
import com.example.examenandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendAdapter(private var data: List<Friend>, private val listener: FriendHolder.OnAdapterListener) : RecyclerView.Adapter<FriendAdapter.FriendHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.FriendHolder {
        val inflater = parent.inflate(R.layout.item_friend, false)
        return FriendHolder(inflater)
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun opActualizarListaAmigos(lstFriend: List<Friend>) {
        this.data = lstFriend
        this.notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: FriendAdapter.FriendHolder, position: Int) {
        var objFriend: Friend = this.data[position]
        if (!objFriend.image.isBlank()) {
            Picasso.get()
                .load(objFriend.image)
                .resize(100,100)
                .centerCrop()
                .into(holder.itemView.item_imagen_friend)
        }
        holder.itemView.item_name_friend.text = String.format("%s %s", objFriend.name, objFriend.lastname)
        holder.itemView.setOnClickListener { listener.onItemClickListener(objFriend) }
    }

    class FriendHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view:View = v
        private var friend: Friend? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_LONG).show()
            }
        }

        interface OnAdapterListener {
            fun onItemClickListener(item: Friend)
        }
    }

}

