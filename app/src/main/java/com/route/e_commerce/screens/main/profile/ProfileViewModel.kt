package com.route.e_commerce.screens.main.profile

import com.route.domain.base.Resource
import com.route.domain.usecases.address.AddAddressUseCase
import com.route.domain.usecases.address.DeleteAddressUseCase
import com.route.domain.usecases.address.GetAddressesUseCase
import com.route.domain.usecases.auth.UpdatePasswordUseCase
import com.route.domain.usecases.auth.UpdateUserUseCase
import com.route.domain.usecases.local_storage.GetUserUseCase
import com.route.e_commerce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getAddressesUseCase: GetAddressesUseCase,
    private val addAddressUseCase: AddAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>(ProfileState()) {

    override suspend fun handleAction(action: ProfileEvent) {
        when (action) {
            is ProfileEvent.LoadInitialData -> {
                updateState(viewState.value.copy(isLoading = true))
                getUser()
                getAddresses()
                updateState(viewState.value.copy(isLoading = false))
            }

            is ProfileEvent.UpdateUser -> updateUser(
                action.name,
                action.email,
                action.phone
            )

            is ProfileEvent.UpdatePassword -> updatePassword(
                action.current,
                action.new,
                action.reNew
            )

            is ProfileEvent.AddAddress -> addAddress(
                action.name,
                action.details,
                action.phone,
                action.city
            )

            is ProfileEvent.DeleteAddress -> deleteAddress(action.addressId)

            is ProfileEvent.IsAddressesBottomSheetVisible -> updateState(
                viewState.value.copy(showAddressesBottomSheet = action.visible)
            )

            is ProfileEvent.IsUpdatePasswordDialogVisible -> updateState(
                viewState.value.copy(showUpdatePasswordDialog = action.visible)
            )

            is ProfileEvent.OpenEditFieldDialog -> updateState(
                viewState.value.copy(
                    showEditFieldDialog = true,
                    editingUserDataField = Pair(action.label, action.value)
                )
            )

            is ProfileEvent.CloseEditFieldDialog -> updateState(
                viewState.value.copy(
                    showEditFieldDialog = false,
                    editingUserDataField = null
                )
            )
        }
    }

    private suspend fun getUser() = updateState(viewState.value.copy(user = getUserUseCase()))

    private suspend fun getAddresses() {
        getAddressesUseCase().collect {
            handleResource(
                it,
                onSuccess = { addresses ->
                    updateState(viewState.value.copy(addresses = addresses ?: emptyList()))
                }
            )
        }
    }

    private suspend fun updateUser(name: String?, email: String?, phone: String?) {
        updateUserUseCase(name, email, phone).collect {
            handleResource(it, onSuccess = {
                sendEffect(ProfileEffect.ShowToast("Updated Successfully"))
                getUser()
            })
        }
    }

    private suspend fun updatePassword(current: String, new: String, reNew: String) {
        if (new != reNew) {
            sendEffect(ProfileEffect.ShowToast("Passwords do not match"))
            return
        }
        updatePasswordUseCase(current, new).collect {
            handleResource(
                it,
                onSuccess = { sendEffect(ProfileEffect.ShowToast("Password updated successfully")) })
        }
    }

    private suspend fun addAddress(name: String, details: String, phone: String, city: String) {
        addAddressUseCase(name, details, phone, city).collect { it ->
            handleResource(
                it,
                onSuccess = { addresses ->
                    addresses?.let {
                        sendEffect(ProfileEffect.ShowToast("Address added Successfully"))
                        updateState(viewState.value.copy(addresses = it))
                    }
                        ?: sendEffect(ProfileEffect.ShowToast("Couldn't add the address"))
                }
            )
        }
    }

    private suspend fun deleteAddress(addressId: String) {
        deleteAddressUseCase(addressId).collect { it ->
            handleResource(
                it,
                onSuccess = { addresses ->
                    addresses?.let {
                        sendEffect(ProfileEffect.ShowToast("Address deleted Successfully"))
                        updateState(viewState.value.copy(addresses = it))
                    }
                        ?: sendEffect(ProfileEffect.ShowToast("Couldn't delete the address"))
                }
            )
        }
    }

    private suspend fun <T> handleResource(
        resource: Resource<T>,
        onSuccess: suspend (T?) -> Unit
    ) {
        when (resource) {
            is Resource.Success -> {
                updateState(viewState.value.copy(isLoading = false))
                onSuccess(resource.data)
            }

            is Resource.Error -> {
                updateState(viewState.value.copy(isLoading = false))
                sendEffect(ProfileEffect.ShowToast(resource.errorMessage))
            }

            else -> Unit
        }
    }
}
