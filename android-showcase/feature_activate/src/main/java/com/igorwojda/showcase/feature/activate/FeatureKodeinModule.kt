package com.igorwojda.showcase.feature.activate

import com.igorwojda.showcase.app.feature.KodeinModuleProvider
import com.igorwojda.showcase.feature.activate.data.dataModule
import com.igorwojda.showcase.feature.activate.domain.domainModule
import com.igorwojda.showcase.feature.activate.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Activate"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
