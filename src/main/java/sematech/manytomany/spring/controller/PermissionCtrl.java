package sematech.manytomany.spring.controller;


//import org.apache.logging.log4j.Logger;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sematech.manytomany.spring.model.PermissionEntity;
import sematech.manytomany.spring.services.PermisionSrv;

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

public class PermissionCtrl {
//    private final static  Logger logger= Logger.getLogger(PermissionCtrl.class.getName());

    @Autowired
    PermisionSrv pS;

    @Operation(summary = "Get All Permissions without any special filters ",description = "get All Permissions",operationId = "getAllPermissions")
    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionEntity>> getAllPermissions(){
        try {
            return new ResponseEntity(pS.getAllPermissions(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "add new permission",description = "Add",operationId = "setNewPermission")
    @PostMapping("/permissions")
    public ResponseEntity<List<PermissionEntity>> setNewPermission(@RequestBody PermissionEntity account){
        try {
            return new ResponseEntity(pS.saveNewPermission(account), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "update a defined Permission by id",description = "update",operationId = "updatePermission")
    @PutMapping("/permissions/{id}")
    public ResponseEntity<PermissionEntity> updatePermission(@PathVariable("id") long id, @RequestBody PermissionEntity account){
        try {
            return new ResponseEntity(pS.updateAnPermission(id,account),HttpStatus.OK);
        }catch (Exception  e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "delete a permission with id",description = "delete",operationId = "deletePermission")
    @DeleteMapping("/permissions")
    public ResponseEntity<PermissionEntity> deletePermission(  @RequestBody PermissionEntity account){
        try {
            return new ResponseEntity(pS.deletePermissionById(account),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            logger.warning(() -> e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
