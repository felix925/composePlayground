package jp.making.felix.composeplayground.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.making.felix.composeplayground.DataViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when{
                isAssignableFrom(DataViewModel::class.java) -> DataViewModel()
                else -> throw IllegalArgumentException("Unknown ViewModel ${modelClass.name}")
            }
        } as T
}