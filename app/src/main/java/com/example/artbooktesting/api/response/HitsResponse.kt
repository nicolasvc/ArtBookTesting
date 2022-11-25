package com.example.artbooktesting.api.response


import com.google.gson.annotations.SerializedName

data class HitsResponse(
    @SerializedName("comments")
    val comments: Int, // 2
    @SerializedName("downloads")
    val downloads: Int, // 6439
    @SerializedName("fullHDURL")
    val fullHDURL: String, // https://pixabay.com/get/ed6a9369fd0a76647_1920.jpg
    @SerializedName("id")
    val id: Int, // 195893
    @SerializedName("imageHeight")
    val imageHeight: Int, // 2250
    @SerializedName("imageSize")
    val imageSize: Int, // 4731420
    @SerializedName("imageURL")
    val imageURL: String, // https://pixabay.com/get/ed6a9364a9fd0a76647.jpg
    @SerializedName("imageWidth")
    val imageWidth: Int, // 4000
    @SerializedName("largeImageURL")
    val largeImageURL: String, // https://pixabay.com/get/ed6a99fd0a76647_1280.jpg
    @SerializedName("likes")
    val likes: Int, // 5
    @SerializedName("pageURL")
    val pageURL: String, // https://pixabay.com/en/blossom-bloom-flower-195893/
    @SerializedName("previewHeight")
    val previewHeight: Int, // 84
    @SerializedName("previewURL")
    val previewURL: String, // https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg
    @SerializedName("previewWidth")
    val previewWidth: Int, // 150
    @SerializedName("tags")
    val tags: String, // blossom, bloom, flower
    @SerializedName("type")
    val type: String, // photo
    @SerializedName("user")
    val user: String, // Josch13
    @SerializedName("user_id")
    val userId: Int, // 48777
    @SerializedName("userImageURL")
    val userImageURL: String, // https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg
    @SerializedName("views")
    val views: Int, // 7671
    @SerializedName("webformatHeight")
    val webformatHeight: Int, // 360
    @SerializedName("webformatURL")
    val webformatURL: String, // https://pixabay.com/get/35bbf209e13e39d2_640.jpg
    @SerializedName("webformatWidth")
    val webformatWidth: Int // 640
)