package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonLoader {
    private final List<Pokemon> all_pokemons;

    // Constructeur prenant un chemin de fichier en argument
    public PokemonLoader(String filePath) {
        all_pokemons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                //System.out.println(columns[0]);
                if (columns.length == 10) {
                    int index = Integer.parseInt(columns[0]);
                    String name = columns[1];
                    int hp = Integer.parseInt(columns[2]);
                    int attack = Integer.parseInt(columns[3]);
                    int defense = Integer.parseInt(columns[4]);
                    int speed = Integer.parseInt(columns[5]);
                    int spAttack = Integer.parseInt(columns[6]);
                    int spDefense = Integer.parseInt(columns[7]);
                    int weight = Integer.parseInt(columns[8]);
                    int height = Integer.parseInt(columns[9]);

                    all_pokemons.add(new Pokemon(index, name, hp, attack, defense, speed, spAttack, spDefense, weight, height));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Pokemon> getAllPokemons(){
        return new ArrayList<>(all_pokemons);
    }

    public Pokemon getOnePokemon(int index) throws PokedexException{
        if (index >=0 && index < all_pokemons.size()){
            return all_pokemons.get(index);
        }else{
            throw new PokedexException("Invalid ID");
        }
    }
}