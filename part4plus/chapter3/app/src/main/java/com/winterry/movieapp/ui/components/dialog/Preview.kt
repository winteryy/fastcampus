package com.winterry.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.winterry.movieapp.R
import com.winterry.movieapp.ui.models.buttons.LeadingIconData
import com.winterry.movieapp.ui.models.dialog.DialogButton
import com.winterry.movieapp.ui.theme.MovieAppTheme

@Preview
@Composable
fun AlertDialog() {
    MovieAppTheme {
        DialogPopup.Alert(
            title = "Title",
            bodyText = "blah blah blah ",
            buttons = listOf(
                DialogButton.UnderlinedText("Okay") {}
            )
        )
    }
}

@Preview
@Composable
fun DefaultDialog() {
    MovieAppTheme {
        DialogPopup.Default(
            title = "Title",
            bodyText = "blah blah blah ",
            buttons = listOf(
                DialogButton.Primary("OPEN") {},
                DialogButton.SecondaryBorderless("CANCEL") {}
            )
        )
    }
}

@Preview
@Composable
fun RatingDialog() {
    MovieAppTheme {
        DialogPopup.Rating(
            movieName = "The Lord of the Rings: The Two Towers",
            rating = 7.5f,
            buttons = listOf(
                DialogButton.Primary(
                    title = "OPEN",
                    leadingIconData = LeadingIconData(
                        iconDrawable = R.drawable.ic_send,
                        iconContentDescription = null
                    )
                ) {},
                DialogButton.Secondary("CANCEL") {}
            )
        )
    }
}