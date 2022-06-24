package com.sunion.pager_observe_diff_vm

import com.sunion.pager_observe_diff_vm.add.AdditionItems
import com.sunion.pager_observe_diff_vm.division.DivisionItems
import com.sunion.pager_observe_diff_vm.multi.MultiplicationItems
import com.sunion.pager_observe_diff_vm.sub.SubtractionItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [AppModule.Bind::class])
object AppModule {
    @Provides
    @Singleton
    fun provideAdditionItems() = AdditionItems()

    @Provides
    @Singleton
    fun provideSubtractionItems() = SubtractionItems()

    @Provides
    @Singleton
    fun provideMultiplicationItems() = MultiplicationItems()

    @Provides
    @Singleton
    fun provideDivisionItems() = DivisionItems()

    @Provides
    @Singleton
    fun provideNoneItems() = NoneItems()

    @InstallIn(SingletonComponent::class)

    @Module
    abstract class Bind {}
}