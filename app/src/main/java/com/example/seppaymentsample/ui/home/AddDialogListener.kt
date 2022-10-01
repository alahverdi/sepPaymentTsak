package com.example.seppaymentsample.ui.home

import com.example.seppaymentsample.data.room.entity.BankCard

interface AddDialogListener {
    fun onAddButtonClicked(item: BankCard)
}