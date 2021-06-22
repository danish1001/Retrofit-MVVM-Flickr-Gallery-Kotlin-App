package com.dr.retrofitmvvm

import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dr.retrofitmvvm.adapter.MovieListAdapter
import com.dr.retrofitmvvm.model.MovieModel
import com.dr.retrofitmvvm.model.PhotosArray
import com.dr.retrofitmvvm.model.Post
import com.dr.retrofitmvvm.network.APIService
import com.dr.retrofitmvvm.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var movieModelList: MutableList<PhotosArray>
    lateinit var adapter: MovieListAdapter
    lateinit var viewModel: MovieListViewModel

    lateinit var urls: ArrayList<String>
    var currentItems: Int = 0
    var scrollOutItems: Int = 0
    var totalItems: Int = 0
    var isScrolling: Boolean = false
    var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        movieModelList = arrayListOf()
//        var linearLayoutManager = GridLayoutManager(this, 2)
        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = MovieListAdapter(this,  movieModelList)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentItems = (linearLayoutManager as LinearLayoutManager).childCount
                totalItems = (linearLayoutManager as LinearLayoutManager).itemCount
                scrollOutItems = (linearLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                // fetch data if condition is met

                if(isScrolling && (totalItems == currentItems + scrollOutItems)) {
                    isScrolling = false
                    pageNumber++;
                    viewModel.makeApiCall(pageNumber)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        })


        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        viewModel.getMoviesListObserver().observe(this, Observer {
            if(it != null) {
                movieModelList = it
                adapter.setMovieLists(it)
            } else {
                Toast.makeText(this, "error !", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeApiCall(1)
    }
}