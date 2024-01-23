package com.winterry.movieapp.features.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.winterry.movieapp.R
import com.winterry.movieapp.ui.components.dialog.Alert
import com.winterry.movieapp.ui.components.dialog.DialogPopup
import com.winterry.movieapp.ui.models.dialog.DialogButton
import com.winterry.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.apply {
            isCancelable = true
            setCanceledOnTouchOutside(true)
            window?.setBackgroundDrawable(ColorDrawable(requireActivity().getColor(android.R.color.transparent)))
        }

        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MovieAppTheme(
                ) {
                    DialogPopup.Alert(
                        title = stringResource(R.string.app_name),
                        bodyText = stringResource(R.string.info_message),
                        buttons = listOf(
                            DialogButton.UnderlinedText(getString(R.string.close)) {
                                dismiss()
                            }
                        )
                    )
                }
            }
        }
    }
}