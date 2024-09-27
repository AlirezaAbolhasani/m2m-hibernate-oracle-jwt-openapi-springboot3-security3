package sematech.manytomany.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sematech.manytomany.spring.model.PermissionEntity;
import sematech.manytomany.spring.model.RoleEntity;
import sematech.manytomany.spring.model.UserEntity;
import sematech.manytomany.spring.repository.RoleRipo;
import sematech.manytomany.spring.repository.UserRipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:11 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@Service
public class RoleSrv {

    @Autowired
    RoleRipo rp;

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public RoleEntity saveNewRole(RoleEntity uEy){
        RoleEntity roleEntity = new RoleEntity();
        try{
            if(uEy != null) {
                roleEntity =rp.save(uEy);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return roleEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public RoleEntity updateAnRole(long id,RoleEntity md) {
        Optional<RoleEntity> roleEntity = Optional.of(new RoleEntity());
        RoleEntity _roleEntity =new RoleEntity();
        if((md != null) && (id > 0)) {
            roleEntity =rp.findById(id);
            if(roleEntity.isPresent()){
                _roleEntity = roleEntity.get();
                _roleEntity.setRole_id(md.getRole_id());
                _roleEntity.setRole_Desc(md.getRole_Desc());
                _roleEntity =rp.save(_roleEntity);
            }
        }
        return _roleEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public Boolean deleteRoleById(RoleEntity md){
        try{
            if (md != null) {
                rp.delete(md);
            }else{
                return false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
    @Transactional(readOnly = true )
    public List<RoleEntity> getAllRoles(){
        List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();
        try{
            roleEntityList= rp.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return roleEntityList;
    }

}
