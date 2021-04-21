package com.example.betcoinnews.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.betcoinnews.R
import com.example.betcoinnews.adapter.RecyclerLatestNewsAdapter
import com.example.betcoinnews.databinding.ActivityMainBinding
import com.example.betcoinnews.latestnewsfragment.LatestNewsFragment
import com.example.betcoinnews.response.Article

class MainActivity : AppCompatActivity(){

    lateinit var binding            : ActivityMainBinding
    lateinit var latestNewsFragment : LatestNewsFragment
    lateinit var array              : ArrayList<Article>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        latestNewsFragment = LatestNewsFragment()
        array = ArrayList()
        val navHostFragment : NavHostFragment   = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController   : NavController     = navHostFragment.navController

        // Set up for action bar back ground
        //supportActionBar!!.setBackgroundDrawable(ContextCompat.getDrawable(this , R.drawable.app_gradinet_color_background))

        var appBarConfiguration = AppBarConfiguration(setOf(R.id.viewPagerFragment ,
                                                            R.id.latestNewsFragment ,
                                                            R.id.saveNewsFragment,
                                                            R.id.otherNewsFragment,
                                                            R.id.detailsNewsFragment))

        setupActionBarWithNavController(navController , appBarConfiguration)

        // Control for action bar
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id){

                R.id.splashFragment     -> supportActionBar?.hide()

                else -> supportActionBar?.show()
            }
        }
    }
}