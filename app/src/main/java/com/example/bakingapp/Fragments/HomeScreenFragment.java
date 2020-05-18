package com.example.bakingapp.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Adapter.HomeAdapter;
import com.example.bakingapp.Model.Recipe;
import com.example.bakingapp.R;
import com.example.bakingapp.ViewModel.viewModel;
import com.example.bakingapp.INTERFACE.communicator;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenFragment extends Fragment
{
    private RecyclerView RecipeRecylerView;
    private RecyclerView.Adapter adapter;
    private List<Recipe> recipeList;
    ProgressBar progressBar;
    private List<Integer> recipeImages=new ArrayList<Integer>(){{
        add(R.drawable.nuterellapie);
        add(R.drawable.brownie);
        add(R.drawable.yellowcake);
        add(R.drawable.cheesecake);
    }};
    viewModel viewmodel;
    Context context;
    private communicator comm;
    public HomeScreenFragment()
    {

    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        try {
            comm=(communicator) context;

        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement communicator");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragement_homescreen_layout,container,false);
        RecipeRecylerView=view.findViewById(R.id.homeRecyclerView);
        progressBar=view.findViewById(R.id.progress);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        RecipeRecylerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        RecipeRecylerView.setHasFixedSize(true);
        viewmodel= ViewModelProviders.of(this).get(viewModel.class);
        progressBar.setVisibility(View.VISIBLE);
        viewmodel.getRecipeLiveData().observe(this, new Observer<List<Recipe>>()
        {
            @Override
            public void onChanged(List<Recipe> recipeList)
            {
                if (recipeList.size()<=0)
                {
                    Toast.makeText(getContext(),"No data found",Toast.LENGTH_SHORT).show();
                    return;

                }
                adapter=new HomeAdapter(recipeList,getContext(),comm);
                RecipeRecylerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
