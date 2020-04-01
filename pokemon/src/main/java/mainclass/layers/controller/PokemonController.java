package mainclass.layers.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("spring-cloud-eureka-client")
public interface PokemonController {

    @RequestMapping(value = "/type/{type}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    String getPokemonByType(@PathVariable("type") Integer type);

    @RequestMapping(value = "/generation/{generation}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    String getPokemonByGeneration(@PathVariable("generation") Integer generation);
}