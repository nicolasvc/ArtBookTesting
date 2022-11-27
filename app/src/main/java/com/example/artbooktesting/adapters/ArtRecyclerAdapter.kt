package com.example.artbooktesting.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import com.example.artbooktesting.databinding.ArtRowBinding
import com.example.artbooktesting.db.entities.ArtEntity
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<ArtEntity>() {

        override fun areItemsTheSame(oldItem: ArtEntity, newItem: ArtEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArtEntity, newItem: ArtEntity): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffUtil)

    var arts: List<ArtEntity>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ArtRecyclerAdapter(
            LayoutInflater.from(parent.context).inflate(
                R.layout.art_row,
                parent,
                false
            ),
            glide
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArtRecyclerAdapter -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ArtEntity>) {
        differ.submitList(list)
    }

    class ArtRecyclerAdapter
    constructor(
        itemView: View,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ArtRowBinding.bind(itemView)

        fun bind(item: ArtEntity) = with(itemView) {
            binding.apply {
                artRowNameText.text = itemView.context.getString(R.string.template_name,item.name)
                artistRowNameText.text = itemView.context.getString(R.string.template_artist,item.artistName)
                artYearRowNameText.text =itemView.context.getString(R.string.template_year,item.year.toString())
                glide.load(item.imageUrl).into(artRowImageView)
            }


        }
    }


}
