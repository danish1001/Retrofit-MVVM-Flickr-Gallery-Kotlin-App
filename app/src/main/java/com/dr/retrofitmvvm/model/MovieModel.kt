package com.dr.retrofitmvvm.model

class MovieModel
    (private var userId: Number=0, private var id: Number=0, private var title: String="", private var body: String="")
{

    fun getUserId(): Number {
        return userId
    }
    fun getId(): Number {
        return id
    }
    fun getTitle(): String {
        return title
    }
    fun getBody(): String {
        return body
    }

//    private var photos: PhotosObject? = null
//    private var stat: String? = null
//
//    constructor(photos: PhotosObject?, stat: String?) {
//        this.photos = photos
//        this.stat = stat
//    }
//
//    fun getPhotos(): PhotosObject? {
//        return photos
//    }
//
//    fun setPhotos(photos: PhotosObject?) {
//        this.photos = photos
//    }
//
//    fun getStat(): String? {
//        return stat
//    }
//
//    fun setStat(stat: String?) {
//        this.stat = stat
//    }

}