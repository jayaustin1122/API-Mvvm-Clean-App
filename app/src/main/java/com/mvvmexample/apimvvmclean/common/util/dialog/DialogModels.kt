package com.mvvmexample.apimvvmclean.common.util.dialog

/**
 * Dialog type enumeration for different dialog styles
 */
enum class DialogType {
    SUCCESS,
    ERROR,
    WARNING,
    INFO,
    CONFIRMATION,
    WELCOME
}

/**
 * Dialog data class to store dialog content and configuration
 */
data class DialogData(
    val title: String,
    val message: String,
    val dialogType: DialogType = DialogType.INFO,
    val onDismiss: () -> Unit = {},
    val onConfirm: () -> Unit = {},
    val onCancel: () -> Unit = {},
    val confirmText: String = "OK",
    val cancelText: String = "Cancel",
    val showCancel: Boolean = false,
    val autoDismissDelay: Long? = null
)