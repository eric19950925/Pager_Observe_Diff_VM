package com.sunion.pager_observe_diff_vm.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.sunion.pager_observe_diff_vm.CountItemInfo
import com.sunion.pager_observe_diff_vm.HomeUiState
import com.sunion.pager_observe_diff_vm.MathOperator
import com.sunion.pager_observe_diff_vm.R


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CountItems(
    items: List<CountItemInfo>,
    pagerState: PagerState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        HorizontalPager(
            count = items.count(),
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            val item = items[page]

            CountItem(
                item = item,
                onClick = onClick
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = dimensionResource(id = R.dimen.space_37))
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                indicatorWidth = dimensionResource(id = R.dimen.space_6),
                modifier = Modifier.padding(16.dp),
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_35)))
            Text(
                text = "Ver. 1.0",
                style = TextStyle(
                    color = colorResource(id = R.color.primaryVariant),
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp
                )
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(device = Devices.NEXUS_5)
@Composable
private fun Preview() {
    CountItems(
        items = listOf(
            CountItemInfo(
                ItemName = "001", ItemType = MathOperator.Addition, ItemNum = 0
            ), CountItemInfo(
                ItemName = "002", ItemType = MathOperator.None, ItemNum = 0
            )
        ),
        pagerState = rememberPagerState(),
        onClick = {}
    )
}
