package com.yuraev.profile.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yuraev.profile.ui.editScreen.EditScreen
import com.yuraev.profile.ui.editScreen.camera.CameraScreen
import com.yuraev.profile.ui.editScreen.storage.StorageScreen
import com.yuraev.profile.ui.mainscreen.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object Profile

@Serializable
object Face

@Serializable
object Menu

@Serializable
object EditProfile

@Serializable
object Storage

@Serializable
object Camera

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Menu", Menu, Icons.Default.Menu),
    TopLevelRoute("Face", Face, Icons.Default.Face),
    TopLevelRoute("Profile", Profile, Icons.Default.AccountCircle),

    )

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        NavHost(navController, startDestination = Profile) {
            composable<Profile> {
                MainScreen(onEditClick = { navController.navigate(EditProfile) })
            }
            composable<Menu> {
                Box() {}
            }
            composable<Face> {
                Box() {}
            }
            composable<EditProfile> {
                EditScreen(
                    onBack = { navController.navigate(Profile) },
                    onDone = { navController.navigate(Profile) },
                    goToStorage = { navController.navigate(Storage) },
                    goToCamera = { navController.navigate(Camera) }
                )
            }
            composable<Storage> {
                StorageScreen(returnToEditScreen = { navController.navigate(EditProfile) })
            }
            composable<Camera>{
                CameraScreen(returnToProfileScreen = { navController.navigate(EditProfile) })
            }

        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(modifier = modifier) {
        topLevelRoutes.forEach { topLevelRoute ->
            NavigationBarItem(
                selected = currentDestination?.route == topLevelRoute.route::class.qualifiedName,
                onClick = { navController.navigate(topLevelRoute.route) },
                icon = { Icon(topLevelRoute.icon, contentDescription = null) }
            )
        }
    }
}
