package com.example.betcoinnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.betcoinnews.databinding.ItemListLatestNewsBinding
import com.example.betcoinnews.databinding.ItemListOtherDetailsBinding
import com.example.betcoinnews.response.Article
import com.squareup.picasso.Picasso

class RecyclerAppleNewsAdapter (private val dataSet: List<Article> , var onClickAppleNews: OnClickAppleNews) : RecyclerView.Adapter<RecyclerAppleNewsAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemListOtherDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun initialize( viewHolder: ViewHolder , dataSet: Article , action : OnClickAppleNews){
            action.onClickAppleNews(viewHolder , dataSet , adapterPosition)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        var myViewHolder = ViewHolder(ItemListOtherDetailsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

        return myViewHolder

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.binding.tvLatestNewsItem.text = dataSet[position].title
        Picasso.get().load(dataSet[position].urlToImage).into(viewHolder.binding.ivItemLatestNews)

        viewHolder.initialize(viewHolder , dataSet[position] , onClickAppleNews)

    }

    // Return the size of your dataSet (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // Interface on click
    interface OnClickAppleNews{
        fun onClickAppleNews(viewHolder: ViewHolder , dataSet: Article , position: Int)
    }
}