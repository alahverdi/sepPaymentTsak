package com.example.seppaymentsample.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seppaymentsample.R
import com.example.seppaymentsample.data.room.entity.NewsFeed
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.banner_news_feed.view.*
import kotlinx.android.synthetic.main.banner_news_feed.view.tvTittle
import kotlinx.android.synthetic.main.item_practical_service.view.*

class NewsFeedAdapter(var items: List<NewsFeed>) :
    RecyclerView.Adapter<NewsFeedAdapter.NewsFeedItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.banner_news_feed, parent, false)

        return NewsFeedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsFeedItemViewHolder, position: Int) {
        val currentNewsFeedItem = items[position]
        holder.itemView.tvTittle.text = currentNewsFeedItem.tittle
        holder.itemView.tvDescription.text = currentNewsFeedItem.description
        holder.itemView.ivNewsFeed.setImageResource(currentNewsFeedItem.icon)
        holder.itemView.linearContainer.setBackgroundColor(currentNewsFeedItem.color)

        holder.itemView.linearContainer.setOnClickListener {
            val snack = Snackbar.make(
                it,
                "${currentNewsFeedItem.tittle} در دست ساخت",
                Snackbar.LENGTH_LONG
            )
            snack.show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class NewsFeedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}