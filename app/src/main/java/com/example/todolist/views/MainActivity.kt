package com.example.todolist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.models.Todo
import com.example.todolist.viewmodels.MainViewModel
import com.example.todolist.views.adapters.TodoListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : TodoListAdapter

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelProvider = ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
        viewModel = viewModelProvider.get(MainViewModel::class.java)

        observeData()

        adapter = TodoListAdapter(mutableListOf(), toggleListener = { position, isChecked ->
            viewModel.toggledCheckbox(position, isChecked)
        })

        rv_todo.adapter = adapter
        rv_todo.layoutManager = LinearLayoutManager(this)


        btn_add.setOnClickListener {
            val todoText = et_enter_todo.text.toString()

            viewModel.addTodo(todoText)
        }

        btn_delete.setOnClickListener {
            viewModel.deleteDone()
        }
    }

    fun observeData() {
        viewModel.todosLiveData.observe(this, Observer {todos ->
            adapter.updateTodosList(todos)
        })
        viewModel.newAddedTodoPositionLiveData.observe(this, Observer {position ->
            adapter.notifyItemInserted(position)
            et_enter_todo.text.clear()
        })
    }
}







