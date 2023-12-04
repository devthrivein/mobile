package com.example.thrivein.ui.component.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.R
import com.example.thrivein.ui.navigation.NavigationItem
import com.example.thrivein.ui.navigation.Screen
import com.example.thrivein.ui.theme.Gray
import com.example.thrivein.ui.theme.Primary

@Composable
fun BottomBarNavigation(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavigationBar(
        modifier = Modifier
            .shadow(
                elevation = 25.dp,
                spotColor = Primary,
                ambientColor = Color.Blue,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                clip = true
            )
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        contentColor = Color.Transparent,
        containerColor = Color.White,

        ) {

        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        val navigationItems = listOf<NavigationItem>(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = painterResource(id = R.drawable.ic_home),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.history),
                icon = painterResource(id = R.drawable.ic_history),
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(R.string.consultation),
                icon = painterResource(id = R.drawable.ic_consultation),
                screen = Screen.Consultation
            ),
            NavigationItem(
                title = stringResource(R.string.setting),
                icon = painterResource(id = R.drawable.ic_settings),
                screen = Screen.Setting
            ),
        )

        navigationItems.map { item ->

            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    selectedTextColor = Primary,
                    indicatorColor = Color.White,
                    unselectedIconColor = Gray,
                    unselectedTextColor = Gray,
                ),
                onClick = {
                    navHostController.navigate(item.screen.route) {

                        if (item.screen.route == Screen.Home.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        } else {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }


                    }

                },
            )

        }

    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun BottomBarNavigationPreview() {
    BottomBarNavigation(navHostController = rememberNavController())
}