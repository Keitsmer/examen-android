package com.example.examenandroid.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.examenandroid.Model.FeedResponse
import com.example.examenandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_feed.view.*


class FeedAdapter (private var data: List<FeedResponse>, private val listener: FeedHolder.OnAdapterListener) : RecyclerView.Adapter<FeedAdapter.FeedHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.FeedHolder {
        val inflater = parent.inflate(R.layout.item_list_feed, false)
        return FeedHolder(inflater)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.FeedHolder, position: Int) {
        val objFeed: FeedResponse = this.data[position]
        if (!objFeed.user_image.isBlank()) {
            Picasso.get()
                .load(objFeed.user_image)
                .resize(100, 100)
                .centerCrop()
                .into(holder.itemView.item_detalle_imagen)
        }
        holder.itemView.lblUsuario.text = objFeed.username

        if (!objFeed.image.isBlank()) {
            Picasso.get()
                .load(objFeed.image)
                .resize(100,100)
                .centerCrop()
                .into(holder.itemView.imgDescripcion)
        }
        holder.itemView.lblDescripcion.text = objFeed.body
        holder.itemView.lblLikes.text = objFeed.likes.toString()

        holder.itemView.btnVerComentarios.setOnClickListener { listener.onItemClickListener((objFeed)) }
        //holder.itemView.setOnClickListener { listener.onItemClickListener(objFeed) }

    }

    fun opActualizarLista(lstFeed: List<FeedResponse>) {
        this.data = lstFeed
        this.notifyDataSetChanged()
    }

    class FeedHolder(v:View): RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private  var feed: FeedResponse? = null
        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_LONG).show()
            }
        }
        interface OnAdapterListener {
            fun onItemClickListener(item: FeedResponse)
        }
    }
}