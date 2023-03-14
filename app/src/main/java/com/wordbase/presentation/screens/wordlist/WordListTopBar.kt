package com.wordbase.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wordbase.R
import com.wordbase.data.Constants

@Composable
fun WordListTopBar(
    onMyListClick: () -> Unit,
    onCreateNewListClick: () -> Unit,
    onPreMadeListsClick: () -> Unit,
    disabled: Int = 0
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Constants.white,
        contentColor = Constants.black,
        disabledContentColor = Constants.white,
        disabledContainerColor = Constants.gray
    )
    Button(
        enabled = 1!=disabled,
        onClick = onMyListClick,
        colors = buttonColors,
        shape = RoundedCornerShape(Constants.borderRadius),
        modifier = Modifier
            .width(Constants.width)
            .height(Constants.height)
    ) {
        Text(stringResource(id = R.string.my_lists), color = Constants.black, fontSize = Constants.buttonFontSize, fontWeight = FontWeight.Medium)
    }
    Spacer(modifier = Modifier.width(10.dp))
    Button(
        enabled = 2!=disabled,
        onClick = onCreateNewListClick,
        colors = buttonColors,
        shape = RoundedCornerShape(Constants.borderRadius),
        modifier = Modifier
            .width(Constants.width)
            .height(Constants.height)
    ) {
        Text(stringResource(id = R.string.create_new_list), color = Constants.black, fontSize = Constants.buttonFontSize, fontWeight = FontWeight.Medium)
    }
    Spacer(modifier = Modifier.width(10.dp))
    Button(
        enabled = 3!=disabled,
        onClick = onPreMadeListsClick,
        colors = buttonColors,
        shape = RoundedCornerShape(Constants.borderRadius),
        modifier = Modifier
            .width(Constants.width)
            .height(Constants.height)
    ) {
        Text(stringResource(id = R.string.pre_made_lists), color = Constants.black, fontSize = Constants.buttonFontSize, fontWeight = FontWeight.Medium)
    }
}