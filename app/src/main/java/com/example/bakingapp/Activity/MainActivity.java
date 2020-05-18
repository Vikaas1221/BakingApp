package com.example.bakingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bakingapp.Model.Recipe;
import com.example.bakingapp.R;
import com.example.bakingapp.ViewModel.viewModel;
import com.example.bakingapp.INTERFACE.communicator;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements communicator {
    public static Context context;
    private RecyclerView RecipeRecylerView;
    private RecyclerView.Adapter adapter;
    private List<Recipe> recipeList;
    private ProgressBar progressBar;
    FrameLayout layout;

    viewModel viewmodel;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;


    }

    @Override
    public void communicate(Recipe recipe)
    {
        Toast.makeText(getApplicationContext(),""+recipe.getName(),Toast.LENGTH_SHORT).show();
        Gson  gson=new Gson();
        String mjson=gson.toJson(recipe);
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("jsonRecipe",mjson);
        startActivity(intent);


    }
}
