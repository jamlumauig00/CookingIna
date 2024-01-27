package com.jam.cookingina.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.jam.cookingina.Database.AppDatabase
import com.jam.cookingina.Database.CookingRepository
import com.jam.cookingina.Database.CookingViewModel
import com.jam.cookingina.Database.CookingViewModelFactory
import com.jam.cookingina.databinding.ActivityDescBinding
import java.util.Locale

class DescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescBinding
    private lateinit var cookingViewModel: CookingViewModel
    private lateinit var data: String

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val position = intent?.getIntExtra("position", 0)

        val repository = CookingRepository(AppDatabase.getDatabase(this).cookingInaDao())
        cookingViewModel = ViewModelProvider(this, CookingViewModelFactory(repository))[CookingViewModel::class.java]

        // Observe LiveData to get data
        cookingViewModel.allData.observe(this) { datas ->
            // Handle the data here
            Log.e("position", position.toString())
            data = datas[position!!].Category.toString()

            val categoryIconName = datas[position].Category!!.toLowerCase(Locale.ROOT) + "_icon"
            Log.e("drawableName", categoryIconName)

            val resourceId = resources.getIdentifier(categoryIconName, "drawable", this.packageName)
            if (resourceId != 0) {
                binding.icons.setImageResource(resourceId)
            } else {
                // Handle the case where the resource is not found
            }
            binding.title.text = datas[position].Cuisine
            binding.cookingdesc.text = datas[position].Description
            binding.ingDesc.text = datas[position].Ingredients
            binding.howToDesc.text = datas[position].How

        }
    }
}