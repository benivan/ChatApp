package com.example.chatapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemTodoBinding;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<Todo> todoList;

    private static final String TAG = "TodoAdapter";
    private ItemClicked itemClicked;

    public TodoAdapter(List<Todo> todoList, ItemClicked itemClicked) {
        this.todoList = todoList;
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemTodoBinding todoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TodoViewHolder(todoBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.itemTodoBinding.getRoot().setOnClickListener(v -> {
            itemClicked.whichItemClicked(position);
        });

        holder.bind(todoList.get(position), isChecked -> {
            Log.d(TAG, String.format("Item position is %d and isChecked: %s", position, isChecked));
            todoList.get(position).isChecked = isChecked;
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void addTodo(Todo todo) {
        todoList.add(todo);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        todoList = new ArrayList<>();
        notifyDataSetChanged();
    }


    static class TodoViewHolder extends RecyclerView.ViewHolder {

        private final ItemTodoBinding itemTodoBinding;

        public TodoViewHolder(ItemTodoBinding todoBinding) {
            super(todoBinding.getRoot());
            this.itemTodoBinding = todoBinding;
        }

        public void bind(Todo todo, TodoItemClicked itemClicked) {



            itemTodoBinding.tvTodoTitle.setText(todo.title);
            itemTodoBinding.checkBox2.setChecked(todo.isChecked);

            itemTodoBinding.checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
                itemTodoBinding.checkBox2.setChecked(isChecked);
                itemClicked.itemCheckChanged(isChecked);
            });
        }
    }

    interface TodoItemClicked {
        void itemCheckChanged(boolean isChecked);
    }


    interface ItemClicked {
        void whichItemClicked(Integer position);
    }


}
