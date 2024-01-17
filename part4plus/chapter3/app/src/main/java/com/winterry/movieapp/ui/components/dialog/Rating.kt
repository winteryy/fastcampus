package com.winterry.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import com.winterry.movieapp.ui.models.dialog.DialogButton
import com.winterry.movieapp.ui.models.dialog.DialogContent
import com.winterry.movieapp.ui.models.dialog.DialogText
import com.winterry.movieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate your Score!"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}