package ec.edu.puce.githubclient.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF24292F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDDF4FF),
    onPrimaryContainer = Color(0xFF0969DA),

    secondary = Color(0xFF0969DA),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDDF4FF),
    onSecondaryContainer = Color(0xFF0550AE),

    tertiary = Color(0xFF8250DF),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFEFE7FF),
    onTertiaryContainer = Color(0xFF4C2889),

    background = Color(0xFFF6F8FA),
    onBackground = Color(0xFF24292F),

    surface = Color.White,
    onSurface = Color(0xFF24292F),

    surfaceVariant = Color(0xFFEEF2F6),
    onSurfaceVariant = Color(0xFF57606A),

    error = Color(0xFFCF222E),
    onError = Color.White,

    outline = Color(0xFFD0D7DE)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFC9D1D9),
    onPrimary = Color(0xFF0D1117),
    primaryContainer = Color(0xFF161B22),
    onPrimaryContainer = Color(0xFF58A6FF),

    secondary = Color(0xFF58A6FF),
    onSecondary = Color(0xFF0D1117),
    secondaryContainer = Color(0xFF0D419D),
    onSecondaryContainer = Color(0xFFDDF4FF),

    tertiary = Color(0xFFD2A8FF),
    onTertiary = Color(0xFF0D1117),
    tertiaryContainer = Color(0xFF3B0764),
    onTertiaryContainer = Color(0xFFF5EFFF),

    background = Color(0xFF0D1117),
    onBackground = Color(0xFFC9D1D9),

    surface = Color(0xFF161B22),
    onSurface = Color(0xFFC9D1D9),

    surfaceVariant = Color(0xFF21262D),
    onSurfaceVariant = Color(0xFF8B949E),

    error = Color(0xFFFF7B72),
    onError = Color(0xFF0D1117),

    outline = Color(0xFF30363D)
)

@Composable
fun GithubClientTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}