package com.example.seppaymentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seppaymentsample.data.repositories.BankCardRepository
import com.example.seppaymentsample.data.room.AppDatabase
import com.example.seppaymentsample.ui.home.BankCardViewModel
import com.example.seppaymentsample.ui.home.BankCardViewModelFactory
import com.example.seppaymentsample.ui.home.HomeFragment
import com.example.seppaymentsample.ui.home.adapters.BankCardAdapter
import com.example.seppaymentsample.ui.sep_festival.SepFestivalFragment
import com.example.seppaymentsample.ui.sep_services.SepServicesFragment
import com.example.seppaymentsample.ui.user_transaction.UserTransactionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup bottomNavigation
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }

    //navController = Navigation.findNavController(this, R.id.sepNavHostFragment)
    //setupWithNavController(bottomNavigationView, navController)

    /*bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
    bottomNav.setOnNavigationItemReselectedListener {
        when (it.itemId) {
            R.id.home -> {
                loadFragment(HomeFragment())
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
                return@setOnNavigationItemReselectedListener
            }
            R.id.services -> {
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
                loadFragment(SepServicesFragment())
                return@setOnNavigationItemReselectedListener
            }
            R.id.transaction -> {
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
                loadFragment(UserTransactionFragment())
                return@setOnNavigationItemReselectedListener
            }
            R.id.festival -> {
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
                loadFragment(SepFestivalFragment())
                return@setOnNavigationItemReselectedListener
            }
        }
    }*/

}