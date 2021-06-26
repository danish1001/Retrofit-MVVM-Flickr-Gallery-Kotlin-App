package com.dr.retrofitmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dr.retrofitmvvm.R
import com.dr.retrofitmvvm.model.MovieModel
import com.dr.retrofitmvvm.model.PhotosArray
import com.dr.retrofitmvvm.model.PhotosObject
import com.dr.retrofitmvvm.model.Post
import kotlinx.android.synthetic.main.recycler_row.view.*

class MovieListAdapter(var context: Context, var movieList: MutableList<PhotosArray>, var listener: Onclick): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val body = itemView.findViewById<View>(R.id.imageView) as ImageView

    }

    fun setMovieLists(movieList: MutableList<PhotosArray>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        var viewHolder = ViewHolder(view)
        view.setOnClickListener {
            listener.onClickImage(movieList[viewHolder.layoutPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(movieList[position].getUrl_s())
            .placeholder(R.drawable.loading_layout)
            .centerCrop()
            .into(holder.body)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface Onclick {
        fun onClickImage(photosArray: PhotosArray)
    }
}