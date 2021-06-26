package com.dr.retrofitmvvm.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dr.retrofitmvvm.model.MovieModel
import com.dr.retrofitmvvm.model.PhotosArray
import com.dr.retrofitmvvm.model.PhotosObject
import com.dr.retrofitmvvm.model.Post
import com.dr.retrofitmvvm.network.APIService
import com.dr.retrofitmvvm.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieListViewModel: ViewModel() {

    var movieList: MutableLiveData<MutableList<PhotosArray>> = MutableLiveData()
    var urls: MutableList<PhotosArray>? = arrayListOf()

    fun getMoviesListObserver(): MutableLiveData<MutableList<PhotosArray>> {
        return movieList
    }

    fun makeApiCall(pageNumber: Number) {
        var apiService: APIService = RetroInstance().getRetroClient()!!.create(APIService::class.java)

        var Url: String = "https://api.flickr.com/services/rest/?method=flickr.photos" +
                ".getRecent&per_page=35&page="+ pageNumber +"&api_key=6f102c62f41998d151e" +
                "5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"
//        var Url: String = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"

        var call: Call<Post> = apiService.getMovieList(Url)

        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                var data:MutableList<PhotosArray>? = response.body()?.photos?.getPhotosArrays()
//                movieList.postValue(data)
                for(i in response.body()?.photos?.getPhotosArrays()!!) {
                    Log.i("data is ", i.getUrl_s().toString())
                    var j: PhotosArray = i

                }
                if (data != null) {
                    urls?.addAll(data)
                }
                movieList.postValue(urls)
                Log.i("success", "---------------------------------------------")
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.i("fail", "---------------------------------------------")
            }
        })

    }


}

