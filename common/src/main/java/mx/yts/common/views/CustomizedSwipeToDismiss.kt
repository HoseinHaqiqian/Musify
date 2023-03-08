package mx.yts.common.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomizedSwipeToDismiss(
    state: DismissState,
    directions: Set<DismissDirection>,
    content: @Composable RowScope.() -> Unit
) {
    SwipeToDismiss(
        state = state,
        dismissThresholds = { dismissDirection -> FractionalThreshold(0.2f) },
        background = {
            val color by animateColorAsState(
                targetValue = when (state.targetValue) {
                    DismissValue.Default -> Color.Cyan
                    else -> Color.Red
                }
            )

            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            val scale by animateFloatAsState(
                if (state.targetValue == DismissValue.Default) 0.75f else 1f
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 8.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    icon,
                    contentDescription = "Delete Icon",
                    modifier = Modifier.scale(scale)
                )
            }

        },
        directions = directions,
        dismissContent = content
    )
}