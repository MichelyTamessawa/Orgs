package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.DialogImageUploadBinding
import com.example.orgs.extensions.loadingImage

class ImageFormDialog(private val context: Context) {
    fun show(
        actualUrl: String? = null,
        imageLoadedListener: (imageUrl: String) -> Unit
    ) {
        DialogImageUploadBinding.inflate(LayoutInflater.from(context)).apply {
            val dialogUploadButton = dialogImageUploadButton
            val dialogUrlTextView = dialogImageUploadUrl
            val dialogImageView = dialogImageUploadImageview

            actualUrl?.let {
                dialogImageView.loadingImage(it)
                dialogUrlTextView.setText(it)
            }

            dialogUploadButton.setOnClickListener {
                dialogImageView.loadingImage(dialogUrlTextView.text.toString())
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirm") { _, _ ->
                    if (dialogUrlTextView.text?.isNotBlank()!!) {
                        val url = dialogUrlTextView.text.toString()
                        imageLoadedListener(url)
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                }
                .show()
        }
    }
}