package com.example.seppaymentsample.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.seppaymentsample.data.room.dao.BankCardDao
import com.example.seppaymentsample.data.room.entity.BankCard

@Database(
    entities = [BankCard::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBankCardDao(): BankCardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val LOCK = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "BankCardDB.db"
            ).build()
    }
}