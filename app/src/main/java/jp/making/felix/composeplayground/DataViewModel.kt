package jp.making.felix.composeplayground

import androidx.lifecycle.*
import jp.making.felix.composeplayground.data.repository.DogDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataViewModel : ViewModel() {
    @Inject
    private lateinit var dogDataSource: DogDataSource
    private val _dogData = MutableLiveData<List<String>>()
    val dogData: LiveData<List<String>>
        get() = _dogData

    fun test() {
        viewModelScope.launch {
            kotlin.runCatching { dogDataSource.getDogs() }
                .onSuccess { _dogData.value = it.url }
        }
    }
}