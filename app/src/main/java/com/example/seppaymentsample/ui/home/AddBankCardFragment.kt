package com.example.seppaymentsample.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.seppaymentsample.R
import com.example.seppaymentsample.data.room.entity.BankCard
import kotlinx.android.synthetic.main.dialog_add_bank_card_item.*

class AddBankCardFragment(var addDialogListener: AddDialogListener) :
    Fragment(R.layout.fragment_add_bank_card) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnAdd.setOnClickListener {
            val bankCardNumber = etBankCardNumber.text.toString()
            val bankCardName = etBankName.text.toString()

            if (bankCardNumber.isEmpty() || bankCardName.isEmpty()) {
                Toast.makeText(context, "please enter all the information...", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val item = BankCard(
                bankCardName,
                bankCardNumber,
                0,
                R.color.yellow_200,
                R.drawable.ic_baseline_delete_24
            )

            addDialogListener.onAddButtonClicked(item)

            // dismiss():
            requireActivity().onBackPressed()
        }


    }
}