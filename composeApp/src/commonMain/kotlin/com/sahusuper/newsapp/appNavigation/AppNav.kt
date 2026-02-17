package com.sahusuper.newsapp.appNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sahusuper.newsapp.data.modal.Article
import com.sahusuper.newsapp.data.repo.NewsRepo
import com.sahusuper.newsapp.presentation.NewsVM
import com.sahusuper.newsapp.ui.DetailsSC
import com.sahusuper.newsapp.ui.HomeSc

@Composable
fun AppNav() {
    val backStack = remember { mutableStateListOf<Routes>(Routes.Home) }
    val newsRepo = NewsRepo()
    val vm  = NewsVM(newsRepo)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider{
            entry<Routes.Home> {
                HomeSc (newsVM = vm, backStack = backStack )
            }

            entry<Routes.Deatils> { a->
                val item = a.item
                DetailsSC(item =item) {
                    backStack.removeLastOrNull()
                }
            }
        },
    )
}

sealed interface Routes {
    data object Home : Routes
    data class Deatils(val item: Article) : Routes
}
