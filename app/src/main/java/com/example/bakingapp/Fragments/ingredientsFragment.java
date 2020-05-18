package com.example.bakingapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Adapter.ingriendentsAdapter;
import com.example.bakingapp.Model.ingredients;
import com.example.bakingapp.R;

import java.util.List;

public class ingredientsFragment extends Fragment
{



    List<ingredients> ingredientsList;
    RecyclerView ingredientsRecycler;
    RecyclerView.Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.ingredients_fragment_layout,container,false);
        ingredientsRecycler=view.findViewById(R.id.ingredientsRecycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ingredientsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecycler.setHasFixedSize(true);
        adapter=new ingriendentsAdapter(getIngredientsList(),getContext());

        ingredientsRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public List<ingredients> getIngredientsList()
    {
        return ingredientsList;
    }

    public void setIngredientsList(List<ingredients> ingredientsList)
    {
        this.ingredientsList = ingredientsList;
    }
}
