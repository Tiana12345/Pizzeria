package com.accenture.service;

import com.accenture.repository.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient ajouter(Ingredient ingredient);
     List<Ingredient> trouverToutes();
}

