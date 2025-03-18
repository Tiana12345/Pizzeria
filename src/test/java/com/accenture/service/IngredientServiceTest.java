package com.accenture.service;

import com.accenture.exception.IngredientException;
import com.accenture.repository.dao.IngredientDao;
import com.accenture.repository.entity.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {
    @InjectMocks
    private IngredientServiceImpl service;
    @Mock
    private IngredientDao dao;

    @Test
    void testAjouterOK() {
        Ingredient ingredient = getTomate();
        Ingredient ingredientSave = getTomate();
        ingredientSave.setId(1);
        Mockito.when(dao.save(ingredient)).thenReturn(ingredientSave);
       Ingredient ingredientEnreg = assertDoesNotThrow(() -> service.ajouter(ingredient));
        assertSame(ingredientEnreg, ingredientSave);
        Mockito.verify(dao).save(ingredient);
    }

    @Test
    void  testAjouterIngredientNull(){
        IngredientException ex = assertThrows(IngredientException.class, () -> service.ajouter(null));
        Assertions.assertEquals("L'ingrédient doit exister", ex.getMessage());
    }

    @Test
    void  testAjouterIngredientNomNull(){
        Ingredient ingredient = getTomate();
        ingredient.setNom(null);
        IngredientException ex = assertThrows(IngredientException.class, () -> service.ajouter(ingredient));
        Assertions.assertEquals("Le nom de l'ingrédient ne peut pas être nul", ex.getMessage());
    }

    @Test
    void  testAjouterIngredienQuantiteNull(){
        Ingredient ingredient = getTomate();
        ingredient.setQuantite(null);
        IngredientException ex = assertThrows(IngredientException.class, () -> service.ajouter(ingredient));
        Assertions.assertEquals("La quantité de l'ingrédient ne peut pas être nul", ex.getMessage());
    }

    @Test
    void testListerIngredient(){
        Ingredient ingredient = getTomate();
        Ingredient ingredient1 = getOlive();
        List<Ingredient> liste = List.of(getOlive(), getTomate() );
        Mockito.when(dao.findAll()).thenReturn(liste);
        assertEquals(liste, service.trouverToutes());
    }

    @Test
    void testModifierQuantite(){

       /* @Test
        void testModifPartielNouveauNom() {
            Client client = creerClient();
            Optional<Client> optionalClient = Optional.of(client);
            Mockito.when(clientDaoMock.findById(1)).thenReturn(optionalClient);
            ClientRequestDto clientRequestDto = creerClientRequestDto();
            Mockito.when(clientMapperMock.toClient(clientRequestDto)).thenReturn(client);
            Client clientNouveauNom = creerClientNouveauNom();
            ClientResponseDto clientResponseDto = creerClientResponseDto();
            Mockito.when(clientDaoMock.save(client)).thenReturn(clientNouveauNom);
            Mockito.when(clientMapperMock.toClientResponseDto(clientNouveauNom)).thenReturn(clientResponseDto);
            assertEquals(clientResponseDto,  service.modifierPartiellement(1, clientRequestDto) );

        }*/
    }

    private static Ingredient getTomate() {
        return new Ingredient("Tomate", 3);
    }
    private static Ingredient getOlive() {
        return new Ingredient("Olive", 3);
    }

}
