package com.route.e_commerce.screens.main.product_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.route.domain.base.Resource
import com.route.domain.entity.product.ProductEntity
import com.route.e_commerce.R
import com.route.e_commerce.ui.theme.blue2
import com.route.e_commerce.ui.theme.colorOfStroke

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    navController: NavHostController,
    searchQuery: String
) {
    val productsState by viewModel.products.collectAsState()
    LaunchedEffect(searchQuery) {
        viewModel.searchProducts(searchQuery)
    }
    when (productsState) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (productsState as Resource.Error).errorMessage, color = Color.Red)
            }
        }
        is Resource.Success -> {
            val products = (productsState as Resource.Success).data ?: emptyList()
            Scaffold(
                containerColor = Color.Transparent,
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(products.size) { index ->
                            ProductCard(
                                product = products[index],
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
        else -> {}
    }
}

@Composable
fun ProductCard(
    product: ProductEntity,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Card(
        modifier = modifier.fillMaxWidth().height(280.dp).border(
            width = 3.dp,
            color = colorOfStroke,
            shape = RoundedCornerShape(15.dp)
        ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageCover ?: ""),
                contentDescription = "product_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            var isFavorite by remember { mutableStateOf(false) }
            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = { isFavorite = it },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .background(Color.White, CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isFavorite) R.drawable.ic_favourite
                        else R.drawable.ic_heart
                    ),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)

        ) {
            Text(
                text = product.title ?: "No Title",
                color = blue2,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                )
            Text(
                text = "EGP ${product.price ?: 0}",
                color = blue2,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400

            )
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Review (${product.rating?: 0})",
                    color = blue2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
                Image(painter = painterResource(R.drawable.ic_star),
                    contentDescription = "Rating",modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Image(painter = painterResource(R.drawable.ic_plus_circle),
                    contentDescription = "add product",modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            product.id?.let { id ->
                                navController.navigate("productDetails/$id")
                            }
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    ProductListScreen( searchQuery = "",navController = rememberNavController())
}
