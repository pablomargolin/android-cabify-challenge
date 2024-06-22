package com.example.corenetwork

import com.example.corenetwork.service.CabifyChallengeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ServiceHelperTest {

    private val serviceBuilder: ServiceBuilder = mockk()
    private lateinit var serviceHelper: ServiceHelper

    @Before
    fun setUp(){
        serviceHelper = ServiceHelper(serviceBuilder)
    }

    @Test
    fun `get cabify service should return service from service builder`(){
        every { serviceBuilder.createRetrofitService() } returns mockk()

        val service = serviceHelper.getCabifyService()

        verify { serviceBuilder.createRetrofitService() }
        assertNotNull(service)
    }
}