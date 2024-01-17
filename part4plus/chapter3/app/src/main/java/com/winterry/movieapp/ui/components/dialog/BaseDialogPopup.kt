package com.winterry.movieapp.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.winterry.movieapp.ui.components.dialog.components.button.DialogButtonsColumn
import com.winterry.movieapp.ui.components.dialog.components.content.DialogContentWrapper
import com.winterry.movieapp.ui.components.dialog.components.title.DialogTitleWrapper
import com.winterry.movieapp.ui.models.dialog.DialogButton
import com.winterry.movieapp.ui.models.dialog.DialogContent
import com.winterry.movieapp.ui.models.dialog.DialogText
import com.winterry.movieapp.ui.models.dialog.DialogTitle
import com.winterry.movieapp.ui.theme.MovieAppTheme
import com.winterry.movieapp.ui.theme.Paddings

@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent: DialogContent? = null,
    buttons: List<DialogButton>? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Paddings.none
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dialogTitle?.let {
                DialogTitleWrapper(it)
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = Paddings.xLarge,
                        end = Paddings.xLarge,
                        bottom = Paddings.xLarge
                    )
            ) {
                dialogContent?.let {
                    DialogContentWrapper(it)
                }
                buttons?.let { DialogButtonsColumn(it) }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview() {
    MovieAppTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Header("TITLE"),
            dialogContent = DialogContent.Large(
                DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde ")
            ),
            buttons = listOf(
                DialogButton.Primary("Okay") {

                }
            )
        )
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview2() {
    MovieAppTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large("TITLE"),
            dialogContent = DialogContent.Default(
                DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde ")
            ),
            buttons = listOf(
                DialogButton.Secondary("Okay") {},
                DialogButton.UnderlinedText("Cancel") {},
            )
        )
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview3() {
    MovieAppTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large("TITLE"),
            dialogContent = DialogContent.Rating(
                DialogText.Rating(
                    text = "Jurassic Park",
                    rating = 8.2f
                )
            ),
            buttons = listOf(
                DialogButton.Primary("Okay") {},
                DialogButton.Secondary("Cancel") {},
            )
        )
    }
}

