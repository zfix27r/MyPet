package com.example.mypet.ui.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.domain.CareRepository
import com.example.mypet.domain.care.CareViewHolderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CareViewModel @Inject constructor(
    private val careRepository: CareRepository
) : ViewModel() {
    private val _careViewHolderModels = MutableStateFlow<List<CareViewHolderModel>>(emptyList())
    val careViewHolderModels = _careViewHolderModels.asStateFlow()

    fun updateCare(petId: Int, careTypeOrdinal: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            careRepository.getCareModels(petId, careTypeOrdinal)
                .collectLatest { _careViewHolderModels.value = it }
        }
    }

    fun switchAlarmState(foodModel: CareViewHolderModel) {
        /*        foodModel.toAlarmSwitchModel()?.let {
                    viewModelScope.launch(Dispatchers.IO) {
                        alarmRepository.switch(it)
                    }
                }*/
    }
}