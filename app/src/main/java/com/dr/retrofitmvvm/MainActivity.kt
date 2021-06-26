package com.dr.retrofitmvvm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dr.retrofitmvvm.adapter.MovieListAdapter
import com.dr.retrofitmvvm.model.PhotosArray
import com.dr.retrofitmvvm.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieListAdapter.Onclick {

    lateinit var movieModelList: MutableList<PhotosArray>
    lateinit var adapter: MovieListAdapter
    lateinit var viewModel: MovieListViewModel

    var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        movieModelList = arrayListOf()
        var linearLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = linearLayoutManager
        adapter = MovieListAdapter(this, movieModelList, this)
        recyclerView.adapter = adapter

        nestedScrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                // on scroll change we are checking when users scroll as bottom.
                if (v != null) {
                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                        // in this method we are incrementing page number,
                        // making progress bar visible and calling get data method.
                        pageNumber++;
                        viewModel.makeApiCall(pageNumber)
                        progress.visibility = View.VISIBLE
                    }
                }
            }
        })


        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        viewModel.getMoviesListObserver().observe(this, Observer {
            if (it != null) {
                movieModelList = it
                adapter.setMovieLists(it)
                progress.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "error !", Toast.LENGTH_SHORT).show()
            }
        })

        if(viewModel.movieList.value == null) viewModel.makeApiCall(1)
    }

    override fun onClickImage(photosArray: PhotosArray) {
        var intent = Intent(this, ShowImageActivity::class.java)
        intent.putExtra("title", photosArray.getTitle())
        intent.putExtra("url", photosArray.getUrl_s())
        startActivity(intent)
    }
}