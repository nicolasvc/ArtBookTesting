package com.example.artbooktesting.api.response


data class ResponsePixBay(
    val total: Int,
    val totalHits: Int,
    val hits:List<HitsResponse>
)