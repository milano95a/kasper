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
import source.domain.*;
import source.repository.InvitationRepo;
import source.service.FamilyMemberService;
import source.service.FamilyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/api")
@Api(value = "user", description = "Operations pertaining user operation")
public class UserController {

    @Autowired
    FamilyService familyService;

    @Autowired
    FamilyMemberService familyMemberService;

    @Autowired
    InvitationRepo invitationRepo;

    @ApiOperation(value = "get fixed contacts", response = Contacts.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved contact"),
            @ApiResponse(code = 401,message = "Failed to sign up"),
            @ApiResponse(code = 400,message = "Badly formatted input"),
            @ApiResponse(code = 409,message = "User already exist with the given phone")
    })
    @PostMapping(value = "/contactfix", produces = "application/json")
    public @ResponseBody Object analyseContacts(@RequestBody String input){
        try {
            Contacts contacts = new Gson().fromJson(input, Contacts.class);

            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(contacts);

        } catch (Exception e) {
            Constants.LOGGER.info(e.toString());
            Constants.LOGGER.info("location: " + this);
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result("message", "Error"));
        }
    }

    @ApiOperation(value = "get all family", response = Family.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved contact"),
                @ApiResponse(code = 401, message = "Failed to sign up"),
                @ApiResponse(code = 400, message = "Badly formatted input"),
                @ApiResponse(code = 409, message = "User already exist with the given phone")
        })
        @PostMapping(value = "/groups/{id}", produces = "application/json")
    public @ResponseBody Object getGroup(@PathVariable long id){
        try {

            ArrayList<Family> myFamilies = familyService.findByUserId(id);

            if(myFamilies == null){
                return ResponseEntity.
                        status(HttpStatus.OK)
                        .body(result("message","null"));
            }

            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(myFamilies);

        } catch (Exception e) {
            Constants.LOGGER.info(e.toString());
            Constants.LOGGER.info("location: " + this);
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result("message", "Error"));
        }
    }

    @ApiOperation(value = "invite", response = Invitation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved contact"),
            @ApiResponse(code = 401,message = "Failed to sign up"),
            @ApiResponse(code = 400,message = "Badly formatted input"),
            @ApiResponse(code = 409,message = "User already exist with the given phone")
    })
    @PostMapping(value = "/invite", produces = "application/json")
    public @ResponseBody Object postInvitation(@RequestBody String input){
        try {
            Invitation invitation = new Gson().fromJson(input,Invitation.class);

            invitationRepo.save(invitation);

            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(invitation);

        }catch (Exception e){
            Constants.LOGGER.info(e.toString());
            Constants.LOGGER.info("location: " + this);
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result("message","Error"));
        }
    }

    @ApiOperation(value = "post familymember", response = FamilyMember.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved contact"),
            @ApiResponse(code = 401,message = "Failed to sign up"),
            @ApiResponse(code = 400,message = "Badly formatted input"),
            @ApiResponse(code = 409,message = "User already exist with the given phone")
    })
    @PostMapping(value = "/fm", produces = "application/json")
    public @ResponseBody Object postFamilyMember(@RequestBody String input){
        try {
            FamilyMember familyMember = new Gson().fromJson(input,FamilyMember.class);

            familyMemberService.save(familyMember);

            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(familyMember);

        }catch (Exception e){
            Constants.LOGGER.info(e.toString());
            Constants.LOGGER.info("location: " + this);
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result("message","Error"));
        }
    }

    @ApiOperation(value = "get all family", response = Group.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved contact"),
            @ApiResponse(code = 401,message = "Failed to sign up"),
            @ApiResponse(code = 400,message = "Badly formatted input"),
            @ApiResponse(code = 409,message = "User already exist with the given phone")
    })
    @PostMapping(value = "/addgroup", produces = "application/json")
    public @ResponseBody Object createGroup(@RequestBody String input){
        try {
            Group group = new Gson().fromJson(input,Group.class);

            familyService.save(group.getFamily());

            long familyId = group.getFamily().getId();

            for (User user : group.getUsers()){
                FamilyMember familyMember =
                        new FamilyMember(familyId,user.getPhoneNumber(),false);
                familyMemberService.save(familyMember);
            }
            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(input);

        }catch (Exception e){
            Constants.LOGGER.info(e.toString());
            Constants.LOGGER.info("location: " + UserController.class);
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result("message","Error"));
        }
    }

    private HashMap<String, String> result(String title, String message){
        HashMap<String,String> result = new HashMap<>();
        result.put(title, message);
        return result;
    }

}
