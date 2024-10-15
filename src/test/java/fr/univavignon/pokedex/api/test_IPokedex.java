package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class test_IPokedex {

    // Déclaration des mocks et objets nécessaires
    private IPokedex pokedex;
    private Pokemon bulbizarre;
    private Pokemon aquali;

    @Before
    public void setUp() {
        // Création du mock de l'interface IPokedex
        pokedex = mock(IPokedex.class);
        
        // Création de quelques objets Pokemon factices
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    }

    @Test
    public void testSize() {
        // Simuler la taille du pokedex
        when(pokedex.size()).thenReturn(2);
        
        // Vérifier la taille du pokedex
        assertEquals(2, pokedex.size());
        
        // Vérifier que la méthode size() a été appelée une fois
        verify(pokedex, times(1)).size();
    }

    @Test
    public void testAddPokemon() {
        // Simuler l'ajout d'un Pokémon au pokedex
        when(pokedex.addPokemon(bulbizarre)).thenReturn(0);
        
        // Ajouter un Pokémon au pokedex et vérifier l'index retourné
        assertEquals(0, pokedex.addPokemon(bulbizarre));
        
        // Vérifier que la méthode addPokemon a été appelée avec le bon Pokémon
        verify(pokedex, times(1)).addPokemon(bulbizarre);
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Simuler la récupération d'un Pokémon par son id
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        
        // Récupérer le Pokémon avec l'ID 0 et vérifier le résultat
        assertEquals(bulbizarre, pokedex.getPokemon(0));
        
        // Vérifier que la méthode getPokemon a été appelée avec l'ID 0
        verify(pokedex, times(1)).getPokemon(0);
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonException() throws PokedexException {
        // Simuler une exception si l'ID n'est pas valide
        when(pokedex.getPokemon(999)).thenThrow(new PokedexException("Invalid ID"));
        
        // Essayer de récupérer un Pokémon avec un ID non valide (999) -> car on a l'ID 0 et 133
        pokedex.getPokemon(999);
    }

    @Test
    public void testGetPokemons() {
        // Simuler la liste des Pokémons
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(bulbizarre);
        pokemonList.add(aquali);
        
        // Simuler le retour de la liste des Pokémons
        when(pokedex.getPokemons()).thenReturn(pokemonList);
        
        // Récupérer la liste des Pokémons et vérifier son contenu
        List<Pokemon> result = pokedex.getPokemons();
        assertEquals(2, result.size());
        assertTrue(result.contains(bulbizarre));
        assertTrue(result.contains(aquali));
        
        // Vérifier que la méthode getPokemons a été appelée
        verify(pokedex, times(1)).getPokemons();
    }

    @Test
    public void testGetPokemonsWithOrder() {
        // Simuler un comparateur pour trier les Pokémons par leur nom
        Comparator<Pokemon> idComparator = Comparator.comparing(Pokemon::getIndex);
        
        // Simuler une liste triée
        List<Pokemon> sortedList = new ArrayList<>();
        sortedList.add(bulbizarre);  // Bulbizarre (après tri)
        sortedList.add(aquali);  // Aquali
        
        // Simuler le retour de la liste triée des Pokémons
        when(pokedex.getPokemons(idComparator)).thenReturn(sortedList);
        
        // Récupérer la liste triée des Pokémons et vérifier son contenu
        List<Pokemon> result = pokedex.getPokemons(idComparator);
        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getIndex());
        assertEquals(133, result.get(1).getIndex());
        
        // Vérifier que la méthode getPokemons(Comparator) a été appelée
        verify(pokedex, times(1)).getPokemons(idComparator);
    }
}