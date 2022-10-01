package com.example.seppaymentsample.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seppaymentsample.data.room.entity.BankCard

@Dao
interface BankCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: BankCard)

    @Delete
    suspend fun delete(item: BankCard)

    @Query("SELECT * FROM bank_card_items")
    fun getAllBankCards() : LiveData<List<BankCard>>
}