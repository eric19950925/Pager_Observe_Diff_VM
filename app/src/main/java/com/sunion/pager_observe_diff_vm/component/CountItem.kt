package com.sunion.pager_observe_diff_vm.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sunion.pager_observe_diff_vm.CountItemInfo
import com.sunion.pager_observe_diff_vm.MathOperator
import com.sunion.pager_observe_diff_vm.R

@Composable
fun CountItem(
    item: CountItemInfo,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_14)))

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_56)))
        ItemImage(item = item, onClick = onClick)
    }
}

@Composable
fun ItemImage(
    item: CountItemInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Button(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = when(item.ItemType){
            MathOperator.Addition -> colorResource(id = R.color.enable_green)
            MathOperator.Subtraction -> colorResource(id = R.color.redE60a17)
            MathOperator.Multiplication -> colorResource(id = R.color.log_error)
            MathOperator.Division -> colorResource(id = R.color.blue_info)
            else -> colorResource(id = R.color.white)
        }),
        enabled = when(item.ItemType){
            MathOperator.Addition, MathOperator.Subtraction, MathOperator.Multiplication, MathOperator.Division -> true
            else -> false
        },
        onClick = onClick
    ){ Text(text = when(item.ItemType){
        MathOperator.Addition -> "ADD 1"
        MathOperator.Subtraction -> "SUB 1"
        MathOperator.Multiplication -> "TIMES 1"
        MathOperator.Division -> "DIVISION"
        MathOperator.None -> item.ItemNum.toString() //not else
        else -> ""
    }) }

}