package com.example.thrivein.ui.component.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.utils.hexStringToColor

@Composable
fun HomeGridServiceCategoryView(
    modifier: Modifier = Modifier,
    listCategory: ServiceCategoriesResponse?,
    navigateToListService: (id: String, title: String) -> Unit,
    navigateToScanStore: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.services),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyHorizontalGrid(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            rows = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            userScrollEnabled = false,
        ) {
            items(listCategory?.services ?: arrayListOf(), key = { it?.category ?: "" }) {
                GridItem(
                    navigateToListService = { id, title ->
                        navigateToListService(id, title)
                    },
                    id = it?.category ?: "",
                    title = it?.title ?: "",
                    iconUrl = it?.iconUrl ?: "",
                    color = it?.color ?: "",
                    navigateToScanStore = navigateToScanStore
                )
            }
        }
    }
}

@Composable
fun GridItem(
    modifier: Modifier = Modifier,
    navigateToListService: (id: String, title: String) -> Unit,
    navigateToScanStore: () -> Unit,
    id: String,
    title: String,
    iconUrl: String,
    color: String,
) {
    Box(
        modifier = modifier
            .width(160.dp)
            .background(
                color = hexStringToColor(color),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .clickable {
                if (title.contains("Scan")) {
                    navigateToScanStore()
                } else {
                    navigateToListService(id, title)

                }
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        AsyncImage(
            model = iconUrl,
            contentDescription = title,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun GridItemPreview() {
    GridItem(
        navigateToListService = { id, title ->
        },
        id = "1",
        title = "Online Solutions",
        iconUrl = "",
        color = "A69BFB",
        navigateToScanStore = {},
    )
}

