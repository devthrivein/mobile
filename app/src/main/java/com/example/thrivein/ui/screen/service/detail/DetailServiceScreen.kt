package com.example.thrivein.ui.screen.service.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.SeeAllButton
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String = "",
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(title = id, navigateBack = navigateBack)
        },
        floatingActionButton = {
            ThriveInButton(
                onClick = { /*TODO*/ },
                label = stringResource(R.string.consult),
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 24.dp),
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Background,
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding)
        ) {
            item {
                AsyncImage(
                    model = stringResource(id = R.string.dummy_image),
                    contentDescription = title,
                    modifier = Modifier
                        .height(166.dp)
                        .fillMaxWidth()
                        .background(Primary),
                    contentScale = ContentScale.Crop,
                )
            }

            item {
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........",
                    modifier = Modifier.padding(24.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                SeeAllButton(label = stringResource(R.string.portfolio), onClickButton = {})
            }

            item {
                LazyHorizontalGrid(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 16.dp
                        ),
                    rows = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    userScrollEnabled = false,
                ) {
                    items(count = 4) {
                        AsyncImage(
                            model = stringResource(id = R.string.dummy_image),
                            contentDescription = title,
                            modifier = Modifier
                                .width(160.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Primary),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailServiceScreenPreview() {
    DetailServiceScreen(id = "1", title = "Social Media Management", navigateBack = {})
}