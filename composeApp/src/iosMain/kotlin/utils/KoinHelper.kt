package utils

import di.dataModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import presentation.screens.countries.CountriesViewModel

fun initKoin(){
    startKoin{
        modules(dataModule)
    }
}