package com.game.roomproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.game.roomproject.data.SubscriberRepository
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val repository: SubscriberRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model class")
    }
}