package com.android.towndirectory.town.interfaces

import com.android.towndirectory.town.models.Town
import retrofit2.Response
import retrofit2.http.GET

interface TownAPIService {

    @GET("/rrafols/mobile_test/master/data.json")
    suspend fun getTowns(): Response<Town>

}