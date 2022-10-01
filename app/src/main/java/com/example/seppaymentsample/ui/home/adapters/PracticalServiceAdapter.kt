package com.example.seppaymentsample.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.seppaymentsample.R
import com.example.seppaymentsample.data.room.entity.PracticalService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_practical_service.view.*

class PracticalServiceAdapter(
    var items: List<PracticalService>
) : RecyclerView.Adapter<PracticalServiceAdapter.PracticalServiceItemViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PracticalServiceItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_practical_service, parent, false)

        return PracticalServiceItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PracticalServiceItemViewHolder, position: Int) {
        val currentPracticalServiceItem = items[position]
        holder.itemView.tvTittle.text = currentPracticalServiceItem.tittle
        holder.itemView.ivIcon.setImageResource(currentPracticalServiceItem.icon)
        holder.itemView.cardViewItem.setCardBackgroundColor(currentPracticalServiceItem.color)

        holder.itemView.cardViewItem.setOnClickListener {
            //Toast.makeText(it.context, "clicked!", Toast.LENGTH_SHORT).show()
            val snack = Snackbar.make(
                it,
                "${currentPracticalServiceItem.tittle} در دست ساخت",
                Snackbar.LENGTH_LONG
            )
            snack.show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class PracticalServiceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}