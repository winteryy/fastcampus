package com.winterry.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import com.winterry.movieapp.ui.models.dialog.DialogButton
import com.winterry.movieapp.ui.models.dialog.DialogContent
import com.winterry.movieapp.ui.models.dialog.DialogText
import com.winterry.movieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Default(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Default(title),
        dialogContent = DialogContent.Default(
            DialogText.Default(bodyText)
        ),
        buttons = buttons
    )
}

