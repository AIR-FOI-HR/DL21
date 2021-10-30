package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import hr.foi.air.database.data.DataRepository
import hr.foi.air.database.data.MockData
import hr.foi.air.dl.databinding.ActivityMainBinding

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
        //Unos podataka u bazu, ako je potrebno
        MockData.mockData(this)

        var discounts : List<String>? = DataRepository(this).getDiscountNames()

        //Prikaz podataka na zaslovnu
        if (discounts != null) {
            binding.lstDiscounts.adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, discounts)
        }
    }
}