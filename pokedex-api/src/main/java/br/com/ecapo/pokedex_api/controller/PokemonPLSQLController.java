package br.com.ecapo.pokedex_api.controller;

import br.com.ecapo.pokedex_api.model.Pokemon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/pokemonSql")
public class PokemonPLSQLController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon get() {
        String query = "SELECT * FROM POKEMON WHERE POKEMON.ID = 52";
//        String queryHabilidades = "SELECT HABILIDADE FROM POKEMON JOIN POKEMON_HABILIDADES ON POKEMON.ID = pokemon_habilidades.pokemon_id WHERE POKEMON.ID = 52";
        String queryHabilidades = "SELECT HABILIDADE FROM POKEMON_HABILIDADES WHERE POKEMON_ID = 52";


        Pokemon pokemon = (Pokemon) entityManager.createNativeQuery(query, Pokemon.class).getSingleResult();
        List<String> habilidades = entityManager.createNativeQuery(queryHabilidades).getResultList();

        Pokemon newPokemon = new Pokemon();

        newPokemon.setId(pokemon.getId());
        newPokemon.setNome(pokemon.getNome());
        newPokemon.setTipo(pokemon.getTipo());

        newPokemon.setHabilidades(habilidades);

        return newPokemon;
    }
}
