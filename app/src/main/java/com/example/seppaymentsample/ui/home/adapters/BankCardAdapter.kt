package com.example.seppaymentsample.ui.home.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.seppaymentsample.MainActivity
import com.example.seppaymentsample.R
import com.example.seppaymentsample.data.room.entity.BankCard
import com.example.seppaymentsample.ui.home.AddBankCardFragment
import com.example.seppaymentsample.ui.home.AddDialogListener
import com.example.seppaymentsample.ui.home.BankCardViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.button_more_item.view.*
import kotlinx.android.synthetic.main.item_add_bank_card.view.*
import kotlinx.android.synthetic.main.item_bank_card.view.*
import kotlinx.android.synthetic.main.item_bank_card.view.ivDeleteItem
import kotlinx.android.synthetic.main.item_bank_card.view.linearContainer
import kotlinx.android.synthetic.main.item_bank_card.view.tvNumber
import kotlinx.android.synthetic.main.item_bank_card.view.tvTittle

class BankCardAdapter(
    var items: List<BankCard>,
    private val viewModel: BankCardViewModel,
    val context: Context
) : RecyclerView.Adapter<BankCardAdapter.BankCardItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankCardItemViewHolder {
        /*val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bank_card, parent, false)
        return BankCardItemViewHolder(view)*/

        val view: View
        if (viewType == R.layout.item_bank_card) {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_bank_card, parent, false)
        } else {
            view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.button_more_item, parent, false)
        }

        return BankCardItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankCardItemViewHolder, position: Int) {

        /*val currentBankCardItem = items[position]
        holder.itemView.tvTittle.text = currentBankCardItem.tittle
        holder.itemView.tvNumber.text = currentBankCardItem.cardNumber

        holder.itemView.ivDeleteItem.setOnClickListener {
            viewModel.delete(currentBankCardItem)
        }*/

        if (position == items.size) {
            holder.itemView.btn_load_more.setOnClickListener {
                //Toast.makeText(it.context, "افزودن کارت جدید!", Toast.LENGTH_SHORT).show()

                // open add bank card fragment
                loadFragment(AddBankCardFragment(object : AddDialogListener {
                    override fun onAddButtonClicked(item: BankCard) {
                        viewModel.upsert(item)
                    }

                }))

            }
        } else {
            val currentBankCardItem = items[position]
            holder.itemView.tvTittle.text = currentBankCardItem.tittle
            holder.itemView.tvNumber.text = currentBankCardItem.cardNumber
            holder.itemView.ivDeleteItem.setOnClickListener {
                viewModel.delete(currentBankCardItem)
            }
            holder.itemView.linearContainer.setOnClickListener {
                val snack = Snackbar.make(
                    it,
                    "ویرایش کارت ${currentBankCardItem.tittle} در دست ساخت",
                    Snackbar.LENGTH_LONG
                )
                snack.show()
            }
        }


    }

    override fun getItemCount(): Int {

        return items.size + 1

    }

    ////////////////////////////////////////////////////////////////////////
    inner class BankCardItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    ////////////////////////////////////////////////////////////////////////

    override fun getItemViewType(position: Int): Int {
        if (position == items.size) {
            return R.layout.button_more_item
        } else {
            return R.layout.item_bank_card
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.newsNavHostFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}