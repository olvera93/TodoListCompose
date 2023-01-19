package com.example.todolistcompose.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
@ExperimentalMaterialApi
fun DropdownContextMenu(
    options: List<String>,
    modifier: Modifier,
    onActionClick: (String) -> Unit
) {

    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = { isExpanded = !isExpanded }) {

        Icon(
            modifier = Modifier.padding(8.dp, 0.dp),
            contentDescription = "More",
            imageVector = Icons.Default.MoreVert
        )

        ExposedDropdownMenu(
            modifier = Modifier.width(180.dp),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    onActionClick(selectionOption)
                }) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun DropdownSelector(
    @StringRes label: Int,
    options: List<String>,
    selection: String,
    modifier: Modifier,
    onNewValue: (String) -> Unit
) {

    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = selection,
            onValueChange = {},
            label = { Text(text = stringResource(id = label)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = (isExpanded)) },
            colors = dropdownColors()
        )

        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    onNewValue(selectionOption)
                    isExpanded = false
                }) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
private fun dropdownColors(): TextFieldColors {
    return ExposedDropdownMenuDefaults.textFieldColors(
        backgroundColor = MaterialTheme.colors.onPrimary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        trailingIconColor = MaterialTheme.colors.onSurface,
        focusedTrailingIconColor = MaterialTheme.colors.onSurface,
        focusedLabelColor = MaterialTheme.colors.primary,
        unfocusedLabelColor = MaterialTheme.colors.primary
    )

}