package com.example.seppaymentsample.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_card_items")
data class BankCard(
    @ColumnInfo(name = "item_tittle")
    val tittle: String,
    @ColumnInfo(name = "item_cardNumber")
    val cardNumber: String,
    @ColumnInfo(name = "item_priority")
    val priority: Int,
    @ColumnInfo(name = "item_color")
    val color: Int,
    @ColumnInfo(name = "item_icon")
    val icon: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}