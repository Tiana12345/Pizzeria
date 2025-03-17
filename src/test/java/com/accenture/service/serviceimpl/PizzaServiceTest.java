package com.accenture.service.serviceimpl;

import com.accenture.exception.PizzaException;
import com.accenture.model.Taille;
import com.accenture.repository.dao.PizzaDao;
import com.accenture.repository.entity.Ingredient;
import com.accenture.repository.entity.Pizza;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {
   @InjectMocks
   private PizzaServiceImpl service;
   @Mock
   private PizzaDao dao;


    @Test
    void ajouterUnePizzaOk (){
        Pizza pizza = margherita();
        Pizza pizzaSave = margherita();
        pizzaSave.setId(1);
        Mockito.when(dao.save(pizza)).thenReturn(pizzaSave);
        Pizza pizzaEnreg = assertDoesNotThrow(()-> service.ajouter(pizza));
        assertSame(pizzaEnreg, pizzaSave);
        Mockito.verify(dao).save(pizza);

    }
    @Test
    void ajouterUnePizzaNomNull() {
        Pizza pizza = margherita();
        pizza.setNom(null);
        PizzaException pe = assertThrows(PizzaException.class, ()-> service.ajouter(pizza));
        assertEquals("Le nom de la pizza ne peut pas être nul", pe.getMessage());
    }
    @Test
    void ajouterUnePizzaNomBlank() {
        Pizza pizza = margherita();
        pizza.setNom("   \t");
        PizzaException pe = assertThrows(PizzaException.class, ()-> service.ajouter(pizza));
        assertEquals("Le nom de la pizza ne peut pas être nul", pe.getMessage());
    }
    @Test
    void ajouterPizzaTailleNulle(){
        Pizza pizza =margherita();
        pizza.setTaille(null);
        PizzaException pe = assertThrows(PizzaException.class, ()-> service.ajouter(pizza));
        assertEquals("Vous devez renseigner la taille de la pizza", pe.getMessage());
    }
    @Test
    void ajouterPizzaTarifNulle(){
        Pizza pizza =margherita();
        pizza.setTarif(null);
        PizzaException pe = assertThrows(PizzaException.class, ()-> service.ajouter(pizza));
        assertEquals("Le tarif de la pizza doit être renseigné", pe.getMessage());
    }
    @Test
    void ajouterPizzaTarisInf0(){
        Pizza pizza =margherita();
        pizza.setTarif(-1.0);
        PizzaException pe = assertThrows(PizzaException.class, ()-> service.ajouter(pizza));
        assertEquals("Le tarif de la pizza doit être renseigné", pe.getMessage());
    }





//    ___________________________________________________________
//            METHODES PRIVEES
//    ___________________________________________________________

    private static Pizza margherita() {
        Pizza pizza = new Pizza("Margherita", Taille.PETITE, 12.9, listIingredients());
        return pizza;
    }

    private static List<Ingredient> listIingredients() {
        Ingredient tomate = new Ingredient("Tomate", 7);
        Ingredient emmental = new Ingredient("Emmental", 8);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(tomate);
        ingredients.add(emmental);
        return ingredients;
    }
}
