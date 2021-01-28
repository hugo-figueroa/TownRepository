package com.android.towndirectory.town.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.towndirectory.core.utils.Resource
import com.android.towndirectory.core.utils.Result
import com.android.towndirectory.core.utils.onFailure
import com.android.towndirectory.core.utils.onSuccess
import com.android.towndirectory.town.business.TownDirectoryBusiness
import kotlinx.coroutines.launch
import javax.inject.Inject

class TownsViewModel @Inject constructor(private val townDirectoryBusiness: TownDirectoryBusiness) :
    ViewModel() {

    // Inhabitants
    private val _inhabitantListData = MutableLiveData<Resource<Any>>()
    val inhabitantList: LiveData<Resource<Any>> = _inhabitantListData

    fun getTowns() {
        _inhabitantListData.value = Resource.loading()
        viewModelScope.launch {
            val result: Result<*> = townDirectoryBusiness.getTowns()
            result.onSuccess {
                _inhabitantListData.value = Resource.success(it) }
                .onFailure {
                    _inhabitantListData.value = Resource.error(it) }
        }
    }
}