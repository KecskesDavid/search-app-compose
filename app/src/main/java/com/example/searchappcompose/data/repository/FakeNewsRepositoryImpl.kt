package com.example.searchappcompose.data.repository

import com.example.searchappcompose.data.remote.service.WebSearchApi
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeNewsRepositoryImpl @Inject constructor(
    private val webSearchApi: WebSearchApi
): NewsRepository{

    private val newsInfo = (1..10).map {
        NewsInfo(
            id = "5969253591358973234",
            title = "Taylor Swift shares dates and venues of The Eras Tour",
            url = "https://www.geo.tv/latest/451796-taylor-swift-shares-dates-and-venues-of-the-eras-tour",
            description = "Taylor Swift shares dates and venues of The Eras Tour",
            body = "Web Desk\n" +
                    "Saturday Nov 12, 2022\n" +
                    "Taylor Swift on Friday shared the details of her upcoming tour before presales for all shows start on November 15.\n" +
                    "The global pop sensation earlier this month announced that she was launching a tour for the first time in five years, with several U.S. stadium concert dates confirmed for 2023 and international stops to be revealed later.\n" +
                    "The 32-year-old American singer-songwriter released her 10th studio album \"Midnights\" on Oct. 21, and by Tuesday its soaring popularity had made her the first artist to claim all top 10 spots on the Billboard 100 in the song chart's 64-year history.\n" +
                    "Her 2023 tour will feature the new album as well as being a retrospective of her prolific and storied career. In an Instagram post, Swift said the tour - dubbed \"The Eras Tour\" - would be \"a journey through the musical eras of my career (past and present!)\".",
            snippet = "<b><b>Taylor Swift</b></b> shares dates and venues of The Eras Tour. Web Desk Saturday Nov 12, 2022 <b><b>Taylor Swift</b></b> on Friday shared the details of her upcoming tour ...",
            datePublished = "2022-11-12T00:28:17",
            imageUrl = "https://www.geo.tv/assets/uploads/updates/2022-11-12/451796_053338_updates.jpg",
            imageThumbnail = "5969253591358973234",
        )
    }
    private val news = News(newsInfo)

    override suspend fun getNews(query: String, pageNumber: Int, pageSize: Int): News {
        return news
    }

    override suspend fun getFavorites(): List<NewsInfo> {
        return listOf()
    }

    override suspend fun addToFavorites(newsInfo: NewsInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorites(newsInfo: NewsInfo) {
        TODO("Not yet implemented")
    }
}