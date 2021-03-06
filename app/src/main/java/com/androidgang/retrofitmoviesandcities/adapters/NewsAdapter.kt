package com.androidgang.retrofitmoviesandcities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidgang.retrofitmoviesandcities.R
import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.androidgang.retrofitmoviesandcities.model.NewsResponse
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private val newsList: ArrayList<NewsResponse.Result> = arrayListOf()

    interface OnNewsClickListener {
        fun onNewsClick(news: NewsResponse.Result)
    }

    var onNewsClickListener: OnNewsClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news: NewsResponse.Result = newsList[position]
        holder.bind(news)
        holder.loadImage(news)
        holder.itemView.setOnClickListener {
            onNewsClickListener?.onNewsClick(news)
        }
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewNews = view.findViewById<TextView>(R.id.news_text)
        private val imageNews = view.findViewById<ImageView>(R.id.news_image)

        fun bind(news: NewsResponse.Result) {
            textViewNews.text = news.title
        }

        fun loadImage(news: NewsResponse.Result) {
            if (!(news.images.isNullOrEmpty())) {
                val urlImage = news.images?.get(0)?.image
                Glide.with(context)
                    .load(urlImage)
                    .into(imageNews)
            }
        }
    }

    fun setList(list: ArrayList<NewsResponse.Result>?) {
        newsList.clear()
        list?.let { newsList.addAll(it) }
        notifyDataSetChanged()
    }
}