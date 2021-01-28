package com.android.towndirectory.town.data

import com.android.towndirectory.R
import com.android.towndirectory.core.TownApplication
import com.android.towndirectory.core.utils.BaseNetwork
import com.android.towndirectory.core.utils.Result
import com.android.towndirectory.town.interfaces.TownAPIService
import javax.inject.Inject

class TownDirectoryRemote @Inject constructor(private val townAPIService: TownAPIService) :
    BaseNetwork() {

    suspend fun getTowns(): Result<*> = safeApiCall(
        call = { townAPIService.getTowns() },
        error = TownApplication.context.getString(R.string.error_getting_inhabitants)
    )
}