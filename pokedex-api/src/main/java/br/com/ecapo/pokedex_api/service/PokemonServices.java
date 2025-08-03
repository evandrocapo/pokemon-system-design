package br.com.ecapo.pokedex_api.service;

import br.com.ecapo.pokedex_api.model.Pokemon;
import br.com.ecapo.pokedex_api.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PokemonServices {

    @Autowired
    private final PokemonRepository pokemonRepository;
    private Logger logger;

    // Este construtor permite a injeção do Logger pelo Mockito
    public PokemonServices(PokemonRepository pokemonRepository, Logger logger) {
        this.pokemonRepository = pokemonRepository;
        this.logger = logger;
    }

    // Este construtor é para o Spring
    public PokemonServices(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.logger = Logger.getLogger(PokemonServices.class.getName());
    }

    public List<Pokemon> findAll(){
        List<Pokemon> pokemons = pokemonRepository.findAll();

        logger.info("Pokemons encontrados com sucesso");

        return pokemons;
    }

    public Pokemon create(Pokemon newPokemon) {
        Pokemon pokemon = pokemonRepository.save(newPokemon);

        logger.info("Pokemon criado com sucesso");

        return pokemon;
    }

    public Pokemon findById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException());

        logger.info("Pokemon encontrado com sucesso");
        return pokemon;
    }

    public Pokemon update(Pokemon newPokemon) {
        Pokemon pokemon = pokemonRepository.findById(newPokemon.getId()).orElseThrow(() -> new RuntimeException());

        pokemon.setNome(newPokemon.getNome());
        pokemon.setTipo(newPokemon.getTipo());
        pokemon.setHabilidades(newPokemon.getHabilidades());

        pokemonRepository.save(pokemon);

        logger.info("Pokemon atualizado com sucesso");
        return pokemon;
    }

    public void delete(Pokemon pokemon) {
        pokemonRepository.deleteById(pokemon.getId());
        logger.info("Pokemon deletado com sucesso");
    }
}
