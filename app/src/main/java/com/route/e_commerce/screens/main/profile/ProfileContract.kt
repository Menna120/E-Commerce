package com.route.e_commerce.screens.main.profile

import com.route.domain.entity.Address
import com.route.domain.entity.User
import com.route.e_commerce.R
import com.route.e_commerce.base.ViewEvent
import com.route.e_commerce.base.ViewSideEffect
import com.route.e_commerce.base.ViewState

data class ProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val addresses: List<Address> = emptyList(),
    val showUpdatePasswordDialog: Boolean = false,
    val showEditFieldDialog: Boolean = false,
    val showAddressesBottomSheet: Boolean = false,
    val editingUserDataField: Pair<String, String>? = null
) : ViewState

sealed interface ProfileEvent : ViewEvent {
    data object LoadInitialData : ProfileEvent
    data class UpdateUser(val name: String?, val email: String?, val phone: String?) : ProfileEvent
    data class UpdatePassword(
        val current: String,
        val new: String,
        val reNew: String
    ) : ProfileEvent

    data class AddAddress(
        val name: String,
        val details: String,
        val phone: String,
        val city: String
    ) : ProfileEvent

    data class DeleteAddress(val addressId: String) : ProfileEvent
    data class IsAddressesBottomSheetVisible(val visible: Boolean) : ProfileEvent
    data class IsUpdatePasswordDialogVisible(val visible: Boolean) : ProfileEvent
    data class OpenEditFieldDialog(val label: String, val value: String) : ProfileEvent
    data object CloseEditFieldDialog : ProfileEvent
}

sealed interface ProfileEffect : ViewSideEffect {
    data class ShowToast(val message: String) : ProfileEffect
}

enum class EditableField(val labelRes: Int) {
    NAME(R.string.your_full_name),
    EMAIL(R.string.your_e_mail),
    PHONE(R.string.your_mobile_number);

    fun toUpdateEvent(newValue: String): ProfileEvent.UpdateUser {
        return ProfileEvent.UpdateUser(
            name = if (this == NAME) newValue else null,
            email = if (this == EMAIL) newValue else null,
            phone = if (this == PHONE) newValue else null
        )
    }
}
