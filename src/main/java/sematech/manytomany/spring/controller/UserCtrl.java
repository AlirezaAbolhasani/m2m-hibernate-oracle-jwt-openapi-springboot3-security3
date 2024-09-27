package sematech.manytomany.spring.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sematech.manytomany.spring.model.UserEntity;
import sematech.manytomany.spring.services.UserSrv;

import java.util.List;
import java.util.logging.Logger;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:12 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

@RestController
@RequestMapping(value = "/api")
@OpenAPIDefinition
public class UserCtrl {
//    private final static Logger logger = Logger.getLogger(UserCtrl.class.getName());
    @Autowired
    UserSrv pS;

    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @Operation(summary = "Get all user without any special filters ",description = "Get",operationId = "getAllUsers")
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        try {
            return new ResponseEntity(pS.getAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "add new user",description = "Add",operationId = "setNewUser")
    @PostMapping("/users")
    public ResponseEntity<List<UserEntity>> setNewUser(@RequestBody UserEntity ac){
        try {
            return new ResponseEntity(pS.saveNewUser(ac), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "update a defined role by id",description = "update",operationId = "updateRole")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateRole(@PathVariable("id") long id, @RequestBody UserEntity ac){
        try {
            return new ResponseEntity(pS.updateAnUser(id,ac),HttpStatus.OK);
        }catch (Exception  e){
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "delete a role with id",description = "delete",operationId = "deleteRole")
    @DeleteMapping("/users")
    public ResponseEntity<UserEntity> deleteRole(@RequestBody UserEntity ac){
        try {
            return new ResponseEntity(pS.deleteUserById(ac),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
