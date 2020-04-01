package mainclass.layers.service.impl;

import mainclass.exception.GenerationNotFoundException;
import mainclass.exception.TypeNotFoundException;
import mainclass.layers.service.PokemonService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Override
    public String getPokemonByType(Integer type) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            HttpEntity<String> result = restTemplate.exchange("https://pokeapi.co/api/v2/type/" + type + "/", HttpMethod.GET, entity, String.class);
            return result.getBody();

        } catch (Exception ex) {
            throw new TypeNotFoundException();
        }

    }

    @Override
    public String getPokemonByGeneration(Integer generation) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            HttpEntity<String> result = restTemplate.exchange("https://pokeapi.co/api/v2/type/" + generation + "/", HttpMethod.GET, entity, String.class);
            return result.getBody();

        } catch (Exception ex) {
            throw new GenerationNotFoundException();
        }
    }

}
