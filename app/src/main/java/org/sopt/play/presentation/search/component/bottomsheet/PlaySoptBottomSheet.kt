package org.sopt.play.core.designsystem.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
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
    enabled: Boolean = true,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp,
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
            onCompleteClick = onCompleteClick,
            enabled = enabled,
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
                    shape = RoundedCornerShape(2.dp),
                ),
        )
    }
}

@Composable
private fun PlaySoptBottomSheetContent(
    titleState: TextFieldState,
    contentState: TextFieldState,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val isCompleteEnabled =
        enabled &&
                titleState.text.isNotBlank() &&
                contentState.text.isNotBlank() &&
                titleState.text.length <= 20 &&
                contentState.text.length <= 1_000

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 24.dp,
            ),
    ) {
        PlaySoptTextField(
            state = titleState,
            label = "제목",
            placeholder = "제목을 입력하세요.",
            keyboardType = KeyboardOptions.Default.keyboardType,
            lineLimits = TextFieldLineLimits.,
            imeAction = ImeAction.Next,
            enabled = enabled,
        )

        Spacer(modifier = Modifier.height(16.dp))

        BottomSheetTextField(
            state = contentState,
            label = "본문",
            placeholder = "본문을 입력하세요.",
            maxLength = 1_000,
            lineLimits = TextFieldLineLimits.MultiLine(
                minHeightInLines = 1,
                maxHeightInLines = 5,
            ),
            imeAction = ImeAction.Default,
            enabled = enabled,
        )

        Spacer(modifier = Modifier.height(20.dp))

        PlaySoptButton(
            text = "완료",
            onClick = onCompleteClick,
            enabled = isCompleteEnabled,
        )
    }
}
