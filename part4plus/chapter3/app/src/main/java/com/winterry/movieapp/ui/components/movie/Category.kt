package com.winterry.movieapp.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winterry.movieapp.ui.theme.MovieAppTheme
import com.winterry.movieapp.ui.theme.Paddings

@Composable
fun CategoryRow() {
    Column {
        CategoryTitle("Action")
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = Paddings.large
            )
        ) {
//            itemsIndexed()

            item {
                MovieItem()
            }
            item {
                MovieItem()
            }
            item {
                MovieItem()
            }
            item {
                MovieItem()
            }
            item {
                MovieItem()
            }
        }
    }
}

@Composable
fun CategoryTitle(s: String) {
    Text(
        text = "Action",
        modifier = Modifier
            .padding(
                vertical = Paddings.large,
                horizontal = Paddings.extra
            )
    )
}

@Preview
@Composable
fun CategoryRowPreview() {
    MovieAppTheme {
        CategoryRow()
    }
}