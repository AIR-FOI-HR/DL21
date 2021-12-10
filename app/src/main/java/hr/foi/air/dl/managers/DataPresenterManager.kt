package hr.foi.air.dl.managers

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import hr.foi.air.core.data.DataPresenter
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.R
import hr.foi.air.dl.fragments.ListViewFragment
import hr.foi.air.dl.repository.DataRepository
import hr.foi.air.dl.repository.LoadDataListener
import hr.foi.air.map.MapViewFragment

class DataPresenterManager private constructor(){
    companion object
    {
        private var instance : DataPresenterManager = DataPresenterManager()
        fun getInstance() : DataPresenterManager
        {
            return instance
        }
    }

    private var dataPresenters : ArrayList<DataPresenter> = ArrayList()
    private lateinit var activity: AppCompatActivity
    private lateinit var navView : NavigationView
    private lateinit var drawerLayout : DrawerLayout

    fun setDependancies(activity: AppCompatActivity, navView: NavigationView, drawerLayout: DrawerLayout) : DataPresenterManager
    {
        this.activity = activity
        this.navView = navView
        this.drawerLayout = drawerLayout

        return this
    }

    fun initializeDataPresenters() : DataPresenterManager
    {
        addDataPresenter(ListViewFragment())
        addDataPresenter(MapViewFragment())

        /*if (isMapBought)
            addDataPresenter(MapViewFragment())
        else
            addDataPresenter(MapBuyFragment())
        */
        return this
    }

    private fun addDataPresenter(dataPresenter: DataPresenter) {
        dataPresenters.add(dataPresenter)
        addPresenterToMenu(dataPresenter)
    }

    private fun addPresenterToMenu(dataPresenter: DataPresenter) {
        val id = dataPresenters.indexOf(dataPresenter)
        val context = activity.applicationContext

        navView.menu
            .add(R.id.dynamic_group, id, id+1, dataPresenter.getName(context))
            .setIcon(dataPresenter.getIcon(context))
            .setCheckable(true)
            .setOnMenuItemClickListener {
                showDataPresenter(dataPresenter)
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
    }

    private fun showDataPresenter(dataPresenter: DataPresenter) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, dataPresenter.getFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack("")
            .commit()

        DataRepository().loadData(activity.applicationContext, object : LoadDataListener
        {
            override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
                dataPresenter.setData(stores, discounts)
            }
        })
    }

    fun showMainDataPresenter()
    {
        showDataPresenter(dataPresenters[0])
    }
}