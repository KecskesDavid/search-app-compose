package com.example.searchappcompose.app.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.searchappcompose.R
import com.example.searchappcompose.app.core.util.DateUtil
import com.example.searchappcompose.domain.model.news.NewsInfo

@Composable
fun NewsCard(
    newsInfo: NewsInfo, onNewsClick: (String) -> Unit, onFavoritesIconClick: (NewsInfo) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onNewsClick(newsInfo.url) },
    ) {
        AsyncImage(
            model = newsInfo.imageUrl,
            contentDescription = null,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(82.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.8f)
        ) {
            Text(
                text = DateUtil.toNewsCardTimestamp(newsInfo.datePublished),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 10.sp,
                modifier = Modifier
                    .alpha(alpha = 0.3f)
                    .padding(top = 4.dp),
            )
            Text(
                text = newsInfo.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(painterResource(
            if (newsInfo.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_outline
            }
        ),
            contentDescription = "Favorites icon",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(32.dp)
                .weight(0.15f)
                .clickable { onFavoritesIconClick(newsInfo) })
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsCard(newsInfo = NewsInfo(
        id = "5969253591358973234",
        title = "Taylor Swift shares dates and venues of adddd",
        url = "https://www.geo.tv/latest/451796-taylor-swift-shares-dates-and-venues-of-the-eras-tour",
        description = "Taylor Swift shares dates and venues of The Eras Tour",
        body = "Web Desk\n" + "Saturday Nov 12, 2022\n" + "Taylor Swift on Friday shared the details of her upcoming tour before presales for all shows start on November 15.\n" + "The global pop sensation earlier this month announced that she was launching a tour for the first time in five years, with several U.S. stadium concert dates confirmed for 2023 and international stops to be revealed later.\n" + "The 32-year-old American singer-songwriter released her 10th studio album \"Midnights\" on Oct. 21, and by Tuesday its soaring popularity had made her the first artist to claim all top 10 spots on the Billboard 100 in the song chart's 64-year history.\n" + "Her 2023 tour will feature the new album as well as being a retrospective of her prolific and storied career. In an Instagram post, Swift said the tour - dubbed \"The Eras Tour\" - would be \"a journey through the musical eras of my career (past and present!)\".",
        snippet = "<b><b>Taylor Swift</b></b> shares dates and venues of The Eras Tour. Web Desk Saturday Nov 12, 2022 <b><b>Taylor Swift</b></b> on Friday shared the details of her upcoming tour ...",
        datePublished = "2022-11-12T00:28:17",
        imageUrl = "https://www.geo.tv/assets/uploads/updates/2022-11-12/451796_053338_updates.jpg",
        imageThumbnail = "5969253591358973234",
    ), onNewsClick = {}, onFavoritesIconClick = {})
}