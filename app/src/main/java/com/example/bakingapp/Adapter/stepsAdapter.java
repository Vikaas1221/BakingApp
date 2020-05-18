package com.example.bakingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.INTERFACE.stepsToVideoInterface;
import com.example.bakingapp.Model.steps;
import com.example.bakingapp.R;

import java.util.List;

public class stepsAdapter extends RecyclerView.Adapter<stepsAdapter.ViewHolder>
{
    List<steps> stepsList;
    Context context;
    stepsToVideoInterface videoInterface;
    public stepsAdapter(List<steps> stepsList, Context context, stepsToVideoInterface videoInterface)
    {
        this.stepsList=stepsList;
        this.context=context;
        this.videoInterface=videoInterface;
    }

    @NonNull
    @Override
    public stepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_single_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull stepsAdapter.ViewHolder holder, int position)
    {
        final steps obj=stepsList.get(position);
        holder.stepname.setText(obj.getShortDescription());
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoInterface.playVideo(obj.getVideoURL(),obj.getDescription());
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return stepsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView stepname;
        ImageView playButton;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            stepname=itemView.findViewById(R.id.stepName);
            playButton=itemView.findViewById(R.id.play);
        }
    }
}
