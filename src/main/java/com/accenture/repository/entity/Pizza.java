package com.accenture.repository.entity;

import com.accenture.model.Taille;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;
    @ManyToMany
    List<Ingredients> listeIngredients;
    @Enumerated(EnumType.STRING)
    Taille taille;
    Double tarif;
}
