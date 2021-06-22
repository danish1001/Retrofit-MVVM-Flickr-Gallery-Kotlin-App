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

class MovieListAdapter(var context: Context, var movieList: MutableList<PhotosArray>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val titleView = itemView.findViewById<View>(R.id.imageView) as TextView
//        val body = itemView.findViewById<View>(R.id.body) as TextView
        val body = itemView.findViewById<View>(R.id.imageView) as ImageView

    }


    fun setMovieLists(movieList: MutableList<PhotosArray>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.titleView.text = movieList[position].getTitle()
//        holder.body.text = movieList[position].getOwner()
        Glide.with(context).load(movieList[position].getUrl_s()).into(holder.body)
//        holder.titleView.text = movieList[position].getTitle()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}