package com.route.e_commerce.screens.main.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.route.domain.entity.User
import com.route.e_commerce.R
import com.route.e_commerce.components.ECommerceTextField
import com.route.e_commerce.components.onBackgroundColors
import com.route.e_commerce.screens.main.profile.components.ProfileListItem
import com.route.e_commerce.screens.main.profile.components.bottom_sheet.AddressesBottomSheet
import com.route.e_commerce.screens.main.profile.components.dialog.EditFieldDialog
import com.route.e_commerce.screens.main.profile.components.dialog.UpdatePasswordDialog
import com.route.e_commerce.ui.theme.ECommerceTheme
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(ProfileEvent.LoadInitialData)

        viewModel.viewEffect.collectLatest {
            when (it) {
                is ProfileEffect.ShowToast -> {
                    Toast.makeText(
                        context,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("ProfileScreen", it.message)
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            ProfileContent(
                user = state.user,
                setEvent = viewModel::setEvent
            )
        }
    }

    if (state.showUpdatePasswordDialog) {
        UpdatePasswordDialog(
            onConfirm = { current, new, reNew ->
                viewModel.setEvent(ProfileEvent.UpdatePassword(current, new, reNew))
                viewModel.setEvent(ProfileEvent.IsUpdatePasswordDialogVisible(false))
            },
            onDismiss = { viewModel.setEvent(ProfileEvent.IsUpdatePasswordDialogVisible(false)) }
        )
    }

    if (state.showEditFieldDialog) {
        state.editingUserDataField?.let { (label, value) ->
            EditFieldDialog(
                label = label,
                value = value,
                onConfirm = { newValue ->
                    val editedField =
                        EditableField.entries.find { context.getString(it.labelRes) == label }
                    editedField?.let { field ->
                        viewModel.setEvent(field.toUpdateEvent(newValue))
                    }
                    viewModel.setEvent(ProfileEvent.CloseEditFieldDialog)
                },
                onDismiss = { viewModel.setEvent(ProfileEvent.CloseEditFieldDialog) }
            )
        }
    }

    if (state.showAddressesBottomSheet) {
        AddressesBottomSheet(
            addresses = state.addresses,
            onAddAddress = { name, details, phone, city ->
                viewModel.setEvent(ProfileEvent.AddAddress(name, details, phone, city))
            },
            onDeleteAddress = { viewModel.setEvent(ProfileEvent.DeleteAddress(it)) },
            onDismiss = { viewModel.setEvent(ProfileEvent.IsAddressesBottomSheetVisible(false)) }
        )
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    user: User?,
    setEvent: (ProfileEvent) -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(Modifier.weight(1f))

        user?.let {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle()
                            .copy(fontWeight = FontWeight.SemiBold)
                    ) {
                        append(stringResource(R.string.welcome, user.name ?: ""))
                    }

                    withStyle(
                        style = MaterialTheme.typography.titleMedium.toSpanStyle()
                            .copy(fontWeight = FontWeight.Normal)
                    ) {
                        append("\n${user.email}")
                    }
                },
                color = scheme.onBackground,
            )
        }

        Spacer(Modifier.weight(1f))

        ECommerceTextField(
            value = user?.name ?: "",
            onValueChange = { },
            label = stringResource(EditableField.NAME.labelRes),
            labelColor = scheme.onBackground,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            isEdit = true,
            onEditClick = {
                setEvent(
                    ProfileEvent.OpenEditFieldDialog(
                        context.getString(EditableField.NAME.labelRes),
                        user?.name ?: ""
                    )
                )
            }
        )

        Spacer(Modifier.weight(.5f))

        ECommerceTextField(
            value = user?.email ?: "",
            onValueChange = { },
            label = stringResource(EditableField.EMAIL.labelRes),
            labelColor = scheme.onBackground,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            isEdit = true,
            onEditClick = {
                setEvent(
                    ProfileEvent.OpenEditFieldDialog(
                        context.getString(EditableField.EMAIL.labelRes),
                        user?.email ?: ""
                    )
                )
            }
        )

        Spacer(Modifier.weight(.5f))

        ECommerceTextField(
            value = user?.phone ?: "",
            onValueChange = { },
            label = stringResource(EditableField.PHONE.labelRes),
            labelColor = scheme.onBackground,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            isEdit = true,
            onEditClick = {
                setEvent(
                    ProfileEvent.OpenEditFieldDialog(
                        context.getString(EditableField.PHONE.labelRes),
                        user?.phone ?: ""
                    )
                )
            }
        )

        Spacer(Modifier.weight(1f))

        ProfileListItem(stringResource(R.string.addresses)) {
            setEvent(ProfileEvent.IsAddressesBottomSheetVisible(true))
        }

        Spacer(Modifier.weight(.25f))

        ProfileListItem(stringResource(R.string.change_password)) {
            setEvent(
                ProfileEvent.IsUpdatePasswordDialogVisible(
                    true
                )
            )
        }

        Spacer(Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    ECommerceTheme {
        ProfileContent(
            user = User(name = "Test User", email = "test@example.com", phone = "010123456789"),
            setEvent = {}
        )
    }
}
