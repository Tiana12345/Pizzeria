package com.accenture.service;

import com.accenture.exception.IngredientException;
import com.accenture.repository.dao.IngredientDao;
import com.accenture.repository.entity.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientDao dao;

    public IngredientServiceImpl(IngredientDao dao) {
        this.dao = dao;
    }

    @Override
    public Ingredient ajouter(Ingredient ingredient) {
        if (ingredient == null)
            throw new IngredientException( "L'ingrédient doit exister");
        if (ingredient.getNom()==null)
            throw new IngredientException("Le nom de l'ingrédient ne peut pas être nul");
        if (ingredient.getQuantite()==null)
            throw new IngredientException("La quantité de l'ingrédient ne peut pas être nul");
        return dao.save(ingredient);
    }

    @Override
    public List<Ingredient> trouverToutes() {
        return dao.findAll();
    }
}

