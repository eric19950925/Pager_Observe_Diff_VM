package com.sunion.pager_observe_diff_vm.add

import com.sunion.pager_observe_diff_vm.CountCmd
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdditionItems @Inject constructor(): CountCmd {
    private val _cmd = MutableSharedFlow<String>()
    val cmd: SharedFlow<String> = _cmd
    override fun onClick() {
        runBlocking {_cmd.emit("add")}
    }

}