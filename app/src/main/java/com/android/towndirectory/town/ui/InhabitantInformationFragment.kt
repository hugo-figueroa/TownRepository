package com.android.towndirectory.town.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.towndirectory.R
import com.android.towndirectory.core.utils.Constants
import com.android.towndirectory.databinding.FragmentInhabitantInformationBinding
import com.android.towndirectory.town.models.Inhabitant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class InhabitantInformationFragment : Fragment() {

    lateinit var inhabitant: Inhabitant

    lateinit var binding: FragmentInhabitantInformationBinding

    companion object {
        fun newInstance(inhabitant: Inhabitant): InhabitantInformationFragment {
            val orderDetailFragment = InhabitantInformationFragment()
            orderDetailFragment.inhabitant = inhabitant
            return orderDetailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_inhabitant_information,
                container,
                false
            )
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        actionBar.title = "Inhabitant"
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.inhabitant = inhabitant
        // Add Image
        var imageUrl = inhabitant.imageUrl
        imageUrl = imageUrl?.replace("http", "https")
        setImage(imageUrl)
        // Add Hair Color
        setHairColor(inhabitant.hairColor)
        // Friends
        setFriends(inhabitant.friends)
        // Professions
        setProfessions(inhabitant.professions)
        return binding.root
    }

    private fun setImage(url: String?) {
        if (url != null && url.isNotEmpty()) {
            Glide.with(context!!)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions().override(250, 250))
                .into(
                    binding.imageView
                )
        }
    }

    private fun setHairColor(hairColor: String?) {
        if (hairColor != null && hairColor.isNotEmpty()) {
            binding.viewHairColor.visibility = View.VISIBLE
            binding.tvLabelHairColor.visibility = View.VISIBLE
            var color: Int
            when (hairColor) {
                Constants.Color.RED -> {
                    binding.viewHairColor.setBackgroundColor(Color.RED)
                }
                Constants.Color.GREEN -> {
                    binding.viewHairColor.setBackgroundColor(Color.GREEN)
                }
                Constants.Color.BLACK -> {
                    binding.viewHairColor.setBackgroundColor(Color.BLACK)
                }
                Constants.Color.GRAY -> {
                    binding.viewHairColor.setBackgroundColor(Color.GRAY)
                }
                Constants.Color.PINK -> {
                    binding.viewHairColor.setBackgroundColor(Color.MAGENTA)
                }
            }
        } else {
            binding.viewHairColor.visibility = View.INVISIBLE
            binding.tvLabelHairColor.visibility = View.INVISIBLE
        }
    }

    private fun setFriends(friends: List<String?>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context!!,
            R.layout.list_item,
            friends
        )
        binding.lvFriends.adapter = adapter
    }

    private fun setProfessions(professions: List<String?>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context!!,
            R.layout.list_item,
            professions
        )
        binding.lvProfessions.adapter = adapter
    }
}