package com.example.bakingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.bakingapp.Fragments.ingredientsFragment;
import com.example.bakingapp.Fragments.stepsFragment;
import com.example.bakingapp.Fragments.videoFragment;
import com.example.bakingapp.INTERFACE.stepsToVideoInterface;
import com.example.bakingapp.Model.Recipe;
import com.example.bakingapp.Model.ingredients;
import com.example.bakingapp.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity implements stepsToVideoInterface
{
    LinearLayout linearLayout;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (findViewById(R.id.linear_layout_tablet)!=null)
        {
            isTwoPane=true;
            Log.d("in_two_pane","in two pane");

            if (savedInstanceState==null)
            {
                Log.d("in_two_pane","in two inner pane");
                Gson gson=new Gson();
                Recipe recipe=gson.fromJson(getIntent().getStringExtra("jsonRecipe"),Recipe.class);

                ingredientsFragment fragment=new ingredientsFragment();
                fragment.setIngredientsList(recipe.getIngredientsList());
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction().add(R.id.ingredientsContainer,fragment).commit();

                stepsFragment stepsfragment=new stepsFragment();
                stepsfragment.setStepsList(recipe.getStepsList());
                FragmentManager manager2=getSupportFragmentManager();
                manager2.beginTransaction().add(R.id.stepsContainer,stepsfragment).commit();

                videoFragment videofragment=new videoFragment();
                FragmentManager manager3=getSupportFragmentManager();
                manager3.beginTransaction().add(R.id.videoContainer,videofragment).commit();
            }
        }
        else
            {
            isTwoPane = false;
                Log.d("in_one_pane","in one pane");
            if (savedInstanceState == null)
            {
                Log.d("in_one_pane","in one inner pane");
                // Reciving data from mainactivity
                Gson gson = new Gson();
                Recipe recipe = gson.fromJson(getIntent().getStringExtra("jsonRecipe"), Recipe.class);
                for (int i = 0; i < recipe.getIngredientsList().size(); i++)
                {
                    ingredients obj = recipe.getIngredientsList().get(i);
                    Log.d("ingredientsInDetail", "" + obj.getMeasure() + "/" + obj.getIngredient() + "/" + obj.getQuantity());
                }


                ingredientsFragment fragment = new ingredientsFragment();
                fragment.setIngredientsList(recipe.getIngredientsList());
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.ingredientsContainer, fragment).commit();

                stepsFragment stepsfragment = new stepsFragment();
                stepsfragment.setStepsList(recipe.getStepsList());
                FragmentManager manager2 = getSupportFragmentManager();
                manager2.beginTransaction().add(R.id.stepsContainer, stepsfragment).commit();
            }
        }
    }

    @Override
    public void playVideo(String videoUrl, String description)
    {
        if (isTwoPane)
        {
            videoFragment v_fragment=new videoFragment();
            v_fragment.setVideoUrl(videoUrl);
            v_fragment.setSteps(description);
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().add(R.id.videoContainer,v_fragment).commit();

        }
        else
            {
            Intent intent = new Intent(DetailActivity.this, RecipeStepDetailActivity.class);
            intent.putExtra("videourl", videoUrl);
            intent.putExtra("description", description);
            startActivity(intent);
        }
    }
}
