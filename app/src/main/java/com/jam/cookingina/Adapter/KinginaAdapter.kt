package com.jam.cookingina.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jam.cookingina.Database.CookingModel
import com.jam.cookingina.R
import com.jam.cookingina.databinding.LayoutKinginaBinding

class KinginaAdapter(var mainlist: List<CookingModel>, var adapterclick : AdapterOnClick) :
    RecyclerView.Adapter<KinginaAdapter.ViewHolder>() {

    class ViewHolder(binding: LayoutKinginaBinding, var itemClick: AdapterOnClick) :
        RecyclerView.ViewHolder(binding.root) {
        var bindings: LayoutKinginaBinding = binding
        @SuppressLint("DiscouragedApi")
        fun bindIdea(dataPor: CookingModel) {
            itemView.apply {
                bindings.title.text = dataPor.Cuisine
                bindings.subtitle.text = dataPor.Description
                val categoryIconName = dataPor.Category!!.toLowerCase() + "_icon"
                Log.e("drawableName", categoryIconName)

                val resourceId = resources.getIdentifier(categoryIconName, "drawable", context.packageName)

                if (resourceId != 0) {
                    bindings.icons.setImageResource(resourceId)
                } else {
                    // Handle the case where the resource is not found
                }

                if(adapterPosition == 3){
                    bindings.view.visibility = View.GONE
                }

            }
            itemView.setOnClickListener {
                itemClick.onclick(dataPor.ID.toInt())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_kingina, parent, false
            ), adapterclick
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mainlist[position]
        holder.bindIdea(data)

    }

    override fun getItemCount(): Int {
        return mainlist.size
    }
}
