package com.example.betcoinnews.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betcoinnews.databinding.ItemListLatestNewsBinding
import com.example.betcoinnews.model.NewsModel
import com.example.betcoinnews.savenewsfragment.SaveNewsViewModel
import com.squareup.picasso.Picasso

class RecyclerSaveNewsAdapter (private val dataSet: List<NewsModel>, var onClickItem : OnClick ) : RecyclerView.Adapter<RecyclerSaveNewsAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemListLatestNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        var saveNewsViewModel = SaveNewsViewModel()
        // fun initialize inter face
        fun initialize(viewHolder: ViewHolder, dataSet: NewsModel, action : OnClick){
            action.onClickItem(viewHolder , dataSet , adapterPosition)

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        var myViewHolder = ViewHolder(ItemListLatestNewsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

        return myViewHolder

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.binding.tvLatestNewsItem.text = dataSet[position].title
        Picasso.get().load(dataSet[position].urlToImage).into(viewHolder.binding.ivItemLatestNews)

        // Call fun initialize
        viewHolder.initialize(viewHolder , dataSet[position] , onClickItem)

    }

    // Return the size of your dataSet (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // Interface for onClick item
    interface OnClick{
        fun onClickItem(viewHolder: ViewHolder, dataSet: NewsModel, position: Int)
    }
}