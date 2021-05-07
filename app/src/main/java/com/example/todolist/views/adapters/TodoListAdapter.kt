package com.example.todolist.views.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.models.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapter(private var todos: MutableList<Todo>,
                      private val toggleListener: (position: Int, isChecked: Boolean) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {

    class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val todo = todos[position]

        holder.itemView.apply {
            tv_todo.text = todo.todoTitle
            cb_done.isChecked = todo.isDone

            toggleStrikeThrough(tv_todo, todo.isDone)
            cb_done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_todo, isChecked)
                toggleListener.invoke(position, isChecked)
            }
        }
    }

    fun toggleStrikeThrough(tvTodo: TextView, isDone:Boolean) {
        if (isDone) {
            tvTodo.paintFlags = tvTodo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodo.paintFlags = tvTodo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun updateTodosList(todos: MutableList<Todo>) {
        this.todos = todos
        notifyDataSetChanged()

    }
}

















