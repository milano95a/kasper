package source.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.constant.Constants;
import source.domain.Token;
import source.domain.User;
import source.service.TokenService;
import source.service.UserService;

import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

@RestController()
@RequestMapping("/api")
@Api(value = "authentication", description = "Operations pertaining registration and login")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Inject
    private TokenService tokenService;

    @GetMapping(path = {"/", ""})
    public @ResponseBody Object home(){
        return result("message","welcome");
    }

    @ApiOperation(value = "Sign up a user and get token", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully signed up user"),
            @ApiResponse(code = 401,message = "Failed to sign up"),
            @ApiResponse(code = 400,message = "Badly formatted input"),
            @ApiResponse(code = 409,message = "User already exist with the given phone")
    })
    @PostMapping(value = "/signup", produces = "application/json")
    public @ResponseBody Object addNewUser(@RequestBody String input){
        try {
            User user = new Gson().fromJson(input,User.class);

            if(!userService.isExist(user.getPhoneNumber())){
                userService.save(user);

                if(validate(user)){
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(generateToken(user.getPhoneNumber()));
                }else{
                    return ResponseEntity
                            .status(HttpStatus.UNAUTHORIZED)
                            .body(result("message","failed to authorize"));
                }
            }else{
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(result("message","user already exist"));
            }
        }catch (Exception e){
            Constants.LOGGER.info(e.toString());
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result("message","Error"));
        }

    }

    @ApiOperation(value = "Sign in a user and get token", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully authenticated"),
            @ApiResponse(code = 401,message = "Failed to authenticate"),
            @ApiResponse(code = 400,message = "Badly formatted input")
    })
    @PostMapping(value = "/signin",produces = "application/json")
    public @ResponseBody Object logIn(@RequestBody String input) {
        try{
            Gson gson = new Gson();
            User user = gson.fromJson(input,User.class);
            if(validate(user)){
                Token token = tokenService.findById(user.getPhoneNumber() + "");
                if(token == null){
                    token = generateToken(user.getPhoneNumber());
                }
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(token);
            }else{
                return ResponseEntity.
                        status(HttpStatus.UNAUTHORIZED).
                        body(result("message","unauthorized"));
            }
        }catch (Exception e){
            Constants.LOGGER.info(e.toString()+ "error in signin");
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR ).
                    body(result("message","error"));
        }
    }

    private Token generateToken(long userId){
        String random = new BigInteger(130, new SecureRandom()).toString(32);
        Token token = new Token(random,userId);
        tokenService.save(token);
        return token;
    }

    private HashMap<String, String> result(String title, String message){
        HashMap<String,String> result = new HashMap<>();
        result.put(title, message);
        Constants.LOGGER.info("Message: " + message);
        return result;
    }

    private boolean validate(User user){
        ArrayList<User> users = userService.findAll();

        for (User dbUser:users) {
            if(user.getPhoneNumber() == dbUser.getPhoneNumber()){
                    return true;
                }
            }

        return false;
    }
}
