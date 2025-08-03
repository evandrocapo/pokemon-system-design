package br.com.ecapo.pokedex_api.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pokemon")
public class Pokemon{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nome;
        private String tipo;

        @ElementCollection(fetch = FetchType.EAGER)  // Anotação para mapear a coleção
        @CollectionTable(name = "pokemon_habilidades", joinColumns = @JoinColumn(name = "pokemon_id"))
        @Column(name = "habilidade")
        private List<String> habilidades;

        public Pokemon() {
        }

        @Override
        public boolean equals(Object o) {
                if (!(o instanceof Pokemon pokemon)) return false;
            return Objects.equals(getId(), pokemon.getId()) && Objects.equals(getNome(), pokemon.getNome()) && Objects.equals(getTipo(), pokemon.getTipo()) && Objects.equals(getHabilidades(), pokemon.getHabilidades());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getId(), getNome(), getTipo(), getHabilidades());
        }

        @Override
        public String toString() {
                return "Pokemon{}";
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getTipo() {
                return tipo;
        }

        public void setTipo(String tipo) {
                this.tipo = tipo;
        }

        public List<String> getHabilidades() {
                return habilidades;
        }

        public void setHabilidades(List<String> habilidades) {
                this.habilidades = habilidades;
        }
}
