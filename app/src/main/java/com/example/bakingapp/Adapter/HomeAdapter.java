package com.example.bakingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Model.Recipe;
import com.example.bakingapp.R;
import com.example.bakingapp.INTERFACE.communicator;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder>
{
    private List<Integer> recipeImages=new ArrayList<Integer>(){{
        add(R.drawable.nuterellapie);
        add(R.drawable.brownie);
        add(R.drawable.yellowcake);
        add(R.drawable.cheesecake);
    }};
    List<Recipe> recipeList;
    Context context;
    ProgressBar progressBar;
    communicator com;

    public HomeAdapter(List<Recipe> recipeList,Context context,communicator com)
    {
        this.recipeList=recipeList;
        this.context=context;
        this.com=com;
    }
    @NonNull
    @Override
    public HomeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_single_item_layout,parent,false);
        Viewholder holder=new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.Viewholder holder, int position)
    {
        final Recipe recipe=recipeList.get(position);
        holder.recipeName.setText(recipe.getName());
        holder.recipeImage.setImageResource(recipeImages.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                com.communicate(recipe);

            }
        });



    }

    @Override
    public int getItemCount()
    {
        return recipeList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
        ImageView recipeImage;
        TextView recipeName;
        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            recipeImage=itemView.findViewById(R.id.recipeImage);
            recipeName=itemView.findViewById(R.id.recipeName);
            linearLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
