package com.igorwojda.showcase.feature.activate.presentation.activatelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.igorwojda.showcase.feature.activate.R
import com.igorwojda.showcase.feature.activate.presentation.activatelist.recycleview.ActivateAdapter
import com.igorwojda.showcase.feature.activate.presentation.activatelist.recycleview.ActivateAdapter2
import com.igorwojda.showcase.feature.activate.presentation.activatelist.recycleview.GridAutofitLayoutManager
import com.igorwojda.showcase.library.base.presentation.extension.observe
import com.igorwojda.showcase.library.base.presentation.fragment.InjectionFragment
import kotlinx.android.synthetic.main.fragment_activate.*
import org.kodein.di.generic.instance


class ActivateListFragment : InjectionFragment(R.layout.fragment_activate) {

    private val viewModel: ActivateListViewModel by instance()

    private val albumAdapter: ActivateAdapter by instance()
    private val albumAdapter2: ActivateAdapter2 by instance()

    private val stateObserver = Observer<ActivateListViewModel.ViewState> {
        albumAdapter.albums = it.albums
    }
    private val stateObserver2 = Observer<ActivateListViewModel.ViewState> {
        albumAdapter2.albums = it.albums.asReversed()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = checkNotNull(context)

        recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen._120dp).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth, LinearLayout.HORIZONTAL, false
                )
            adapter = albumAdapter
        }

        recyclerView2.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.displayMetrics.widthPixels
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth, LinearLayout.HORIZONTAL, false
                )
            adapter = albumAdapter2.apply {
                albums.asReversed()
            }
        }

        recyclerView3.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.displayMetrics.widthPixels
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth, LinearLayout.HORIZONTAL, false
                )
            adapter = albumAdapter2
        }

        observe(viewModel.stateLiveData, stateObserver)
        observe(viewModel.stateLiveData, stateObserver2)
        viewModel.loadData()
    }
}
