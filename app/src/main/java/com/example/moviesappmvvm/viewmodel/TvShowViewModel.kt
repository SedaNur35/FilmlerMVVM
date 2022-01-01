package com.example.moviesappmvvm.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappmvvm.module.ModelItem
import com.example.moviesappmvvm.repository.ModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope

@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: ModelRepository) : ViewModel() {

    private val _response = MutableLiveData<List<ModelItem>>()
    val responseTvShow: LiveData<List<ModelItem>>
        get() = _response

    init {
        getAllTvShows()
    }

    private fun getAllTvShows() = viewModelScope.launch {
        repository.getTvShows().let {response ->

            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("tag", "getAllTvShows Error: ${response.code()}")
            }
        }
    }



}