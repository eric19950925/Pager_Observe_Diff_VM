package com.sunion.pager_observe_diff_vm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.sunion.pager_observe_diff_vm.component.CountItems
import com.sunion.pager_observe_diff_vm.component.Empty
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
){
    val uiState = homeViewModel.uiState.collectAsState().value

    HomeScreen(
        state = uiState,
        onClick = homeViewModel::onItemClick,
        onPageChangeByUser = homeViewModel::setCurrentPage,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    onClick: () -> Unit,
    onPageChangeByUser: (Int) -> Unit = {},
    currentPage: Int = 0,
    modifier: Modifier = Modifier,
){
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()
    val openAddDialog = remember { mutableStateOf(false) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChangeByUser(page)
        }
    }

    LaunchedEffect(key1 = currentPage) {
        pagerState.animateScrollToPage(currentPage)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space_20)))
                    Image(
                        painter = painterResource(id = R.drawable.ic_list),
                        contentDescription = "List",
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.space_32))
                            .clickable { coroutineScope.launch { scaffoldState.drawerState.open() } }
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add_circle),
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.space_32))
                            .clickable(onClick = {
                                openAddDialog.value = true
                            })
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space_20)))
                },
                title = {},
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        drawerContent = {},
        drawerBackgroundColor = Color.White,
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center) {
            if (state.items.isEmpty())
                Empty(modifier = Modifier.align(Alignment.Center))
            else
                CountItems(
                    items = state.items,
                    pagerState = pagerState,
                    onClick = onClick,
                )
        }
    }
}