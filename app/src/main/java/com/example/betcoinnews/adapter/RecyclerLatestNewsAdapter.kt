package com.example.betcoinnews.adapter


import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.betcoinnews.databinding.ItemListLatestNewsBinding
import com.example.betcoinnews.latestnewsfragment.LatestNewsFragment
import com.example.betcoinnews.latestnewsfragment.LatestNewsViewModel
import com.example.betcoinnews.model.NewsModel
import com.example.betcoinnews.operationroomdb.AppDataBase
import com.example.betcoinnews.response.Article
import com.example.betcoinnews.util.UtilBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class RecyclerLatestNewsAdapter (private val dataSet: List<Article> , var onClickItem : OnClick ) : RecyclerView.Adapter<RecyclerLatestNewsAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemListLatestNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        var checkBoxArray = SparseBooleanArray()
        // fun initialize inter face
        fun initialize( viewHolder: ViewHolder , dataSet: Article , action : OnClick){
            action.onClickItem(viewHolder , dataSet , adapterPosition)

            var latestNewsFragment = LatestNewsViewModel()

            binding.togButtSave.isChecked = checkBoxArray.get( adapterPosition , false)
            binding.togButtSave.setOnClickListener {
                if(!checkBoxArray.get(adapterPosition , false)){

                    binding.togButtSave.isChecked = true
                    checkBoxArray.put( adapterPosition , true)

                    latestNewsFragment.insertNewsToRomDb( itemView.context ,  dataSet.title , dataSet.description , dataSet.urlToImage )

                }else{

                    binding.togButtSave.isChecked = false
                    checkBoxArray.put( adapterPosition , false)

                    latestNewsFragment.deleteItemsFormRoomBd(itemView.context , dataSet.title)
                }
            }

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
        fun onClickItem( viewHolder: ViewHolder , dataSet: Article , position: Int)
    }
}