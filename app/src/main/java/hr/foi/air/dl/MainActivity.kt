package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.recyclerview.StoreParent
import hr.foi.air.dl.recyclerview.StoreRecyclerAdapter
import hr.foi.air.dl.repository.DataRepository
import hr.foi.air.dl.repository.LoadDataListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayData(binding)
    }


    fun displayData(binding: ActivityMainBinding)
    {
        var repository = DataRepository()
        var context = this

        repository.loadData(this, object : LoadDataListener {
            override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
                if (stores != null) {
                    val parentList : ArrayList<StoreParent> = ArrayList()
                    for (s in stores)
                        parentList.add(StoreParent(s, discounts!!))
                    
                    //prikaz podataka
                    binding.mainRecycler.adapter = StoreRecyclerAdapter(context, parentList)
                    binding.mainRecycler.layoutManager = LinearLayoutManager(context)

                    //hiding empty message
                    if (!stores.isEmpty())
                        binding.emptyMessage.isVisible = false
                }
            }
        } )
    }
}