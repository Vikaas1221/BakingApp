package com.example.bakingapp.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bakingapp.Model.Recipe;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bakingapp.Model.ingredients;
import com.example.bakingapp.Model.steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import static com.example.bakingapp.Activity.MainActivity.context;


public class RecipeRepositry
{
    public List<Recipe> recipeList;
    public MutableLiveData<List<Recipe>> getAllRecipe()
    {
        Log.d("InReposity","In reposirtry");

        final String APIKEY="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        final MutableLiveData<List<Recipe>> data=new MutableLiveData<>();
       final List<Recipe> recipeList=new ArrayList<>();

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, APIKEY, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                for (int i=0;i<response.length();i++)
                {
                    String recipeName="";
                    String servings="";
                    String image="";
                    final List<steps> stepsList=new ArrayList<>();
                    final List<ingredients> ingredientsArrayList = new ArrayList<>();

                    JSONObject object=null;
                    try {
                        object=response.getJSONObject(i);
                        recipeName=object.getString("name");

                        servings=object.getString("servings");
                        image=object.getString("image");
                        Log.d("reciperNAme","Recipe Name: "+recipeName+"/"+"servings "+servings);
                        JSONArray jsonArrayIngredients=object.getJSONArray("ingredients");
                        Log.d("jsonIngresoieSize",""+jsonArrayIngredients.length());

                        for (int j=0;j<jsonArrayIngredients.length();j++)
                        {
                            JSONObject objectIngredients=(JSONObject) jsonArrayIngredients.get(j);
                            String quantity="";
                            String measure="";
                            String ingredient="";
                            quantity=objectIngredients.getString("quantity");
                            measure=objectIngredients.getString("measure");
                            ingredient=objectIngredients.getString("ingredient");
                            Log.d("ingredients12345","quant: "+quantity+"/"+ingredient);
                            ingredients objIngredients=new ingredients(quantity,measure,ingredient);
                            ingredientsArrayList.add(objIngredients);

                        }
                        JSONArray jsonArraySetps=object.getJSONArray("steps");

                        for (int k=0;k<jsonArraySetps.length();k++)
                        {
                            JSONObject objectSteps=(JSONObject) jsonArraySetps.get(k);
                            String shortDescription="";
                            String description="";
                            String videoURL="";
                            String thumbnailURL="";
                            shortDescription=objectSteps.getString("shortDescription");
                            description=objectSteps.getString("description");
                            videoURL=objectSteps.getString("videoURL");
                            thumbnailURL=objectSteps.getString("thumbnailURL");
                            Log.d("setRecipe","viderourl"+description);
                             steps objSteps=new steps(shortDescription,description,videoURL,thumbnailURL);
                             stepsList.add(objSteps);
                        }

                        Recipe recipe=new Recipe(recipeName,ingredientsArrayList,stepsList,servings,image);

                        recipeList.add(recipe);


                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                data.setValue(recipeList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return data;
    }
}
