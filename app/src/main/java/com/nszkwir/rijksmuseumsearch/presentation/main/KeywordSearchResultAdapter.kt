package com.nszkwir.rijksmuseumsearch.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nszkwir.rijksmuseumsearch.R
import com.nszkwir.rijksmuseumsearch.databinding.KeywordSearchResultItemBinding
import com.nszkwir.rijksmuseumsearch.domain.models.ArtObject
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class KeywordSearchResultAdapter
    : RecyclerView.Adapter<KeywordSearchResultAdapter.ViewHolder>() {

    private var artObjects: ArrayList<ArtObject> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<ArtObject>) {
        artObjects.clear()
        artObjects.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artObjects[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            KeywordSearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = artObjects.size

    inner class ViewHolder(
        private val itemBinding: KeywordSearchResultItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(artObject: ArtObject) {

            itemBinding.author.text = artObject.principalOrFirstMaker
            itemBinding.title.text = artObject.title
            itemBinding.longTitle.text = artObject.longTitle
            itemBinding.webLink.text = artObject.webLink

            Picasso.get()
                .load(artObject.headerImageLink)
                .placeholder(R.drawable.ic_close_24)
                .error(R.drawable.ic_close_24)
                .fit()
                .into(itemBinding.headerImage, object : Callback {
                    override fun onSuccess() {
                        // TODO may implement loading feature
                    }

                    override fun onError(e: Exception?) {
                        // TODO may handle the error
                    }
                })

            Picasso.get()
                .load(artObject.webImageLink)
                .placeholder(R.drawable.ic_close_24)
                .error(R.drawable.ic_close_24)
                .fit()
                .into(itemBinding.webImage, object : Callback {
                    override fun onSuccess() {
                        // TODO may implement loading feature
                    }

                    override fun onError(e: Exception?) {
                        // TODO may handle the error
                    }
                })


        }
    }
}