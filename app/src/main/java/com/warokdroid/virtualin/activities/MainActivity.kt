package com.warokdroid.virtualin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.warokdroid.virtualin.R
import com.warokdroid.virtualin.fragments.HomeFragment
import com.warokdroid.virtualin.fragments.ProfileFragment
import com.warokdroid.virtualin.fragments.PromotionsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null)
            navView.selectedItemId = R.id.navigation_home
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            val fragmentManager: FragmentManager
            val fragmentTransaction: FragmentTransaction
            val fragment: Fragment
            when (it.itemId) {
                R.id.navigation_home -> {
                    fragment = HomeFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_home, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_promotion -> {
                    fragment = PromotionsFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_home, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                else -> {
                    fragment = ProfileFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_home, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
        }
}
