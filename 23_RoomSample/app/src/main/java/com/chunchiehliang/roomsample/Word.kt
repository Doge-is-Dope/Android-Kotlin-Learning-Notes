package com.chunchiehliang.roomsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "word") val word: String,
)
