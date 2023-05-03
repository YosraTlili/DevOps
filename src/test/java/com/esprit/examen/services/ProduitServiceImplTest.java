package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplTest {

    @InjectMocks
    private ProduitServiceImpl produitServiceImpl;

    @Mock
    private ProduitRepository produitRepository;

    @Test
    public void testRetrieveAllProduits() {
        // given
        List<Produit> produits = new ArrayList<Produit>();
        produits.add(new Produit(1L, "P01", "Produit 1", 10, new Date(), new Date(), null, null, null));
        produits.add(new Produit(2L, "P02", "Produit 2", 20, new Date(), new Date(), null, null, null));
        when(produitRepository.findAll()).thenReturn(produits);

        // when
        List<Produit> retrievedProduits = produitServiceImpl.retrieveAllProduits();

        // then
        assertEquals(2, retrievedProduits.size());
    }

    @Test
    public void testAddProduit() {
        // given
        Produit produit = new Produit(null, "P01", "Produit 1", 10, new Date(), new Date(), null, null, null);
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // when
        Produit addedProduit = produitServiceImpl.addProduit(produit);

        // then
        assertEquals("P01", addedProduit.getCodeProduit());
    }


    @Test
    public void testUpdateProduit() {
        // given
        Produit produit = new Produit(1L, "P01", "Produit 1", 10, new Date(), new Date(), null, null, null);
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // when
        Produit updatedProduit = produitServiceImpl.updateProduit(produit);

        // then
        assertEquals("Produit 1", updatedProduit.getLibelleProduit());
    }




}