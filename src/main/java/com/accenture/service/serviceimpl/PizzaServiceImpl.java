package com.accenture.service.serviceimpl;

import com.accenture.exception.PizzaException;
import com.accenture.repository.dao.PizzaDao;
import com.accenture.repository.entity.Pizza;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl {

    private final PizzaDao pizzaDao;

    public PizzaServiceImpl(PizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    public Pizza ajouter(Pizza pizza) {
        verifPizza(pizza);
        return pizzaDao.save(pizza);
    }

    public Pizza modifierPartiellement(){

    }


//    _______________________________________________________________________________________________
//            METHODES PRIVEES
//    ______________________________________________________________________________________________
    private static void verifPizza(Pizza pizza) {
        if (pizza == null)
            throw new PizzaException("La pizza doit exister");
        if (pizza.getNom() == null || pizza.getNom().isBlank())
            throw new PizzaException("Le nom de la pizza ne peut pas être nul");
        if (pizza.getTaille() == null)
            throw new PizzaException("Vous devez renseigner la taille de la pizza");
        if (pizza.getIngredients() == null)
            throw new PizzaException("La taille de la pizza est obligatoire");
        if (pizza.getTarif() == null || pizza.getTarif() < 0)
            throw new PizzaException("Le tarif de la pizza doit être renseigné");
    }

}
