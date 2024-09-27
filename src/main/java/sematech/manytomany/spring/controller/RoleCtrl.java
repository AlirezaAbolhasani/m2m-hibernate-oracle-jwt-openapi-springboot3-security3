package sematech.manytomany.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sematech.manytomany.spring.model.RoleEntity;
import sematech.manytomany.spring.services.RoleSrv;

import java.util.List;
import java.util.logging.Logger;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:13 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@RestController
@RequestMapping(value = "/api")
public class RoleCtrl {
//    private static final Logger logger = Logger.getLogger(RoleCtrl.class.getName());
    @Autowired
    RoleSrv pS;

    @Operation(summary = "Get all roles without any special filter",description = "Get",operationId = "getAllRoles")
    @GetMapping("/roles")
    public ResponseEntity<List<RoleEntity>> getAllRoles(){
        try {
            return new ResponseEntity(pS.getAllRoles(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "add new Role",description = "Add",operationId = "setNewRole")
    @PostMapping("/roles")
    public ResponseEntity<List<RoleEntity>> setNewRole(@RequestBody RoleEntity roleEntity){
        try {
            return new ResponseEntity(pS.saveNewRole(roleEntity), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "update a defined role by id",description = "update",operationId = "updateRole")
    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable("id") long id, @RequestBody RoleEntity ac){
        try {
            return new ResponseEntity(pS.updateAnRole(id,ac),HttpStatus.OK);
        }catch (Exception  e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "delete a permission with id",description = "delete",operationId = "deletePermission")
    @DeleteMapping("/roles")
    public ResponseEntity<RoleEntity> deleteRole(@RequestBody RoleEntity ac){
        try {
            return new ResponseEntity(pS.deleteRoleById(ac),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
