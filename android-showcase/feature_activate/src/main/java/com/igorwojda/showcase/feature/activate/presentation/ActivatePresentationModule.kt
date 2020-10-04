package com.igorwojda.showcase.feature.activate.presentation

import androidx.fragment.app.Fragment
import com.igorwojda.showcase.feature.activate.MODULE_NAME
import com.igorwojda.showcase.feature.activate.presentation.activatelist.ActivateListViewModel
import com.igorwojda.showcase.feature.activate.presentation.activatelist.recycleview.ActivateAdapter
import com.igorwojda.showcase.feature.activate.presentation.activatelist.recycleview.ActivateAdapter2
import com.igorwojda.showcase.library.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<ActivateListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { ActivateListViewModel(instance(), instance()) }
    }

    bind() from singleton { ActivateAdapter() }

    bind() from singleton { ActivateAdapter2() }
}
