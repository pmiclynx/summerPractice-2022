package com.summer.practice.tvtracker.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.ItemLastMovieSimpleBinding
import com.summer.practice.tvtracker.databinding.ItemMovieSimpleBinding
import com.summer.practice.tvtracker.domain.Movie

class MovieAdapter(
    list: List<Movie>,
    private val itemClickListener: ItemClickListener
): RecyclerView.Adapter<MovieAdapter.GenericViewHolder>() {

    private val items: MutableList<Movie> = list.toMutableList()

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }
    abstract class GenericViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        abstract fun bind(movie: Movie, itemClickListener: ItemClickListener)

    }

    class MovieViewHolder(view: View, private val binding: ItemMovieSimpleBinding): GenericViewHolder(binding.root) {
        override fun bind(
            movie: Movie,
            itemClickListener: ItemClickListener,
        ) {
            binding.textViewMovieTitle.text = movie.name

            Glide.with(itemView)
                .load(movie.backdropPath)
                .transform(CenterCrop())
                .placeholder(R.drawable.series_image)
                .into(binding.imageViewMovie)
            binding.root.setOnClickListener { itemClickListener.onItemClicked(movie.id) }
        }
    }
    class LastMovieViewHolder(view: View,private val binding: ItemLastMovieSimpleBinding): GenericViewHolder(binding.root) {
        override fun bind(
            movie: Movie,
            itemClickListener: ItemClickListener,
        ) {
            binding.textViewMovieTitle.text = movie.name

            Glide.with(itemView)
                .load(movie.backdropPath)
                .transform(CenterCrop())
                .placeholder(R.drawable.series_image)
                .into(binding.imageViewMovie)
            binding.root.setOnClickListener { itemClickListener.onItemClicked(movie.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        if(viewType == items.size-1){
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_last_movie_simple, parent, false)
            return LastMovieViewHolder(view, ItemLastMovieSimpleBinding.bind(view)
            )
        }
        else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_simple, parent, false)
            return MovieViewHolder(view, ItemMovieSimpleBinding.bind(view))
        }
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val popularItem = items[position]
        holder.bind(popularItem,itemClickListener)
    }
}