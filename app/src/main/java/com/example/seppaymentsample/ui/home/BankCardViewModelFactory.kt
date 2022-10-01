package com.example.seppaymentsample.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seppaymentsample.data.repositories.BankCardRepository

@Suppress("UNCHECKED_CAST")
class BankCardViewModelFactory(
    private val repository: BankCardRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BankCardViewModel(repository) as T
    }
}