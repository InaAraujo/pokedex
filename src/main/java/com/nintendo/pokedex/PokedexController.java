package com.nintendo.pokedex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/pokemons")
public class PokedexController {
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private int ultimoId = 1;

    @GetMapping
    public ArrayList<Pokemon> listar() { return pokemons;}

    @PostMapping
    public void cadastrar(@RequestBody Pokemon pokemon) {
        pokemon.setId(ultimoId);
      pokemons.add(pokemon);
      ultimoId++;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id){
        Pokemon pokemonEncontrado = null;

        for (Pokemon pokemon : pokemons){
            if(pokemon.getId() == id) {
                pokemonEncontrado = pokemon;
                break;
            }
        }
      if (pokemonEncontrado != null) {
          pokemons.remove(pokemonEncontrado);
          return;
      }

      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
