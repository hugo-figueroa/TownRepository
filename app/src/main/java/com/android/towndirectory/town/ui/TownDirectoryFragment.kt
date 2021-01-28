package com.android.towndirectory.town.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.towndirectory.BaseActivity
import com.android.towndirectory.R
import com.android.towndirectory.core.TownApplication
import com.android.towndirectory.core.utils.*
import com.android.towndirectory.databinding.FragmentTownDirectoryBinding
import com.android.towndirectory.town.models.Inhabitant
import com.android.towndirectory.town.models.Town
import com.android.towndirectory.town.ui.adapter.TownAdapter
import com.android.towndirectory.town.viewmodel.TownsViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class TownDirectoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        requireActivity().getViewModel<TownsViewModel>(
            viewModelFactory
        )
    }
    lateinit var actionBar: ActionBar
    lateinit var binding: FragmentTownDirectoryBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // LiveData objects
        viewModel.inhabitantList.subscribe(viewLifecycleOwner, this::manageInhabitantListResponse)
        // Get Inhabitants
        viewModel.getTowns()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Dagger
        (requireActivity().applicationContext as TownApplication).appComponent.townDirectoryComponent()
            .create()
            .inject(this)
        // Binding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_town_directory, container, false)
        // ActionBar
        actionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        actionBar.title = "Town Directory"
        actionBar.setDisplayHomeAsUpEnabled(false)
        // Inhabitants
        binding.rvTown.isNestedScrollingEnabled = true
        binding.rvTown.layoutManager =
            GridLayoutManager(requireActivity(), 2)
        return binding.root
    }

    private fun manageInhabitantListResponse(res: Resource<Any>) {
        when (res.status) {
            Resource.STATUS_LOADING -> {
                (requireActivity() as BaseActivity).showProgress()
            }
            Resource.STATUS_SUCCESS -> {
                (requireActivity() as BaseActivity).hideProgress()
                val town: Town = (res.data as Town)
                val townAdapter = TownAdapter(town.inhabitants, ::onActionViewItem)
                binding.rvTown.adapter = townAdapter
            }
            Resource.STATUS_ERROR -> {
                (requireActivity() as BaseActivity).hideProgress()
                var message: String =
                    context!!.getString(R.string.exception) + context!!.getString(R.string.error_getting_inhabitants)
                when (res.data as ErrorEntity) {
                    is ErrorEntity.Network -> message =
                        context!!.getString(R.string.error_config_NoNetworkConnection)
                    is ErrorEntity.FailureResponse -> message

                    else -> message
                }
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun onActionViewItem(any: Any) {
        // I think all clicks need to be handle in the fragment, in this way we have a centralized
        // place for clicks in our screen, even if they happend on viewholders
        val actionViewItem = any as ClickedViewItem
        when (actionViewItem.id) {
            R.id.cvInhabitant -> {
                val fragment =
                    InhabitantInformationFragment.newInstance(actionViewItem.obj as Inhabitant)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        fragment,
                        InhabitantInformationFragment::class.java.simpleName
                    ).addToBackStack(InhabitantInformationFragment::class.java.simpleName).commit()
            }
        }
    }
}