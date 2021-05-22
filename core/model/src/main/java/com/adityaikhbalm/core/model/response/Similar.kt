package com.adityaikhbalm.core.model.response

import com.squareup.moshi.Json

data class Similar(
    var id: Int = 0,
    var title: String? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null
)
