package com.fabien.africschool.ui.theme

import androidx.compose.runtime.*
import com.fabien.africschool.utils.AdaptiveLayout
import com.fabien.africschool.utils.AdaptiveLayoutType
import com.fabien.africschool.utils.ContentType
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.dynamiccolor.ColorSpec
import com.materialkolor.rememberDynamicMaterialThemeState

private val localAdaptiveLayoutType = compositionLocalOf { mutableStateOf(AdaptiveLayoutType.Compact) }
private val localContentType = compositionLocalOf { mutableStateOf(ContentType.Single) }

object Themes {
    val adaptiveLayoutType: AdaptiveLayoutType
        @Composable @ReadOnlyComposable
        get() = localAdaptiveLayoutType.current.value

    val contentType: ContentType
        @Composable @ReadOnlyComposable
        get() = localContentType.current.value

    @Composable
    fun Themes.isCompact(): Boolean = localAdaptiveLayoutType.current.value === AdaptiveLayoutType.Compact
}

@Composable
fun AppTheme(
//    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val adaptiveLayoutType = remember { mutableStateOf(AdaptiveLayoutType.Compact) }
    val contentType = remember { mutableStateOf(ContentType.Single) }
    val dynamicThemeState =
        rememberDynamicMaterialThemeState(
            isDark = isDarkTheme,
            style = PaletteStyle.Neutral,
            specVersion = ColorSpec.SpecVersion.SPEC_2025,
            seedColor = SeedColor,
        )

    AdaptiveLayout(adaptiveLayoutType, contentType)

    CompositionLocalProvider(
        localAdaptiveLayoutType provides adaptiveLayoutType,
        localContentType provides contentType,
    ) {
        DynamicMaterialTheme(
            state = dynamicThemeState,
            animate = true,
            content = content,
        )
    }
}
