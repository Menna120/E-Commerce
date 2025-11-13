package com.route.e_commerce.screens.main.profile.components.bottom_sheet

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.domain.entity.Address
import com.route.e_commerce.R
import com.route.e_commerce.screens.main.profile.components.dialog.AddAddressDialog
import com.route.e_commerce.ui.theme.ECommerceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesBottomSheet(
    addresses: List<Address>,
    onAddAddress: (name: String, details: String, phone: String, city: String) -> Unit,
    onDeleteAddress: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showAddAddressSheet by remember { mutableStateOf(false) }
    val isExpanding = sheetState.targetValue == SheetValue.Expanded
    val roundedCornerRadius by animateDpAsState(
        targetValue = if (sheetState.isAnimationRunning && isExpanding) 0.dp else 24.dp,
        label = "bottom sheet shape"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topEnd = roundedCornerRadius,
            topStart = roundedCornerRadius,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        AddressesBottomSheetContent(
            addresses = addresses,
            onDeleteAddress = onDeleteAddress,
            onAddClick = { showAddAddressSheet = true },
            isExpanded = sheetState.currentValue == SheetValue.Expanded
        )

        if (showAddAddressSheet) {
            AddAddressDialog(
                onAddAddress = { name, details, phone, city ->
                    onAddAddress(name, details, phone, city)
                    showAddAddressSheet = false
                },
                onDismiss = { showAddAddressSheet = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesBottomSheetContent(
    addresses: List<Address>,
    onDeleteAddress: (String) -> Unit,
    onAddClick: () -> Unit,
    isExpanded: Boolean
) {
    val scheme = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.your_addresses)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = scheme.background,
                    titleContentColor = scheme.onBackground
                )
            )
        },
        floatingActionButton = {
            if (isExpanded) {
                FloatingActionButton(
                    onClick = onAddClick,
                    containerColor = scheme.onBackground,
                    contentColor = scheme.background
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                        contentDescription = "Add Address"
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        AddressesList(
            addresses = addresses,
            onDeleteAddress = onDeleteAddress,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}

@Composable
fun AddressesList(
    addresses: List<Address>,
    onDeleteAddress: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (addresses.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("No address found add one")
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(addresses, key = { it.id!! }) { address ->
                AddressItem(address = address, onDelete = onDeleteAddress)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddressesBottomSheetPreview() {
    val addresses = listOf(
        Address(
            id = "1",
            name = "Home",
            details = "123 Main St",
            phone = "555-1234",
            city = "Anytown"
        ),
        Address(
            id = "2",
            name = "Work",
            details = "456 Business Ave",
            phone = "555-5678",
            city = "Busy town"
        )
    )

    ECommerceTheme {
        AddressesBottomSheetContent(
            addresses = addresses,
            onDeleteAddress = {},
            onAddClick = {},
            isExpanded = true
        )
    }
}

@Composable
fun AddressItem(
    address: Address,
    modifier: Modifier = Modifier,
    onDelete: (String) -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography

    ListItem(
        headlineContent = {
            Text(
                text = address.name ?: "",
                style = typo.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        modifier = modifier.fillMaxWidth(),
        supportingContent = {
            val supportingTextStyle = typo.bodySmall.copy(fontWeight = FontWeight.Normal)

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = address.details ?: "", style = supportingTextStyle)
                Text(text = address.city ?: "", style = supportingTextStyle)
                Text(text = address.phone ?: "", style = supportingTextStyle)
            }
        },
        trailingContent = {
            IconButton({ address.id?.let(onDelete) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                    contentDescription = "Delete Address"
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = scheme.background,
            headlineColor = scheme.onBackground,
            supportingColor = scheme.secondary,
            trailingIconColor = scheme.onBackground
        )
    )
}

@Preview
@Composable
fun AddressItemPreview() {
    val address = Address(
        id = "1",
        name = "Home",
        details = "123 Main St, Apt 4B",
        phone = "555-1234",
        city = "Springfield"
    )

    ECommerceTheme {
        AddressItem(address = address, onDelete = {})
    }
}
