package com.winterry.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.winterry.movieapp.R
import com.winterry.movieapp.ui.models.dialog.DialogButton
import com.winterry.movieapp.ui.models.dialog.DialogContent
import com.winterry.movieapp.ui.models.dialog.DialogText
import com.winterry.movieapp.ui.models.dialog.DialogTitle

object DialogPopup

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(
            title
        ),
        dialogContent = DialogContent.Large(
            DialogText.Default(bodyText)
        ),
        buttons = buttons
    )
}