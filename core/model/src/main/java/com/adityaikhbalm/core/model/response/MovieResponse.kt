package com.adityaikhbalm.core.model.response

data class MovieResponse(
    var page: Int = 0,
    var total_pages: Int = 0,
    var results: List<Movie>? = null
)
