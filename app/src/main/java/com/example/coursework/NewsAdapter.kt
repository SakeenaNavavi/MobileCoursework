package com.example.coursework

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context, private val news_list: List<News>?) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_news_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNews = news_list?.get(position)

        currentNews?.let { news ->
            val imgUri = Uri.parse(news.getUrlToImage())
            Glide.with(context).load(imgUri).into(holder.img)

            holder.title.text = news.getTitle()
            holder.description.text = news.getDescription()

            holder.itemView.setOnClickListener {
                val bookInfoUri = Uri.parse(news.getUrl())
                val websiteIntent = Intent(Intent.ACTION_VIEW, bookInfoUri)
                context.startActivity(websiteIntent)
            }
        }
    }

    override fun getItemCount(): Int = news_list?.size ?: 0

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val title: TextView = itemView.findViewById(R.id.titlee)
        val description: TextView = itemView.findViewById(R.id.description)
    }
}