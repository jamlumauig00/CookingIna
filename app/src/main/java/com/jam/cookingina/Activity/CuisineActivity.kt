package com.jam.cookingina.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jam.cookingina.Adapter.AdapterOnClick
import com.jam.cookingina.Adapter.KinginaAdapter
import com.jam.cookingina.Database.AppDatabase
import com.jam.cookingina.Database.CookingModel
import com.jam.cookingina.Database.CookingRepository
import com.jam.cookingina.Database.CookingViewModel
import com.jam.cookingina.Database.CookingViewModelFactory
import com.jam.cookingina.databinding.ActivityCuisineBinding

class CuisineActivity : AppCompatActivity(), AdapterOnClick {

    private lateinit var binding: ActivityCuisineBinding
    private lateinit var cookingViewModel: CookingViewModel
    private lateinit var rview: RecyclerView
    lateinit var arrayAdapter: KinginaAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuisineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent?.getStringExtra("data")
        Log.e("dataargs", data.toString())

        binding.navbar.text = data
        rview = binding.cookingRview
        rview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val repository =
            CookingRepository(AppDatabase.getDatabase(this).cookingInaDao())
        cookingViewModel = ViewModelProvider(
            this,
            CookingViewModelFactory(repository)
        )[CookingViewModel::class.java]

        cookingViewModel.allData.observe(this) { datas ->
            // Handle the data here
            Log.e("cookingViewModel", datas.toString())
            val filteredItems = ArrayList<CookingModel>()

            for (value in datas) {
                if (value.Category == data) {
                    Log.e("VALUE", value.Category.toString())
                    filteredItems.add(value)
                }
            }
            arrayAdapter = KinginaAdapter(filteredItems, this)
            rview.adapter = arrayAdapter
            arrayAdapter.notifyDataSetChanged()
        }

    }

    override fun onclick(position: Int) {
        val intent = Intent(this, DescActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}