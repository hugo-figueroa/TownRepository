package com.android.towndirectory.town.models

import com.squareup.moshi.Json

data class Inhabitant(
    var id: Int,
    var name: String?,
    @field:Json(name = "thumbnail") var imageUrl: String?,
    var age: Int,
    var weight: Double,
    var height: Double,
    @field:Json(name = "hair_color") var hairColor: String?,
    var professions: List<String?>,
    var friends: List<String?>
)