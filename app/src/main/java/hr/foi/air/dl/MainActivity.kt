package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.fragments.ListViewFragment
import hr.foi.air.dl.recyclerview.StoreParent
import hr.foi.air.dl.recyclerview.StoreRecyclerAdapter
import hr.foi.air.dl.repository.DataRepository
import hr.foi.air.dl.repository.LoadDataListener

class MainActivity : AppCompatActivity() {
    private var currentFragment : ListViewFragment? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeLayout()
        showMainFragment()
        loadDataToFragment()
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
    }

    private fun showMainFragment()
    {
        currentFragment = ListViewFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.layoutMain.contentMain.mainFragment.id, currentFragment!!)
            .commit()
    }

    fun loadDataToFragment()
    {
        if (currentFragment != null)
            DataRepository().loadData(this, currentFragment!!)
    }
}