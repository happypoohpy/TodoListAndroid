package com.example.todolist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.models.Todo

class MainViewModel() : ViewModel() {

    var todosLiveData = MutableLiveData<MutableList<Todo>>()
    var newAddedTodoPositionLiveData = MutableLiveData<Int>()

    init {
        todosLiveData.value = mutableListOf()
    }

    fun addTodo(todoText: String) {
        if (todoText.isEmpty()) {
            return
        }

        val todo = Todo(todoText)
        todosLiveData.value?.add(todo)

        //updating the observers happens after this line
        newAddedTodoPositionLiveData.value = todosLiveData.value?.size
    }

    fun deleteDone() {
        //updating all the observers after executing this line
        todosLiveData.value = todosLiveData.value?.apply {
            removeAll {todo ->
                todo.isDone
            }
        }
    }

    fun toggledCheckbox(position: Int, isChecked: Boolean) {
        todosLiveData.value?.get(position)?.isDone = isChecked
    }
}