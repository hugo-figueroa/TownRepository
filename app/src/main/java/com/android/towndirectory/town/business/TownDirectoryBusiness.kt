package com.android.towndirectory.town.business

import com.android.towndirectory.core.utils.Result
import com.android.towndirectory.town.data.TownDirectoryRepository
import com.android.towndirectory.town.models.Inhabitant
import com.android.towndirectory.town.models.Town
import javax.inject.Inject

class TownDirectoryBusiness @Inject constructor(private val townDirectoryRepository: TownDirectoryRepository) {

    suspend fun getTowns(): Result<*> {
        return when (val result: Result<*> = townDirectoryRepository.getTowns()) {
            is Result.Success -> {
                Result.Success(result.data as Town)
                // Note to Examiner: Here you can add logic to manage the data before returning to the view
            }
            is Result.Failure -> result
        }
    }
}