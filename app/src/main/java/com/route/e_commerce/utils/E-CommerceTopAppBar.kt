package com.route.e_commerce.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.e_commerce.R
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.ui.theme.blue2
import com.route.e_commerce.ui.theme.blue3
import com.route.e_commerce.ui.theme.colorOfStroke

@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    val appNavController = LocalAppNavController.current

        Row {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(55.dp)
                    .background(Color.White, RoundedCornerShape(30.dp))
                    .border(1.dp, color = blue3, RoundedCornerShape(30.dp))
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(25.dp),
                    tint = blue2
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = query,
                    onValueChange = { query = it; onSearch(it) },
                    placeholder = { Text("what do you search for?", fontSize = 18.sp,
                        textAlign = TextAlign.Center) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.87f),
                    colors = TextFieldDefaults.colors(
                        focusedPlaceholderColor = Color.Transparent,
                        unfocusedPlaceholderColor = colorOfStroke,
                        errorPlaceholderColor = colorOfStroke,
                        focusedTextColor = colorOfStroke,
                        unfocusedTextColor =colorOfStroke,
                        errorTextColor = colorOfStroke,
                        errorContainerColor = colorOfStroke,
                        focusedContainerColor =Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }
            IconButton(onClick = onCartClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_shopping_cart),
                    contentDescription = "Cart",
                    tint = blue2,
                    modifier = Modifier.size(28.dp).
                    clickable{
                        appNavController.navigate(Screen.Cart)
                    }
                )
            }
        }
    }

@Preview(showBackground = true)
@Composable
private fun CustomTopBarPreview () {
    CustomTopBar()
}
