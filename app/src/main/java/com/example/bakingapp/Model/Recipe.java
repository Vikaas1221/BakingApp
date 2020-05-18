package com.example.bakingapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe
{
    private String name;
    private List<ingredients> ingredientsList;
    private List<steps> stepsList;
    private String servings;
    private String image;

    public Recipe(String name, List<ingredients> ingredientsList, List<steps> stepsList, String servings, String image) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
        this.servings = servings;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public List<ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public List<steps> getStepsList() {
        return stepsList;
    }

    public String getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }


}
