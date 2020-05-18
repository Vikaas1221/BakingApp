package com.example.bakingapp.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bakingapp.Model.Recipe;
import com.example.bakingapp.Repository.RecipeRepositry;

import java.util.List;

public class viewModel extends ViewModel
{
    LiveData<List<Recipe>> recipeLiveData=null;
    RecipeRepositry recipeRepositry=null;
    public viewModel()
    {
        recipeRepositry=new RecipeRepositry();
    }
    public LiveData<List<Recipe>> getRecipeLiveData()
    {
        if (recipeLiveData==null)
        {
            recipeLiveData=recipeRepositry.getAllRecipe();
        }
        List<Recipe> a=recipeLiveData.getValue();
        Log.d("vsc","in view model"+a);

        return recipeLiveData;
    }

}
