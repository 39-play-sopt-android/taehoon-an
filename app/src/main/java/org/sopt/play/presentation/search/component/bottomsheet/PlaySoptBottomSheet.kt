package org.sopt.play.presentation.search.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.components.button.PlaySoptButton
import org.sopt.play.core.designsystem.components.textfield.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaySoptBottomSheet(
    titleState: TextFieldState,
    contentState: TextFieldState,
    onDismissRequest: () -> Unit,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
        ),
        containerColor = PlaySoptTheme.colors.white,
        scrimColor = Color.Black.copy(alpha = 0.4f),
        dragHandle = {
            BottomSheetHandle()
        },
    ) {
        PlaySoptBottomSheetContent(
            titleState = titleState,
            contentState = contentState,
            enabled = enabled,
            onCompleteClick = onCompleteClick
        )
    }
}

@Composable
private fun BottomSheetHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(width = 36.dp, height = 4.dp)
                .background(
                    color = PlaySoptTheme.colors.gray3,
                    shape = CircleShape,
                ),
        )
    }
}

@Composable
private fun PlaySoptBottomSheetContent(
    titleState: TextFieldState,
    contentState: TextFieldState,
    enabled: Boolean,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp
            ),
    ) {
        PlaySoptTextField(
            state = titleState,
            label = "제목",
            placeholder = "제목을 입력하세요.",
            keyboardType = KeyboardOptions.Default.keyboardType,
            imeAction = ImeAction.Next,
            maxLength = 20,
        )

        Spacer(modifier = Modifier.height(12.dp))

        PlaySoptTextField(
            state = contentState,
            label = "본문",
            placeholder = "본문을 입력하세요.",
            keyboardType = KeyboardOptions.Default.keyboardType,
            imeAction = ImeAction.Default,
            lineLimits = TextFieldLineLimits.MultiLine(),
            maxLength = 1000,
        )

        Spacer(modifier = Modifier.height(20.dp))

        PlaySoptButton(
            text = "완료",
            onClick = onCompleteClick,
            enabled = enabled,
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun PlaySoptBottomSheetPreview() {
    val titleState = rememberTextFieldState()
    val contentState = rememberTextFieldState()

    PlaySoptTheme {
        Column(
            modifier = Modifier.background(
                color = PlaySoptTheme.colors.white,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                ),
            ),
        ) {
            BottomSheetHandle()

            PlaySoptBottomSheetContent(
                titleState = titleState,
                contentState = contentState,
                enabled = true,
                onCompleteClick = {},
            )
        }
    }
}
