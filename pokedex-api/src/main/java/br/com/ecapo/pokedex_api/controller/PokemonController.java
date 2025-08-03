package br.com.ecapo.pokedex_api.controller;

import br.com.ecapo.pokedex_api.model.Pokemon;
import br.com.ecapo.pokedex_api.service.PokemonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonServices pokemonServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pokemon> getAll(){
        return pokemonServices.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Pokemon create(
            @RequestBody Pokemon pokemon
    ) {
        return pokemonServices.create(pokemon);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Pokemon findById(
            @PathVariable("id") Long id
    ) {
        return pokemonServices.findById(id);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Pokemon update(
            @RequestBody Pokemon pokemon
    ) {
        return pokemonServices.update(pokemon);
    }

    @DeleteMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> delete(
            @RequestBody Pokemon pokemon
    ) {
        pokemonServices.delete(pokemon);
        return ResponseEntity.noContent().build();
    }
}
