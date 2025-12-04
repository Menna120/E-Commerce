package com.route.e_commerce.screens.product_details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.route.domain.base.Resource
import com.route.domain.entity.productDetails.DataEntity
import com.route.e_commerce.R
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.ui.theme.Black
import com.route.e_commerce.ui.theme.blue2
import com.route.e_commerce.ui.theme.blue3
import com.route.e_commerce.ui.theme.colorOfStroke
import com.route.e_commerce.ui.theme.green
import com.route.e_commerce.ui.theme.lightBlue
import com.route.e_commerce.ui.theme.lightRed
import com.route.e_commerce.ui.theme.orange
import com.route.e_commerce.ui.theme.yellow
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavHostController,
    productId: String?
) {
    LaunchedEffect(productId) {
        productId?.let { viewModel.getProductDetails(it) }
    }

    val productDetailsState by viewModel.productDetails.collectAsState()
    when (productDetailsState) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (productDetailsState as Resource.Error).errorMessage, color = Color.Red)
            }
        }
        is Resource.Success -> {
            val productDetails = (productDetailsState as Resource.Success).data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp)
            ) {
                TopAppBar(navController =navController)
                ProductImagesSlider(images = productDetails?.data?.images ?: emptyList())
                ProductContent(product = productDetails?.data!!)
            }
        }
        else -> {}
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier,navController : NavHostController) {
    val appNavController = LocalAppNavController.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow_back),
            contentDescription = "left_arrow",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Text(
            stringResource(R.string.product_details),
            fontWeight = FontWeight.W500,
            color = blue2,
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )
        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "Search",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    navController.navigate(Screen.ProductList(subCategoryId = ""))
                }
        )
        Image(
            painter = painterResource(R.drawable.ic_shopping_cart),
            contentDescription = "ShoppingCart",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    appNavController.navigate(Screen.Cart)
                }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImagesSlider(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 3.dp,
                    color = colorOfStroke,
                    shape = RoundedCornerShape(20.dp)
                )
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row {
            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .size(if (index == pagerState.currentPage) 10.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == pagerState.currentPage) blue3
                            else Color.LightGray
                        )
                )
            }
        }
    }
}

@Composable
fun ProductContent(modifier: Modifier = Modifier, product: DataEntity) {
    var quantity by remember { mutableIntStateOf(1) }
    Column(modifier = Modifier.padding(15.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.title ?: stringResource(R.string.unknown_product),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = blue2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "EGP ${product.price ?: 0}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = blue2
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 2.dp,
                                color = colorOfStroke,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            "${product.sold ?: 0} Sold",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            fontFamily = FontFamily.SansSerif,
                            color = blue2
                        )
                    }

                Icon(
                    painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    tint = yellow,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    " ${product.ratingsAverage ?: 0} (${product.ratingsQuantity ?: 0})",
                    fontSize = 14.sp,
                    color = blue2,
                    fontWeight = FontWeight.W400
                )
                Spacer(modifier = Modifier.padding(30.dp))
                Box(
                    modifier = Modifier
                        .size(width = 120.dp, height = 40.dp)
                        .clip(RoundedCornerShape(50))
                        .background(blue3)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                    ) {
                        IconButton(onClick = {
                            if (quantity > 1) quantity--
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_sub),
                                contentDescription = "minus",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Text(
                            quantity.toString(),
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        IconButton(onClick = {
                            quantity++
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_add),
                                contentDescription = stringResource(R.string.plus),
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            stringResource(R.string.description),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = blue2
        )
        Text(
            product.description ?: "",
            fontSize = 14.sp,
            color = colorOfStroke,
            fontWeight = FontWeight.W400
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Size",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = blue2
        )

        val sizes = listOf("38", "39", "40", "41", "42")
        var selectedSize by remember { mutableStateOf<String?>(null) }

        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            sizes.forEach { size ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            if (size == selectedSize) blue3
                            else Color.White
                        )
                        .border(
                            width = 2.dp,
                            color = if (size == selectedSize) blue3 else Color.LightGray,
                            shape = CircleShape
                        )
                        .clickable { selectedSize = size }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = size,
                        color = if (size == selectedSize) Color.White else Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            stringResource(R.string.color),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = blue2
        )

        val colors = listOf(
            Black,
            orange,
            lightBlue,
            green,
            lightRed
        )
        var selectedColor by remember { mutableStateOf<Color?>(null) }

        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(color)
                        .clickable { selectedColor = color },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedColor == color) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    stringResource(R.string.total_price),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    color = colorOfStroke
                )
                Text(
                    "EGP ${product.price?.times(quantity) ?: 0}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    color = blue2
                )
            }

            Button(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(blue3),
                modifier = Modifier
                    .height(48.dp)
                    .width(250.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_add_cart),
                    contentDescription = stringResource(R.string.add_to_cart_button),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.add_to_cart), fontSize = 18.sp, fontWeight = FontWeight.W500)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductContentPreview() {
    val dummyProduct = DataEntity(
        id = "1",
        title = "Nike Air Jordon",
        description = "High-quality shoes for sports and lifestyle.",
        price = 3500,
        quantity = 100,
        sold = 3230,
        ratingsAverage = 4.8,
        ratingsQuantity = 7500,
        imageCover = "",
        images = listOf(),
        subcategory = null,
        category = null,
        brand = null,
        slug = "",
        createdAt = "",
        updatedAt = "",
        reviews = null,
        v = null
    )
    ProductContent(product = dummyProduct)
}
@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar(navController = rememberNavController())
}
