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

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val preferenceDataStore = PreferenceDataStore(application)

    private val _listItems = MutableLiveData<List<ListItemModel>>()
    val listItems: LiveData<List<ListItemModel>> = _listItems

    private val _searchResults = MutableLiveData<List<ListItemModel>>()
    val searchResults: LiveData<List<ListItemModel>> = _searchResults

    private val _itemCountText = MutableLiveData<String>()
    val itemCountText: LiveData<String> = _itemCountText

    fun loadData() {
        viewModelScope.launch {
            val list = preferenceDataStore.listFlow.first()
            if (list.isEmpty()) {
                initializeData()
            } else {
                _listItems.value = list
                _searchResults.value = list
                updateCounts(list)
            }
        }
    }

    private suspend fun initializeData() {
        val data = (1..100).map {
            ListItemModel(it, "Title $it", "Description $it", if (it % 2 == 0) "Completed" else "Pending")
        }
        preferenceDataStore.saveList(data)
        _listItems.value = data
        _searchResults.value = data
        updateCounts(data)
    }

    fun updateItem(updatedItem: ListItemModel) {
        viewModelScope.launch {
            preferenceDataStore.updateItem(updatedItem)
            loadData() // Reload data to reflect changes
        }
    }

    fun search(query: String) {
        val listToSearch = _listItems.value ?: emptyList()
        if (query.isEmpty()) {
            _searchResults.value = listToSearch
            updateCounts(listToSearch)
            return
        }

        val results = listToSearch.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true) ||
            it.status.contains(query, ignoreCase = true)
        }
        _searchResults.value = results
        updateCounts(results)
    }

    private fun updateCounts(list: List<ListItemModel>) {
        val total = list.size
        val pending = list.count { it.status == "Pending" }
        val completed = list.count { it.status == "Completed" }
        _itemCountText.value = "Total: $total, Pending: $pending, Completed: $completed"
    }
}
