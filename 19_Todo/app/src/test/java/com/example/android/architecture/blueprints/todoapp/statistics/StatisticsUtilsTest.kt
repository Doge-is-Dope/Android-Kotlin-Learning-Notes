package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {

    // If there is no completed task and one active task,
    // then there are 100% active tasks and 0% completed tasks
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {

        // Create an active tasks (the false makes this active)
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }

    // If there are 2 completed tests and 3 active test
    // then there are 60% active tasks and 40% completed tasks
    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {

        // Create an active tasks (the false makes this active)
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZero() {

        // Create an active tasks (the false makes this active)
        val tasks = emptyList<Task>()
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZero() {

        // Create an active tasks (the false makes this active)
        val tasks = null
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }
}

