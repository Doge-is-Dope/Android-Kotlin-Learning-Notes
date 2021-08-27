package com.chunchiehliang.hiltsample

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.chunchiehliang.hiltsample.data.AppDatabase
import com.chunchiehliang.hiltsample.data.LoggerLocalDataSource
import com.chunchiehliang.hiltsample.navigator.AppNavigator
import com.chunchiehliang.hiltsample.navigator.AppNavigatorImpl
import com.chunchiehliang.hiltsample.util.DateFormatter

class ServiceLocator(applicationContext: Context) {

    private val logsDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "logging.db"
    ).build()

    val loggerLocalDataSource = LoggerLocalDataSource(logsDatabase.logDao())

    fun provideDateFormatter() = DateFormatter()

    fun provideNavigator(activity: FragmentActivity): AppNavigator {
        return AppNavigatorImpl(activity)
    }
}
