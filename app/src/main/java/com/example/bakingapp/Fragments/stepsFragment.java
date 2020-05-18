package com.example.bakingapp.Fragments;

import android.content.Context;
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
import com.example.bakingapp.Adapter.stepsAdapter;
import com.example.bakingapp.INTERFACE.communicator;
import com.example.bakingapp.INTERFACE.stepsToVideoInterface;
import com.example.bakingapp.Model.steps;
import com.example.bakingapp.R;

import java.util.List;

public class stepsFragment extends Fragment
{

    List<steps> stepsList;
    RecyclerView stepsRecycler;
    RecyclerView.Adapter adapter;
    stepsToVideoInterface videoInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.steps_fragment_layout,container,false);
        stepsRecycler=view.findViewById(R.id.stepsReyclerView);
        return view;

    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        try {
            videoInterface=(stepsToVideoInterface) context;

        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement videoInteface");
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        stepsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsRecycler.setHasFixedSize(true);
        adapter=new stepsAdapter(getStepsList(),getContext(),videoInterface);
        stepsRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public List<steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<steps> stepsList) {
        this.stepsList = stepsList;
    }

}
