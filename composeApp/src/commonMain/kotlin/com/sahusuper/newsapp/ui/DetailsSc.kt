package com.sahusuper.newsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.sahusuper.newsapp.data.modal.Article
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailsSC(
    item: Article,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(text = item.title.toString(), style = TextStyle(
           color = Color.Black,
           fontSize = 18.sp,
           fontWeight = FontWeight.W600,
           fontFamily = FontFamily.SansSerif
       ))
        Spacer(Modifier.height(16.dp))
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
                        color = Color.Gray,
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

        Spacer(Modifier.height(16.dp))

        Text(text = item.description.toString(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.SansSerif,

            )
            )


    }
}
