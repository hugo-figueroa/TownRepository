package com.android.towndirectory.town.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.towndirectory.R
import com.android.towndirectory.core.utils.ClickedViewItem
import com.android.towndirectory.databinding.InhabitantViewBinding
import com.android.towndirectory.town.models.Inhabitant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions


class TownAdapter<T>(
    private var inhabitants: List<Inhabitant?>,
    private val onActionViewItem: (Any) -> T
) :
    RecyclerView.Adapter<TownAdapter.TownViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val binding: InhabitantViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.inhabitant_view, parent,
            false
        )
        context = parent.context
        return TownViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return inhabitants.size
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        val inhabitant = inhabitants[position]
        holder.binding.inhabitant = inhabitant
        holder.binding.cvInhabitant.setOnClickListener {
            onActionViewItem(ClickedViewItem(it.id, inhabitant))
        }
        // Add Image
        var imageUrl = inhabitant?.imageUrl
        imageUrl = imageUrl?.replace("http", "https")
        setImage(imageUrl, holder)
    }

    private fun setImage(url: String?, holder: TownViewHolder) {
        if (url != null && url.isNotEmpty()) {
            val glideUrl = GlideUrl(
                url, LazyHeaders.Builder()
                    .addHeader(
                        "User-Agent",
                        "Mozilla Firefox Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0."
                    )
                    .build()
            )
            Glide.with(context)
                .load(glideUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions().override(250, 250))
                .into(
                    holder.binding.imageView
                )
        }
    }

    class TownViewHolder(binding: InhabitantViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding: InhabitantViewBinding = binding
    }
}