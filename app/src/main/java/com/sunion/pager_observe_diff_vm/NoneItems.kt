package com.sunion.pager_observe_diff_vm

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoneItems @Inject constructor(): CountCmd {

    private val _cmd = MutableSharedFlow<Boolean>()
    val cmd: SharedFlow<Boolean> = _cmd


    override fun onClick() {

    }
}