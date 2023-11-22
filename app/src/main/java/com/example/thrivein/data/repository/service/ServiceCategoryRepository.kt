package com.example.thrivein.data.repository.service

import com.example.thrivein.data.dummy.ServiceCategoryDummy
import com.example.thrivein.data.model.ThriveInServiceCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceCategoryRepository @Inject constructor() {

    private val serviceCategories = mutableListOf<ThriveInServiceCategory>()


    init {
        if (serviceCategories.isEmpty()) {
            for (thriveInService in ServiceCategoryDummy.dummyService) {
                serviceCategories.add(thriveInService)
            }
        }
    }


    fun getAllService(): Flow<List<ThriveInServiceCategory>> {
        return flowOf(serviceCategories)
    }

}