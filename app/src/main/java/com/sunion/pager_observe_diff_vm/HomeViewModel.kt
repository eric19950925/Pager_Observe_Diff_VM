package com.sunion.pager_observe_diff_vm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunion.pager_observe_diff_vm.add.AdditionItems
import com.sunion.pager_observe_diff_vm.division.DivisionItems
import com.sunion.pager_observe_diff_vm.multi.MultiplicationItems
import com.sunion.pager_observe_diff_vm.sub.SubtractionItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val additionItems: AdditionItems,
    private val subtractionItems: SubtractionItems,
    private val divisionItems: DivisionItems,
    private val multiplicationItems: MultiplicationItems,
    private val noneItems: NoneItems,
) : ViewModel(){
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
    //製作list
    private var countItemList = mutableStateListOf<CountItemInfo>()
//    val countItemList: List<CountItemInfo> = _countItemList
    private var currentIndex = 0
    val addCmd: SharedFlow<String> = additionItems.cmd
    val multiplicationCmd: SharedFlow<String> = multiplicationItems.cmd

    init {
        val myCountItemList = listOf<CountItemInfo>(
            CountItemInfo(ItemName = "001", ItemType = MathOperator.Addition, ItemNum = 0),
            CountItemInfo(ItemName = "002", ItemType = MathOperator.None, ItemNum = 0),
            CountItemInfo(ItemName = "003", ItemType = MathOperator.Multiplication, ItemNum = 0)
        )

        countItemList.addAll(myCountItemList)
        _uiState.update { it.copy(items = countItemList) }

        collectAddCmd()
        collectMultiCmd()
    }
    //紀錄目前頁面的項目，依據type決定click行為

    fun onItemClick(){
        val item = countItemList[currentIndex]
        when(item.ItemType){
            MathOperator.Addition -> { additionItems.onClick() }
            MathOperator.Subtraction -> { subtractionItems.onClick() }
            MathOperator.Multiplication -> { multiplicationItems.onClick() }
            MathOperator.Division -> { divisionItems.onClick() }
            MathOperator.None -> {}
        }
    }

    private fun collectAddCmd(){
        addCmd.onEach {
            countItemList.find { it.ItemName == "002" && it.ItemType == MathOperator.None }
                ?.let {
                    it.ItemNum += 1
                }
            Timber.d("item num:${countItemList.find { it.ItemName == "002" && it.ItemType == MathOperator.None }?.ItemNum}")
            _uiState.update { it.copy(items = countItemList) }
        }.catch { Timber.e(it) }.launchIn(viewModelScope)
    }

    private fun collectMultiCmd(){
        multiplicationCmd.onEach {
            countItemList.find { it.ItemName == "002" && it.ItemType == MathOperator.None }
                ?.let {
                    it.ItemNum = it.ItemNum * 2
                }
            Timber.d("item num:${countItemList.find { it.ItemName == "002" && it.ItemType == MathOperator.None }?.ItemNum}")
            _uiState.update { it.copy(items = countItemList) }
        }.catch { Timber.e(it) }.launchIn(viewModelScope)
    }

    //換頁更新ui
    fun setCurrentPage(page: Int) {
//        Timber.d("page:$page")
//        Timber.d("items:${countItemList[page].ItemNum}")
        _uiState.update { it.copy(currentPage = page) }
        currentIndex = page

        loadLockState(page)
    }

    private fun loadLockState(index: Int) {
        if (countItemList.isEmpty() || index > countItemList.lastIndex)
            return

        val item = countItemList[index]

        flow { emit(
            _uiState.update { it.copy(items = countItemList) }
        ) }
            .flowOn(Dispatchers.IO)
            .catch { Timber.e(it) }
            .launchIn(viewModelScope)

    }
}
data class HomeUiState(
    val items: MutableList<CountItemInfo> = mutableStateListOf(),
    val currentPage: Int = 0,
)

data class CountItemInfo (
    val ItemName: String,
    val ItemType: Int,
    var ItemNum: Int,
)

object MathOperator {
    const val Addition = 1
    const val Subtraction = 2
    const val Multiplication = 3
    const val Division = 4
    const val None = 5
}