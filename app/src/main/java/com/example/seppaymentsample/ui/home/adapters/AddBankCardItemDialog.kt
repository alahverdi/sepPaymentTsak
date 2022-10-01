package com.example.seppaymentsample.ui.home.adapters

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.seppaymentsample.R
import com.example.seppaymentsample.data.room.entity.BankCard
import com.example.seppaymentsample.ui.home.AddDialogListener
import kotlinx.android.synthetic.main.dialog_add_bank_card_item.*

class AddBankCardItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_bank_card_item)

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

            dismiss()
        }


        btnCancel.setOnClickListener {
            cancel()
        }


    } // end onCreate

} // end dialog class