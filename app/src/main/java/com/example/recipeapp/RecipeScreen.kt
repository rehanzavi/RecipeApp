package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.recipeapp.ui.theme.MainViewModel

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 viewState: MainViewModel.RecipeState,
                 navigateToDetails: (Category) -> Unit) {
    val recipeModel: MainViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error!=null -> {
                Text("Error Occurred")
            }
            else -> {
                CategoryScreen(viewState.list, navigateToDetails = navigateToDetails)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetails: (Category) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(category, navigateToDetails)
        }
    }
}

@Composable
fun CategoryItem(item: Category,
                 navigateToDetails: (Category) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)
        .fillMaxSize()
        .clickable {
            navigateToDetails(item)
        },
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        AsyncImage(
            model = item.strCategoryThumb,
            contentDescription = "Image of ${item.strCategory}",
            modifier = Modifier
                .size(120.dp)
                .aspectRatio(1f)
        )

        Text(text =item.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp)
        )
    }
}