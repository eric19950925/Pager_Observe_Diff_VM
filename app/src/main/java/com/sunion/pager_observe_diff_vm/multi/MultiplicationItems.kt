package com.sunion.pager_observe_diff_vm.multi

import com.sunion.pager_observe_diff_vm.CountCmd
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MultiplicationItems @Inject constructor(): CountCmd{
    private val _cmd = MutableSharedFlow<String>()
    val cmd: SharedFlow<String> = _cmd
    override fun onClick() {
        runBlocking {_cmd.emit("multi")}
    }
}