package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

import com.example.chatapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Todo> todoList = new ArrayList<>();
        todoList.add(new Todo("To watch", false));

        TodoAdapter todoAdapter = new TodoAdapter(todoList, position -> {

        });

        binding.rvTodoItems.setAdapter(todoAdapter);
        binding.rvTodoItems.setLayoutManager(new LinearLayoutManager(this));

        binding.btnAddTodo.setOnClickListener(v -> {
            Editable text = binding.etTodoTitle.getText();
            if (text != null && !text.toString().equals(""))
                todoAdapter.addTodo(new Todo(text.toString(), false));
        });

        binding.btnDelete.setOnClickListener(v -> {
            todoAdapter.deleteAll();
        });

    }


}