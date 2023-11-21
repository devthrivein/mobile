package com.example.thrivein.data.dummy

import com.example.thrivein.data.model.ThriveInServiceCategory

object ServiceCategoryDummy {

    val dummyService = listOf<ThriveInServiceCategory>(
        ThriveInServiceCategory(
            id = "1",
            title = "Online Solutions",
            description = "Online Solution",
            iconUrl = "https://static.vecteezy.com/system/resources/previews/015/153/132/original/boost-startup-3d-icon-design-png.png",
            color = "F46590",
        ),
        ThriveInServiceCategory(
            id = "2",
            title = "Offline Solutions",
            description = "Offline Solution",
            iconUrl = "https://static.vecteezy.com/system/resources/previews/010/996/073/original/3d-rendering-market-icon-on-transparent-background-png.png",
            color = "A69BFB",
        ),
        ThriveInServiceCategory(
            id = "3",
            title = "Hybrid Solutions",
            description = "Hybrid Solution",
            iconUrl = "https://static.vecteezy.com/system/resources/previews/010/987/222/original/3d-illustration-of-digital-marketing-business-png.png",
            color = "4FC3FC",
        ),
        ThriveInServiceCategory(
            id = "4",
            title = "Detect Your Store",
            description = "Detect your store",
            iconUrl = "https://cdn3d.iconscout.com/3d/premium/thumb/camera-3485077-2914595.png?f=webp",
            color = "FFA258",
        ),
    )

}