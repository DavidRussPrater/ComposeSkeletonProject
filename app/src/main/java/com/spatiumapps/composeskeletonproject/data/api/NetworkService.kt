package com.spatiumapps.composeskeletonproject.data.api

import com.spatiumapps.composeskeletonproject.data.model.Response
import retrofit2.http.GET

interface NetworkService {
    @GET(".")
    suspend fun getNetworkRequest(): Response
}
