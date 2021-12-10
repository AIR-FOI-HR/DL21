package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.navigation.NavigationView
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.fragments.ListViewFragment
import hr.foi.air.dl.managers.DataPresenterManager
import hr.foi.air.dl.recyclerview.StoreParent
import hr.foi.air.dl.recyclerview.StoreRecyclerAdapter
import hr.foi.air.dl.repository.DataRepository
import hr.foi.air.dl.repository.LoadDataListener

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var currentFragment : ListViewFragment? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeLayout()
        initializeDataPresenterManager()
        initializeAds()
    }

    private fun initializeLayout()
    {
        setSupportActionBar(binding.layoutMain.toolbar)
        var drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.layoutMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun initializeDataPresenterManager()
    {
        DataPresenterManager
            .getInstance()
            .setDependancies(this, binding.navView, binding.drawerLayout)
            .initializeDataPresenters()
            .showMainDataPresenter()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //R.id.menu_about -> Log.d("AirAir", "Menu item About")
            //else -> TODO()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private var mInterstitialAd: InterstitialAd? = null

    fun initializeAds()
    {
        MobileAds.initialize(this) {}

        var adRequest = AdRequest.Builder().build()
        var context = this

        //release: Map View Interstitial ca-app-pub-6991391774275847/7461615137
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
                mInterstitialAd?.show(context)
            }
        })

    }
}