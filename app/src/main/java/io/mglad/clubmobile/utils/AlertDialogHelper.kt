package io.mglad.clubmobile.utils

import android.content.Context
import android.support.v7.app.AlertDialog

class AlertDialogHelper {
    companion object {
        fun alertWithAction(context: Context, positiveTitle: String, message: String, action: () -> Unit) {
            AlertDialog.Builder(context)
                    .setPositiveButton(positiveTitle, { _, _ ->
                        action.invoke()
                    })
                    .setNegativeButton("Cancel", { dialog, _ ->
                        dialog.cancel()
                    })
                    .setMessage(message)
                    .create()
                    .show()
        }
    }
}