package br.com.ecapo.pokedex_api.unitTests.service;

import br.com.ecapo.pokedex_api.model.Pokemon;
import br.com.ecapo.pokedex_api.repository.PokemonRepository;
import br.com.ecapo.pokedex_api.service.PokemonServices;
import br.com.ecapo.pokedex_api.unitTests.mocks.MockPokemon;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PokemonServiceTest {

    @Mock
    private Logger logger;

    @Mock
    PokemonRepository pokemonRepository;

    @InjectMocks
    PokemonServices pokemonServices;

    MockPokemon mockPokemon;

    @BeforeEach
    public void setup(){
        mockPokemon = new MockPokemon();
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("findAll")
    class FindAll {
        @Test
        @DisplayName("Should return all pokemons")
        public void shouldCreatePokemon(){
            Mockito.when(pokemonRepository.findAll()).thenReturn(new ArrayList<Pokemon>());

            List<Pokemon> pokemons = pokemonServices.findAll();

            Assertions.assertEquals(pokemons, new ArrayList<Pokemon>());
            Mockito.verify(logger).info("Pokemons encontrados com sucesso");
        }
    }

    @Nested
    @DisplayName("create")
    class Create {
        @Test
        @DisplayName("Should create pokemon")
        public void shouldCreatePokemon(){
            Mockito.when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(mockPokemon.mockEntity());

            Pokemon pokemon = pokemonServices.create(mockPokemon.mockEntity());

            Assertions.assertEquals(mockPokemon.mockEntity().getId(), pokemon.getId());
            Assertions.assertEquals(mockPokemon.mockEntity().getNome(), pokemon.getNome());
            Assertions.assertEquals(mockPokemon.mockEntity().getTipo(), pokemon.getTipo());
            Assertions.assertEquals(mockPokemon.mockEntity().getHabilidades(), pokemon.getHabilidades());

            Mockito.verify(logger).info("Pokemon criado com sucesso");
        }
    }

//    @Nested
//    @DisplayName("update")
//    class Update {
//        @Test
//        @DisplayName("Should update a pokemon")
//        public void shouldCreatePokemon(){
//            Pokemon oldPokemon = mockPokemon.mockEntity();
//            Pokemon newPokemon = new Pokemon();
//
//            newPokemon.setId(1L);
//            newPokemon.setNome("Bulbasaur");
//            newPokemon.setTipo("Planta");
//            newPokemon.setHabilidades(new ArrayList<>());
//
//            Pokemon pokemon = pokemonServices.update(newPokemon);
//
//            Assertions.assertEquals(newPokemon.getId(), pokemon.getId());
//            Assertions.assertEquals(newPokemon.getNome(), pokemon.getNome());
//            Assertions.assertEquals(newPokemon.getTipo(), pokemon.getTipo());
//            Assertions.assertEquals(newPokemon.getHabilidades(), pokemon.getHabilidades());
//
//            Mockito.verify(logger).info("Pokemon atualizado com sucesso");
//        }
//    }

    @Nested
    @DisplayName("delete")
    class Delete {
        @Test
        @DisplayName("Should delete a pokemon")
        public void shouldCreatePokemon(){
            pokemonServices.delete(mockPokemon.mockEntity());

            Mockito.verify(logger).info("Pokemon deletado com sucesso");
        }
    }


}
