package com.accenture.service.dto;

import com.accenture.model.Taille;
import com.accenture.repository.entity.Ingredient;

import java.util.List;

public record PizzaRequestDto(
        int id,
        String nom,
        List<Ingredient> ingredients,
        Taille taille,
        Double tarif
) {
}
