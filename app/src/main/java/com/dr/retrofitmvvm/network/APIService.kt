package com.dr.retrofitmvvm.network

import com.dr.retrofitmvvm.model.MovieModel
import com.dr.retrofitmvvm.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

//    @GET("posts")
//    @GET("services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    @GET
    fun getMovieList(@Url url: String?): Call<Post>
}