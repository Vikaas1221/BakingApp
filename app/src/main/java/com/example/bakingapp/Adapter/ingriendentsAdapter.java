package com.example.bakingapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Model.ingredients;
import com.example.bakingapp.R;

import java.util.List;

public class ingriendentsAdapter extends RecyclerView.Adapter<ingriendentsAdapter.ViewHolder>
{
    List<ingredients> ingredientsList;
    Context context;
    public ingriendentsAdapter(List<ingredients> ingredientsList, Context context)
    {
        this.ingredientsList=ingredientsList;
        this.context=context;
    }

    @NonNull
    @Override
    public ingriendentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingiendents_single_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ingriendentsAdapter.ViewHolder holder, int position)
    {
        ingredients obj=ingredientsList.get(position);
        holder.quantity.setText(obj.getQuantity()+"  ");
        holder.units.setText(obj.getMeasure()+"  ");
        holder.descp.setText(obj.getIngredient());

    }

    @Override
    public int getItemCount()
    {
        return ingredientsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView quantity,units,descp;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            quantity=itemView.findViewById(R.id.quantity);
            units=itemView.findViewById(R.id.unit);
            descp=itemView.findViewById(R.id.desc);
        }
    }
}
