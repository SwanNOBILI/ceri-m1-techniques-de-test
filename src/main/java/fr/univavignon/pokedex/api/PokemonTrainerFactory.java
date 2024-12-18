package fr.univavignon.pokedex.api;

/**
 * Factory class for creating PokemonTrainer instances.
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    /**
     * Creates a PokemonTrainer instance using the given name, team, and pokedex factory.
     * 
     * @param name Name of the trainer.
     * @param team Team of the trainer.
     * @param pokedexFactory Factory to use for creating the trainer's associated pokedex.
     * @return Created PokemonTrainer instance.
     */
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        // Create a new Pokedex using the provided factory
        IPokedex pokedex = pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory());
        
        // Return a new PokemonTrainer instance with the provided details and created Pokedex
        return new PokemonTrainer(name, team, pokedex);
    }
}
