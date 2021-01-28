package com.android.towndirectory.town.models

import com.squareup.moshi.Json

data class Town(@field:Json(name = "Brastlewark") var inhabitants: List<Inhabitant?>) {
}