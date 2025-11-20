package com.route.e_commerce.screens.main.profile

import androidx.lifecycle.viewModelScope
import com.route.domain.base.Resource
import com.route.domain.usecases.address.AddAddressUseCase
import com.route.domain.usecases.address.DeleteAddressUseCase
import com.route.domain.usecases.address.GetAddressesUseCase
import com.route.domain.usecases.auth.UpdatePasswordUseCase
import com.route.domain.usecases.auth.UpdateUserUseCase
import com.route.domain.usecases.local_storage.GetUserUseCase
import com.route.e_commerce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LoadInitialData -> {
                setState { copy(isLoading = true) }
                viewModelScope.launch {
                    getUser()
                    getAddresses()
                    setState { copy(isLoading = false) }
                }
            }

            is ProfileEvent.UpdateUser -> viewModelScope.launch {
                updateUser(
                    event.name,
                    event.email,
                    event.phone
                )
            }

            is ProfileEvent.UpdatePassword -> viewModelScope.launch {
                updatePassword(
                    event.current,
                    event.new,
                    event.reNew
                )
            }

            is ProfileEvent.AddAddress -> viewModelScope.launch {
                addAddress(
                    event.name,
                    event.details,
                    event.phone,
                    event.city
                )
            }

            is ProfileEvent.DeleteAddress -> viewModelScope.launch {
                deleteAddress(event.addressId)
            }

            is ProfileEvent.IsAddressesBottomSheetVisible -> setState {
                copy(showAddressesBottomSheet = event.visible)
            }

            is ProfileEvent.IsUpdatePasswordDialogVisible -> setState {
                copy(showUpdatePasswordDialog = event.visible)
            }

            is ProfileEvent.OpenEditFieldDialog -> setState {
                copy(
                    showEditFieldDialog = true,
                    editingUserDataField = Pair(event.label, event.value)
                )
            }

            is ProfileEvent.CloseEditFieldDialog -> setState {
                copy(
                    showEditFieldDialog = false,
                    editingUserDataField = null
                )
            }
        }
    }

    private suspend fun getUser() {
        val user = getUserUseCase()
        setState { copy(user = user) }
    }

    private suspend fun getAddresses() {
        getAddressesUseCase().collect {
            handleResource(
                it,
                onSuccess = { addresses ->
                    setState { copy(addresses = addresses ?: emptyList()) }
                }
            )
        }
    }

    private suspend fun updateUser(name: String?, email: String?, phone: String?) {
        updateUserUseCase(name, email, phone).collect {
            handleResource(it, onSuccess = {
                setEffect { ProfileEffect.ShowToast("Updated Successfully") }
                getUser()
            })
        }
    }

    private suspend fun updatePassword(current: String, new: String, reNew: String) {
        if (new != reNew) {
            setEffect { ProfileEffect.ShowToast("Passwords do not match") }
            return
        }
        updatePasswordUseCase(current, new).collect {
            handleResource(
                it,
                onSuccess = { setEffect { ProfileEffect.ShowToast("Password updated successfully") } })
        }
    }

    private suspend fun addAddress(name: String, details: String, phone: String, city: String) {
        addAddressUseCase(name, details, phone, city).collect { it ->
            handleResource(
                it,
                onSuccess = { addresses ->
                    addresses?.let {
                        setEffect { ProfileEffect.ShowToast("Address added Successfully") }
                        setState { copy(addresses = it) }
                    }
                        ?: setEffect { ProfileEffect.ShowToast("Couldn't add the address") }
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
                        setEffect { ProfileEffect.ShowToast("Address deleted Successfully") }
                        setState { copy(addresses = it) }
                    }
                        ?: setEffect { ProfileEffect.ShowToast("Couldn't delete the address") }
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
                setState { copy(isLoading = false) }
                onSuccess(resource.data)
            }

            is Resource.Error -> {
                setState { copy(isLoading = false) }
                setEffect { ProfileEffect.ShowToast(resource.errorMessage) }
            }

            else -> Unit
        }
    }
}
