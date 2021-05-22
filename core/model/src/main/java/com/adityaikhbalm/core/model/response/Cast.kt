package com.adityaikhbalm.core.model.response

import com.squareup.moshi.Json

data class Cast(
    var id: Int = 0,
    var name: String? = null,
    @Json(name = "profile_path")
    var profilePath: String? = null,
    var order: Int = 0
)
