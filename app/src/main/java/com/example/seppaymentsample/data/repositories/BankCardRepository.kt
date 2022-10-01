package com.example.seppaymentsample.data.repositories

import com.example.seppaymentsample.data.room.AppDatabase
import com.example.seppaymentsample.data.room.entity.BankCard

class BankCardRepository(
    private val db: AppDatabase
) {

    suspend fun upsert(item: BankCard) = db.getBankCardDao().upsert(item)

    suspend fun delete(item: BankCard) = db.getBankCardDao().delete(item)

    fun getAllBankCards() = db.getBankCardDao().getAllBankCards()

}