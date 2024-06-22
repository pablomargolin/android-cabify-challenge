package com.example.corenetwork

import com.example.corenetwork.service.CabifyChallengeService
import javax.inject.Inject

class ServiceHelper @Inject constructor(
    private val serviceBuilder: ServiceBuilder
) {
    private val retrofit: CabifyChallengeService by lazy {
        serviceBuilder.createRetrofitService()
    }

    fun getCabifyService(): CabifyChallengeService = retrofit
}