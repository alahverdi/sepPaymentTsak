package com.example.seppaymentsample.ui.home

import androidx.lifecycle.ViewModel
import com.example.seppaymentsample.data.repositories.BankCardRepository
import com.example.seppaymentsample.data.room.entity.BankCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BankCardViewModel(
    private val repository: BankCardRepository
) : ViewModel() {

    fun upsert(item: BankCard) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: BankCard) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllBankCards() = repository.getAllBankCards()

}