package co.nes.parceltestkmp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.nes.parceltestkmp.ui.theme.AspenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AspenSearchBar(
    query: String,
    onQueryChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    active: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {},
    modifier: Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = AspenTheme.colors.onTertiary,
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = AspenTheme.colors.tertiary,
            dividerColor = AspenTheme.colors.onTertiary,
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                focusedTextColor = AspenTheme.colors.onTertiary,
                unfocusedTextColor = AspenTheme.colors.onTertiary,
                disabledTextColor = AspenTheme.colors.onTertiary,
                cursorColor = AspenTheme.colors.onBackground,
            )
        ),
    ) {}
}