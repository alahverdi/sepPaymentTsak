package com.example.seppaymentsample.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seppaymentsample.R
import com.example.seppaymentsample.data.repositories.BankCardRepository
import com.example.seppaymentsample.data.room.AppDatabase
import com.example.seppaymentsample.data.room.entity.BankCard
import com.example.seppaymentsample.data.room.entity.NewsFeed
import com.example.seppaymentsample.data.room.entity.PracticalService
import com.example.seppaymentsample.ui.home.adapters.AddBankCardItemDialog
import com.example.seppaymentsample.ui.home.adapters.BankCardAdapter
import com.example.seppaymentsample.ui.home.adapters.NewsFeedAdapter
import com.example.seppaymentsample.ui.home.adapters.PracticalServiceAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.banner_frequent_payment.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.File

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init viewModelFactory
        val database = AppDatabase(requireActivity())
        val repository = BankCardRepository(database)
        val factory = BankCardViewModelFactory(repository)

        // init viewModel
        val viewModel = ViewModelProviders.of(this, factory).get(BankCardViewModel::class.java)


        // init BandCard recyclerView
        val adapterBankCard = BankCardAdapter(listOf(), viewModel, requireActivity())
        recyclerViewBankCard.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
        recyclerViewBankCard.adapter = adapterBankCard

        // display all BankCard items from database
        viewModel.getAllBankCards().observe(requireActivity(), Observer {
            adapterBankCard.items = it
            adapterBankCard.notifyDataSetChanged()

            if (adapterBankCard.items.isEmpty()) {
                //Toast.makeText(requireActivity(), "is Empty!", Toast.LENGTH_SHORT).show()
                cvBankCardBanner.visibility = View.VISIBLE
                recyclerViewBankCard.visibility = View.GONE
            } else {
                cvBankCardBanner.visibility = View.GONE
                recyclerViewBankCard.visibility = View.VISIBLE
            }

        })


        // handle addButton for bankCard item into db
        btnAddBankCardItem.setOnClickListener {
            //Toast.makeText(requireActivity(), "clicked", Toast.LENGTH_SHORT).show()
            // show add bankCard Fragment:
            loadFragment(AddBankCardFragment(object : AddDialogListener{
                override fun onAddButtonClicked(item: BankCard) {
                    viewModel.upsert(item)
                }
            }))

            // show Dialog:
            /*AddBankCardItemDialog(
                requireActivity(),
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: BankCard) {
                        viewModel.upsert(item)
                    }
                }).show()*/
        }

        setupPracticalServicesRecyclerView()
        setupNewsFeedRecyclerView()


        linearContainer.setOnClickListener {
            val snack = Snackbar.make(
                it,
                "ذخیره پرداخت ها در حال ساخت",
                Snackbar.LENGTH_LONG
            )
            snack.show()
        }

        cvBankCardBanner.setOnClickListener {
            loadFragment(AddBankCardFragment(object : AddDialogListener{
                override fun onAddButtonClicked(item: BankCard) {
                    viewModel.upsert(item)
                }
            }))
        }


    } // end onViewCreated

    ////////////////////////////////////////////////////////////////////////////
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.newsNavHostFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun setupPracticalServicesRecyclerView() {
        // init practicalServices recyclerView
        val adapterPracticalServices = PracticalServiceAdapter(getPracticalServicesList())
        recyclerPracticalServices.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
            //GridLayoutManager(context, 4)

        recyclerPracticalServices.adapter = adapterPracticalServices
    }

    private fun setupNewsFeedRecyclerView() {
        // init newsFeed recyclerView
        val adapterNewsFeed = NewsFeedAdapter(getNewsFeedItemsList())
        recyclerNewsFeed.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)

        recyclerNewsFeed.adapter = adapterNewsFeed
    }

    private fun getPracticalServicesList(): List<PracticalService> {
        val listItems = listOf(
            PracticalService(
                "کارت به کارت",
                0,
                ContextCompat.getColor(requireActivity(), R.color.service_item_1),
                R.drawable.ic_service_card
            ),
            PracticalService(
                "شارژ",
                0,
                ContextCompat.getColor(requireActivity(), R.color.service_item_2),
                R.drawable.ic_service_charge
            ),
            PracticalService(
                "اینترنت",
                0,
                ContextCompat.getColor(requireActivity(), R.color.service_item_3),
                R.drawable.ic_service_internet
            ),
            PracticalService(
                "قبض",
                0,
                ContextCompat.getColor(requireActivity(), R.color.service_item_4),
                R.drawable.ic_service_bill_true
            )
        )

        return listItems
    }

    private fun getNewsFeedItemsList(): List<NewsFeed> {
        val listItems = listOf(
            NewsFeed(
                "انتقال وجه لحظه ای",
                "با سرویس پل در لحظه پول انتقال بده",
                0,
                ContextCompat.getColor(requireActivity(), R.color.news_feed_item_1),
                R.drawable.ic_banner_news_feed
            ),
            NewsFeed(
                "سیستم پرداخت",
                "با سرویس پل در لحظه پول انتقال بده",
                0,
                ContextCompat.getColor(requireActivity(), R.color.news_feed_item_2),
                R.drawable.ic_banner_news_feed
            ),
            NewsFeed(
                "پرداخت الکترونیک",
                "با سرویس پل در لحظه پول انتقال بده",
                0,
                ContextCompat.getColor(requireActivity(), R.color.news_feed_item_1),
                R.drawable.ic_banner_news_feed
            ),
            NewsFeed(
                "دانش مالی",
                "با سرویس پل در لحظه پول انتقال بده",
                0,
                ContextCompat.getColor(requireActivity(), R.color.news_feed_item_2),
                R.drawable.ic_banner_news_feed
            )
        )

        return listItems
    }


    private fun databaseFileExists(): Boolean {
        return try {
            File(requireActivity().getDatabasePath("MyInfoDatabase.db").absolutePath).exists()
        } catch (e: Exception) {
            false
        }
    }


} // end HomeFragment Class