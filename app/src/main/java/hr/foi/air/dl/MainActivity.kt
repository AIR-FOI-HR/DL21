package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import hr.foi.air.database.data.DbDataSource
import hr.foi.air.database.data.MockData
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.repositry.DataRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //val btnShowData = findViewById<Button>(R.id.btn_show_data)
        binding.btnShowData.setOnClickListener{
            displayData(binding)
        }
    }


    fun displayData(binding: ActivityMainBinding)
    {
        var repository = DataRepository()

        var discounts : List<String>? = repository.getDiscountNames(this)

        //Prikaz podataka na zaslovnu
        if (discounts != null) {
            binding.lstDiscounts.adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, discounts)

            //Dodatne mogućnosti prikaza
            for (disc in discounts!!) {
                //privremeni ispis na zaslon
                Toast.makeText(this, disc, Toast.LENGTH_LONG).show()

                //ispis u log
                Log.d("AIR", disc)
            }

            //hiding empty message
            if (!discounts.isEmpty())
                binding.emptyMessage.isVisible = false
        }
    }
}