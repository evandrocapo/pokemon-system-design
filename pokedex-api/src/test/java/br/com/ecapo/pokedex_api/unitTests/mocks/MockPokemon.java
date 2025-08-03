package br.com.ecapo.pokedex_api.unitTests.mocks;

import br.com.ecapo.pokedex_api.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MockPokemon {

    public Pokemon mockEntity() {
        List<String> habilidades = new ArrayList<String>();

        habilidades.add("trovão");
        habilidades.add("ataque rapido");

        Pokemon pokemon = new Pokemon();
        pokemon.setId(123L);
        pokemon.setNome("Pikachu");
        pokemon.setTipo("raio");
        pokemon.setHabilidades(habilidades);

        return pokemon;
    }
}
