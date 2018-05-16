package source.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import source.constant.Constants;
import source.service.TokenService;

import java.util.HashMap;


@RestController()
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    TokenService tokenService;

    @GetMapping(path = {"/unauthorized"})
    public @ResponseBody
    Object unauthorized(){
        return ResponseEntity.
                status(HttpStatus.UNAUTHORIZED).
                body(result("message","unauthorized"));
    }

    @GetMapping(path = {"/all"})
    public @ResponseBody Object all(){
        return ResponseEntity.
                status(HttpStatus.UNAUTHORIZED).
                body(result("message","unauthorized"));
    }

    private HashMap<String, String> result(String title, String message){
        HashMap<String,String> result = new HashMap<>();
        result.put(title, message);
        return result;
    }

}
