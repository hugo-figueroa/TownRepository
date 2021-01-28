package com.android.towndirectory.town.data

import com.android.towndirectory.core.utils.Result
import javax.inject.Inject

class TownDirectoryRepository @Inject constructor(private val townDirectoryRemote: TownDirectoryRemote) {

    suspend fun getTowns(): Result<*> = townDirectoryRemote.getTowns()

}