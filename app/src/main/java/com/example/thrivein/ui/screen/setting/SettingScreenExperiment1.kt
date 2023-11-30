package com.example.thrivein.ui.screen.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.ui.component.item.SettingItemExperiment1
import com.example.thrivein.ui.component.navigation.BottomBarNavigation
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreenExperimen1(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigateToProfile: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBarNavigation(navHostController = navHostController)
        },
    ) { innerpadding ->
        val scrollState = rememberScrollState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Box(
                modifier = modifier
                    .background(Background)
                    .padding(innerpadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(scrollState)
                ) {
                    TopBarSetting(
                        name = "Fika",
                        iconUrl = "https://th.bing.com/th/id/OIP.SdB_qPhbKS73WKzeP25VOgHaK9?rs=1&pid=ImgDetMain"
                    ) {
                        navigateToProfile
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    //your store
                    Text(
                        text = stringResource(id = R.string.your_store),
                        modifier = Modifier.offset(x = 6.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    SettingItemExperiment1(
                        id = "1",
                        title = stringResource(id = R.string.store_profile),
                        icon = painterResource(id = R.drawable.ic_store_profile)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    //services
                    Text(
                        text = stringResource(id = R.string.services),
                        modifier = Modifier.offset(x = 6.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    SettingItemExperiment1(
                        id = "2",
                        title = stringResource(id = R.string.FAQ),
                        icon = painterResource(id = R.drawable.ic_faq)
                    )
                    SettingItemExperiment1(
                        id = "3",
                        title = stringResource(id = R.string.terms_and_conditions),
                        icon = painterResource(id = R.drawable.ic_terms_and_conditions)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    //Authentication
                    Text(
                        text = stringResource(id = R.string.authentication),
                        modifier = Modifier.offset(x = 6.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    SettingItemExperiment1(
                        id = "4",
                        title = stringResource(id = R.string.delete_account),
                        icon = painterResource(id = R.drawable.ic_delete_account)
                    )
                    SettingItemExperiment1(
                        id = "5",
                        title = stringResource(id = R.string.logout),
                        icon = painterResource(id = R.drawable.ic_logout)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun SettingScreenExperimen1Preview() {
    SettingScreenExperimen1(navHostController = rememberNavController(), navigateToProfile = {})
}

@Composable
fun TopBarSetting(
    name: String,
    iconUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .background(Primary),
                model = iconUrl,
                contentDescription = stringResource(id = R.string.profile),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.width(20.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 3,
            )
            Spacer(Modifier.width(150.dp))
//            IconButton(
//                onClick = {
//                    onClick()
//                },
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = stringResource(R.string.go_to_profile),
//                )
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarSettingPreview() {
    TopBarSetting(
        name = "Fika",
        iconUrl = "https://th.bing.com/th/id/OIP.SdB_qPhbKS73WKzeP25VOgHaK9?rs=1&pid=ImgDetMain",
        onClick = {}
    )
}
