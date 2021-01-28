package com.android.towndirectory.town.di

import com.android.towndirectory.town.business.TownDirectoryBusiness
import com.android.towndirectory.town.data.TownDirectoryRemote
import com.android.towndirectory.town.data.TownDirectoryRepository
import com.android.towndirectory.town.interfaces.TownAPIService
import dagger.Module
import dagger.Provides

@Module
class TownDirectoryModule {
    @Provides
    fun provideTownDirectoryRemote(townAPIService: TownAPIService): TownDirectoryRemote {
        return TownDirectoryRemote(townAPIService)
    }

    @Provides
    fun provideTownDirectoryRepository(townDirectoryRemote: TownDirectoryRemote): TownDirectoryRepository {
        return TownDirectoryRepository(townDirectoryRemote)
    }

    @Provides
    fun provideTownDirectoryBusiness(townDirectoryRepository: TownDirectoryRepository): TownDirectoryBusiness {
        return TownDirectoryBusiness(townDirectoryRepository)
    }
}