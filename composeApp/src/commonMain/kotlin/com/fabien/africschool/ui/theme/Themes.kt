package com.fabien.africschool.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.dynamiccolor.ColorSpec
import com.materialkolor.rememberDynamicMaterialThemeState

@Composable
fun AppTheme(
//    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val dynamicThemeState =
        rememberDynamicMaterialThemeState(
            isDark = isDarkTheme,
            style = PaletteStyle.Neutral,
            specVersion = ColorSpec.SpecVersion.SPEC_2025,
            seedColor = SeedColor,
        )

    CompositionLocalProvider {
        DynamicMaterialTheme(
            state = dynamicThemeState,
            animate = true,
            content = content,
        )
    }
}

enum class WindowSize {
    COMPACT,
    MEDIUM,
    EXPANDED,
    ;

    companion object {
        fun basedOnWidth(size: Dp): WindowSize =
            when {
                size < 600.dp -> COMPACT
                size < 840.dp -> MEDIUM
                else -> EXPANDED
            }
    }
}
