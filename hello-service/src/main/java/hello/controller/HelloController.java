package hello.controller;

import hello.dto.GetHelloMessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Value("${greeting.name}")
    private String name;

    @GetMapping(value = "/hi")
    public ResponseEntity<GetHelloMessageResponse> getHelloMessage() {
        GetHelloMessageResponse response = new GetHelloMessageResponse();

        response.setMessage("Hello " + name + "!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}