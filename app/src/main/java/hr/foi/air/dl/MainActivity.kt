package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import hr.foi.air.database.data.MockData
import hr.foi.air.dl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mockSomeData()
        //setContentView(R.layout.activity_main)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //val btnShowData = findViewById<Button>(R.id.btn_show_data)
        binding.btnShowData.setOnClickListener{
        }
    }

    fun mockSomeData()
    {
        MockData.mockData(this)
    }
}