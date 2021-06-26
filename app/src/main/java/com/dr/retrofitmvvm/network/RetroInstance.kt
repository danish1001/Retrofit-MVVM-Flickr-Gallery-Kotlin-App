package com.dr.retrofitmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    var BASE_URL: String = "https://api.flickr.com/"

    // var BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    // https://jsonplaceholder.typicode.com/posts
    // services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s

    var retrofit: Retrofit? = null

    fun getRetroClient(): Retrofit? {

        if(retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

}