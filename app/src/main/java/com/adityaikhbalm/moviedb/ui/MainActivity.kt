package com.adityaikhbalm.moviedb.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.adityaikhbalm.libraries.abstraction.extensions.viewBinding
import com.adityaikhbalm.libraries.utility.Utility.convertDpToPixel
import com.adityaikhbalm.moviedb.R
import com.adityaikhbalm.moviedb.databinding.ActivityMainBinding
import com.adityaikhbalm.moviedb.databinding.AppBarBinding
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private var currentNavController: LiveData<NavController>? = null
    private var flagPast: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAppBar()
        setNavContainer()
        setSelectedMenu()
    }

    private fun setAppBar() {
        val appBar = AppBarBinding.bind(binding.root)
        setSupportActionBar(appBar.searchToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setNavContainer() {
        val navGraphIds = listOf(R.navigation.home, R.navigation.popular, R.navigation.favorite)

        val controller = binding.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navigationContainer,
            intent = intent
        )

        controller.observe(this) { navController ->
            setupActionBarWithNavController(navController)
        }
        currentNavController = controller
    }

    private fun setSelectedMenu() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    bottomMenuAnimate()
                    flagPast = 1
                }
                R.id.navigation_popular -> {
                    bottomMenuAnimate()
                    flagPast = 2
                }
                R.id.navigation_favorite -> {
                    bottomMenuAnimate()
                    flagPast = 3
                }
                else -> null
            } != null
        }
    }

    private fun bottomMenuAnimate() {
        setMenuAnimate(300, 0)

        val timer = 400 + (100 * flagPast).absoluteValue

        Handler(Looper.getMainLooper()).postDelayed(
            {
                setMenuAnimate(500, 60.convertDpToPixel)
            },
            timer.toLong()
        )
    }

    private fun setMenuAnimate(time: Long, size: Int) {
        ValueAnimator.ofInt(binding.bottomNavigationLight.measuredHeight, size).apply {
            addUpdateListener { valueAnimator ->
                val layoutParams = binding.bottomNavigationLight.layoutParams
                layoutParams.height = valueAnimator.animatedValue as Int
                binding.bottomNavigationLight.layoutParams = layoutParams
            }
            duration = time
            start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("position", flagPast)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        flagPast = savedInstanceState.getInt("position")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }
}
