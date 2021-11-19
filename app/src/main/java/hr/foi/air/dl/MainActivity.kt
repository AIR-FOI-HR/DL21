package hr.foi.air.dl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.repository.DataRepository

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

    }
}