package com.rsb.appwrkitassigment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rsb.appwrkitassigment.repository.PreferenceDataStore
import com.rsb.appwrkitassigment.model.ListItemModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val preferenceDataStore = PreferenceDataStore(application)

    private val _item = MutableLiveData<ListItemModel>()
    val item: LiveData<ListItemModel> = _item

    private val _isStatusUpdated = MutableLiveData<Boolean>()
    val isStatusUpdated: LiveData<Boolean> = _isStatusUpdated

    fun loadItem(itemId: Int) {
        viewModelScope.launch {
            val list = preferenceDataStore.listFlow.first()
            val foundItem = list.find { it.id == itemId }
            _item.value = foundItem
        }
    }

    fun markAsCompletedOrPending() {
        val currentItem = _item.value

        if (currentItem != null && currentItem.status == "Pending") {
            val updatedItem = currentItem.copy(status = "Completed")
            viewModelScope.launch {
                preferenceDataStore.updateItem(updatedItem)
                _item.value = updatedItem
                _isStatusUpdated.value = true
            }
        } else {
            val updatedItem = currentItem!!.copy(status = "Pending")
            viewModelScope.launch {
                preferenceDataStore.updateItem(updatedItem)
                _item.value = updatedItem
                _isStatusUpdated.value = true
            }
        }
    }
}
