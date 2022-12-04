package com.example.searchappcompose.app.screens.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.searchappcompose.R
import com.example.searchappcompose.app.core.ui.NewsList
import com.example.searchappcompose.app.core.ui.background.AppBackground
import com.example.searchappcompose.app.core.ui.dialog.AppDialog
import com.example.searchappcompose.app.core.ui.loading_overlay.LoadingOverlay
import com.example.searchappcompose.app.screens.navigation.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(), navController: NavHostController
) {
    LaunchedEffect(true) {
        favoritesViewModel.getFavoriteNews()
    }

    val state = favoritesViewModel.state

    AppBackground {
        if (state.isLoading) {
            LoadingOverlay()
        } else if (state.showDeleteDialog) {
            AppDialog(title = stringResource(id = R.string.remove_favorites_dialog_title),
                subTitle = stringResource(id = R.string.remove_favorites_dialog_subtitle),
                confirmButtonText = stringResource(id = R.string.remove_favorites_dialog_confirm_button),
                cancelButtonText = stringResource(id = R.string.remove_favorites_dialog_cancel_button),
                onConfirmButtonClick = {
                    favoritesViewModel.onEvent(FavoritesEvent.OnDeleteDialogConfirm)
                },
                onCancelButtonClick = {
                    favoritesViewModel.onEvent(FavoritesEvent.OnDeleteDialogDismiss)
                })
        }

        NewsList(
            news = state.favorites,
            onNewsClick = { URL ->
                navController.navigate(
                    Screen.DetailScreen.withArguments(
                        URLEncoder.encode(
                            URL, StandardCharsets.UTF_8.toString(),
                        ),
                    ),
                )
            },
            onFavoritesIconClick = {
                favoritesViewModel.onEvent(
                    FavoritesEvent.OnFavoritesClicked(it),
                )
            },
        )
    }
}
