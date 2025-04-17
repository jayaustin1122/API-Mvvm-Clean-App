package com.mvvmexample.apimvvmclean.common.util.dialog

/**
 * Dialog utilities object containing all dialog-related functionality
 */
object DialogUtils {

    /**
     * Shows a success dialog
     */
    fun createSuccessDialog(
        title: String = "Success",
        message: String,
        onDismiss: () -> Unit,
        autoDismissDelay: Long? = 2000
    ): DialogData {
        return DialogData(
            title = title,
            message = message,
            dialogType = DialogType.SUCCESS,
            onDismiss = onDismiss,
            autoDismissDelay = autoDismissDelay
        )
    }

    /**
     * Shows an error dialog
     */
    fun createErrorDialog(
        title: String = "Error",
        message: String,
        onDismiss: () -> Unit
    ): DialogData {
        return DialogData(
            title = title,
            message = message,
            dialogType = DialogType.ERROR,
            onDismiss = onDismiss
        )
    }

    /**
     * Shows a warning dialog
     */
    fun createWarningDialog(
        title: String = "Warning",
        message: String,
        onDismiss: () -> Unit
    ): DialogData {
        return DialogData(
            title = title,
            message = message,
            dialogType = DialogType.WARNING,
            onDismiss = onDismiss
        )
    }

    /**
     * Shows a confirmation dialog
     */
    fun createConfirmationDialog(
        title: String = "Confirm",
        message: String,
        confirmText: String = "OK",
        cancelText: String = "Cancel",
        onConfirm: () -> Unit,
        onCancel: () -> Unit
    ): DialogData {
        return DialogData(
            title = title,
            message = message,
            dialogType = DialogType.CONFIRMATION,
            onConfirm = onConfirm,
            onCancel = onCancel,
            confirmText = confirmText,
            cancelText = cancelText,
            showCancel = true
        )
    }

    /**
     * Shows a welcome dialog
     */
    fun createWelcomeDialog(
        title: String = "Welcome",
        message: String,
        confirmText: String = "Let's Start",
        onConfirm: () -> Unit
    ): DialogData {
        return DialogData(
            title = title,
            message = message,
            dialogType = DialogType.WELCOME,
            onConfirm = onConfirm,
            confirmText = confirmText
        )
    }

    /**
     * Shows a logout confirmation dialog
     * OutlinedButton(
     *             onClick = {
     *                 showDialog = DialogUtils.createLogoutConfirmationDialog(
     *                     onConfirm = {
     *                         viewModel.logout()
     *                         onLogout()
     *                     },
     *                     onCancel = {
     *                         showDialog = null
     *                     }
     *                 )
     *             },
     *             modifier = Modifier.fillMaxWidth()
     *         )
     */
    fun createLogoutConfirmationDialog(
        onConfirm: () -> Unit,
        onCancel: () -> Unit
    ): DialogData {
        return DialogData(
            title = "Logout Confirmation",
            message = "Are you sure you want to logout?",
            dialogType = DialogType.CONFIRMATION,
            onConfirm = onConfirm,
            onCancel = onCancel,
            confirmText = "Logout",
            cancelText = "Cancel",
            showCancel = true
        )
    }

    /**
     * Shows a login success dialog
     */
    fun createLoginSuccessDialog(
        username: String,
        onDismiss: () -> Unit
    ): DialogData {
        return DialogData(
            title = "Login Successful",
            message = "Welcome back, $username!",
            dialogType = DialogType.SUCCESS,
            onDismiss = onDismiss,
            autoDismissDelay = 2000
        )
    }

    /**
     * Shows a signup success dialog
     */
    fun createSignupSuccessDialog(
        username: String,
        onDismiss: () -> Unit
    ): DialogData {
        return DialogData(
            title = "Account Created",
            message = "Welcome to our app, $username! Your account has been created successfully.",
            dialogType = DialogType.SUCCESS,
            onDismiss = onDismiss,
            confirmText = "Continue",
            autoDismissDelay = null
        )
    }
}

