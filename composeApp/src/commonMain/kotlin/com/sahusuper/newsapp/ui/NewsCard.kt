package com.sahusuper.newsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.sahusuper.newsapp.appNavigation.Routes
import com.sahusuper.newsapp.data.modal.Article
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource


@Composable
fun ArcitleCard(
    item: Article,
    backStack: MutableList<Routes>
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .shadow(elevation = 8.dp)
        ,
        shape = RoundedCornerShape(12.dp)

        ,
        onClick = {
            backStack.add(Routes.Deatils(item = item))
        }

    ) {


        SubcomposeAsyncImage(
            model = item.image_url,
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,

            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .height(200.dp),

            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(28.dp),
                        strokeWidth = 2.dp,
                        color = Color.Gray,   // iOS gray tone
                        trackColor = Color.Red,

                    )

                }
            },

            error = {
                Image(
                    painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "error",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        )




        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = item.title.toString(), style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily.SansSerif
            ))

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
                    , verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = item.source_icon,
                        contentDescription = "source_icon",

                        modifier = Modifier.size(32.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Text(text = item.source_name.toString(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Justify
                        )
                        )
                }

                Text(text = item.pubDate.toString(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily.SansSerif
                    ))

            }


        }


    }
}