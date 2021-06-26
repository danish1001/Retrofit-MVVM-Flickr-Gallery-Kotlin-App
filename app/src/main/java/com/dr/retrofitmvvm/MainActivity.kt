package com.dr.retrofitmvvm

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
        var linearLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = linearLayoutManager
        adapter = MovieListAdapter(this, movieModelList)
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
                    }
                }
            }
        })



//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//
//
//                currentItems = (linearLayoutManager as GridLayoutManager).childCount
//                totalItems = (linearLayoutManager as GridLayoutManager).itemCount
//                scrollOutItems = (linearLayoutManager as GridLayoutManager).findFirstVisibleItemPosition()
//
//                // fetch data if condition is met
//
//                if (isScrolling && (totalItems == currentItems + scrollOutItems)) {
//                    isScrolling = false
//                    pageNumber++;
//                    viewModel.makeApiCall(pageNumber)
//                }
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    isScrolling = true
//                }
//            }
//        })


        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        viewModel.getMoviesListObserver().observe(this, Observer {
            if (it != null) {
                movieModelList = it
                adapter.setMovieLists(it)
            } else {
                Toast.makeText(this, "error !", Toast.LENGTH_SHORT).show()
            }
        })

        if(viewModel.movieList.value == null) viewModel.makeApiCall(1)
    }
}