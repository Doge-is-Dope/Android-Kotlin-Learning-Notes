package com.chunchiehliang.kotlinflowsample

import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    data class ParentType(
        val id: Int,
        val title: String,
        val show: Boolean,
        val children: List<ChildType>
    )

    data class ChildType(val id: Int, val title: String)

    @Test
    fun nested_type() {
        val data = listOf(
            ParentType(
                0, "A", false,
                listOf(ChildType(0, "aaa"), ChildType(1, "aab"), ChildType(2, "aac"))
            ),
            ParentType(
                1, "B", true,
                listOf(ChildType(3, "bbb"))
            ),
            ParentType(
                2, "C", true,
                listOf(ChildType(4, "ccc"), ChildType(5, "cca"), ChildType(6, "ccb"))
            )
        )

        val childIdSet = setOf(3, 1, 2)

        val result = mutableListOf<Pair<String, List<String>>>()

        data.forEach { parentType ->
            val typeList = mutableListOf<String>()
            parentType.children.forEach { type ->
                if (type.id in childIdSet) typeList.add(type.title)
            }
            if (typeList.isNotEmpty()) result.add(
                Pair(if (parentType.show) parentType.title else "", typeList)
            )
        }

        val sb = StringBuilder()
        sb.append(result.joinToString(separator = " / ") { if (it.first.isBlank()) it.second.joinToString() else "${it.first} ${it.second.joinToString()}" })

        Assert.assertEquals("aab, aac / B bbb", sb.toString())
    }
}