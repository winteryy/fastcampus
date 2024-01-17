package com.winterry.movieapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.winterry.movieapp.ui.theme.color.Black
import com.winterry.movieapp.ui.theme.color.Pink40
import com.winterry.movieapp.ui.theme.color.Pink80
import com.winterry.movieapp.ui.theme.color.Purple40
import com.winterry.movieapp.ui.theme.color.Purple80
import com.winterry.movieapp.ui.theme.color.Purple900
import com.winterry.movieapp.ui.theme.color.PurpleGrey40
import com.winterry.movieapp.ui.theme.color.PurpleGrey80
import com.winterry.movieapp.ui.theme.color.Red400
import com.winterry.movieapp.ui.theme.color.Red700
import com.winterry.movieapp.ui.theme.color.Red800
import com.winterry.movieapp.ui.theme.color.White

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Red700,
    secondary = Purple900,
    tertiary = Pink40,
    surface = White,
    onSurface = Black,
    background = White,
    onBackground = Black,
    error = Red400,
    onPrimary = White,
    inversePrimary = Red800

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}