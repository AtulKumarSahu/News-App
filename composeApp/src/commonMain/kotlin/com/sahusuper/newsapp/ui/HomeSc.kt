package com.sahusuper.newsapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sahusuper.newsapp.appNavigation.Routes
import com.sahusuper.newsapp.data.modal.NewsRes
import com.sahusuper.newsapp.presentation.NewsVM
import com.sahusuper.newsapp.state.UiState
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.*

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Preview
@Composable
fun HomeSc(
    backStack: MutableList<Routes>,
    newsVM: NewsVM
) {


    val news = newsVM.newsState.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        OutlinedTextField(
            value = newsVM.query.collectAsState().value,
            onValueChange = {newsVM.onQueryChange(it)},
            modifier = Modifier.fillMaxWidth().padding(top = 60.dp),
            placeholder = { Text(text = "Search") },
            maxLines = 1
        )


        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val lists = listOf(
                Type("Latest", Res.drawable.latest_ic,
                    "latest"),
                Type("Crypto", Res.drawable.crypto_ic,"crypto"),
                Type("Archive", Res.drawable.archive_ic,""),
                Type("Source", Res.drawable.source_ic,"sources"),
                Type("Market", Res.drawable.market_ic,"market"),
            )

            LazyRow {
                items(lists) { s ->
                    val isSelected = newsVM.type.collectAsState().value == s.type
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                newsVM.setType(s.type)
                            }
                            .border(
                                border = BorderStroke(1.dp, color = Color.Gray),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = if (isSelected) Color.Red.copy(1f) else Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        Image(
                            painter = painterResource(s.icon),
                            contentDescription = "icon",
                            modifier = Modifier.size(24.dp),
                            alignment = Alignment.Center,
                            colorFilter = ColorFilter.tint(
                                if (isSelected) Color.White else Color.Black
                            )
                            )

                        Text(text = s.title, color = if (isSelected) Color.White else Color.Black)

                    }
                }

            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        when (news.value) {

            is UiState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp)
                    , color = Color.Red,
                    trackColor = Color.LightGray,
                    gapSize = 4.dp

                )

            }

            is UiState.Success -> {

                val list = (news.value as UiState.Success<NewsRes>).data.results

                LazyColumn {
                    items(list) { item ->
                        ArcitleCard(item, backStack)
                    }
                }


            }

            is UiState.Error -> {
                Text(text = "Error")

            }


            else -> {
                Text(text = "Idle")

            }
        }
    }
}


data class Type(
    val title: String,
    val icon: DrawableResource,
    val type: String
)