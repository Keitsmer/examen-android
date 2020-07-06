package com.example.examenandroid.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.examenandroid.Model.Comment
import com.example.examenandroid.Model.FeedResponse
import com.example.examenandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comentario.view.*

class CommentAdapter(private var data: List<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CommentHolder {
        val inflater = parent.inflate(R.layout.item_comentario, false)
        return CommentAdapter.CommentHolder(inflater)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentHolder, position: Int) {
        var objFeed: Comment = this.data[position]
        if (!objFeed.user_image.isBlank()) {
            Picasso.get()
                .load(objFeed.user_image)
                .resize(100,100)
                .centerCrop()
                .into(holder.itemView.item_imagen_comment)
        }
        holder.itemView.item_description_comment.text = objFeed.comment
    }
    fun opActualizarListaComentarios(lstComentarios: List<Comment>) {
        this.data = lstComentarios
        this.notifyDataSetChanged()
    }
    class CommentHolder(v:View): RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private  var comment: Comment? = null
        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_LONG).show()
            }
        }

    }
}