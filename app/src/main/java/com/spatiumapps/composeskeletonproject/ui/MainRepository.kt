package com.spatiumapps.composeskeletonproject.ui

import com.spatiumapps.composeskeletonproject.data.api.NetworkService
import com.spatiumapps.composeskeletonproject.data.model.Response
import javax.inject.Inject

class MainRepository
    @Inject
    constructor(
        private val restaurantService: NetworkService
    ) {
        suspend fun getRestaurants(): Response {
            return restaurantService.getNetworkRequest()
        }
    }
