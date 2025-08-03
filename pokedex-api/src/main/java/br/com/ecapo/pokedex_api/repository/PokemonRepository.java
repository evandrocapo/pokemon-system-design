package br.com.ecapo.pokedex_api.repository;

import br.com.ecapo.pokedex_api.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
