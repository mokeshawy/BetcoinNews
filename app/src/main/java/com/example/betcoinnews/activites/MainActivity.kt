package com.example.betcoinnews.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.betcoinnews.R
import com.example.betcoinnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        val navHostFragment : NavHostFragment   = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController   : NavController     = navHostFragment.navController

        // Set up for action bar back ground
        //supportActionBar!!.setBackgroundDrawable(ContextCompat.getDrawable(this , R.drawable.app_gradinet_color_background))

       // val appBarConfiguration = AppBarConfiguration(setOf())

        var appBarConfiguration = AppBarConfiguration(setOf(R.id.latestNewsFragment ,
                                                            R.id.saveNewsFragment,
                                                            R.id.otherNewsFragment))
        setupActionBarWithNavController(navController , appBarConfiguration)

        // Control for action bar
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id){

                R.id.splashFragment     -> supportActionBar?.hide()
                R.id.viewPagerFragment  -> supportActionBar!!.hide()

                else -> supportActionBar?.show()
            }
        }
    }
}