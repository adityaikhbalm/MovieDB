package com.adityaikhbalm.core.remote.helper

object Constant {
    const val movie = "/movie/upcoming?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1"
    const val movie_detail = "/movie/1771?append_to_response=credits,videos,similar"
    const val movie_search = "/search/movie?language=en-US&include_adult=false&query=avenger&page=1"
}
