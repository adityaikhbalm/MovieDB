package com.adityaikhbalm.core.model.response

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "backdrop_path")
    var backdropPath: String? = null,
    var genres: List<Genre>? = null,
    var id: Int = 0,
    var overview: String? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "release_date")
    var releaseDate: String? = null,
    var runtime: Int = 0,
    var title: String? = null,
    @Json(name = "credits")
    var credit: CreditResponse? = null,
    @Json(name = "videos")
    var trailer: TrailerResponse? = null,
    var similar: SimilarResponse? = null,
    var favorite: Int = 0
)
