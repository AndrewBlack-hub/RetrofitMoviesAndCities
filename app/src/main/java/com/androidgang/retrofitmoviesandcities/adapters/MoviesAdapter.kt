package com.androidgang.retrofitmoviesandcities.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.retrofitmoviesandcities.R
import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MoviesAdapter(private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    private val moviesList: ArrayList<MoviesResponse.Result> = arrayListOf()

    interface OnMovieClickListener {
        fun onMovieClick(movie: MoviesResponse.Result)
    }

    var onMovieClickListener: OnMovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_cell, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie: MoviesResponse.Result = moviesList[position]
        holder.bind(movie)
        holder.loadImage(movie)
        holder.itemView.setOnClickListener {
            onMovieClickListener?.onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int = moviesList.size

    inner class MoviesHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewMovie: TextView = view.findViewById(R.id.movie_text)
        private val textViewCountry: TextView = view.findViewById(R.id.movie_age)
        private val textViewAge: TextView = view.findViewById(R.id.movie_country)
        private val imageMovie: ImageView = view.findViewById(R.id.movie_image)

        fun bind(movie: MoviesResponse.Result) {
            textViewMovie.text = movie.title
            textViewCountry.text = movie.country
            textViewAge.text = movie.ageRestriction
        }

        fun loadImage(movie: MoviesResponse.Result) {
            if (!(movie.poster?.image.isNullOrEmpty())) {
                val imageUrl = movie.poster?.image
                Glide.with(context)
                    .load(imageUrl)
                    .into(imageMovie)
            }
        }
    }

    fun setList(list: ArrayList<MoviesResponse.Result>?) {
        moviesList.clear()
        list?.let { moviesList.addAll(it) }
        notifyDataSetChanged()
    }
}