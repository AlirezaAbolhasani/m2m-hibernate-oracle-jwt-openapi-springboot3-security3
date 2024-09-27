package sematech.manytomany.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sematech.manytomany.spring.model.PermissionEntity;
import sematech.manytomany.spring.model.RoleEntity;
import sematech.manytomany.spring.model.UserEntity;
import sematech.manytomany.spring.repository.PermissionRipo;
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
public class PermisionSrv {

    @Autowired
    PermissionRipo rp;

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public PermissionEntity saveNewPermission(PermissionEntity uEy){
        PermissionEntity permissionEntity = new PermissionEntity();
        try{
            if(uEy != null) {
                permissionEntity =rp.save(uEy);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return permissionEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PermissionEntity updateAnPermission(long id, PermissionEntity md) {
        Optional<PermissionEntity> permissionEntity = Optional.of(new PermissionEntity());
        PermissionEntity _perEntity =new PermissionEntity();
        if((md != null) && (id > 0)) {
            permissionEntity =rp.findById(id);
            if(permissionEntity.isPresent()){
                _perEntity = permissionEntity.get();
                _perEntity.setPermission_id(md.getPermission_id());
                _perEntity.setPermission_desc(md.getPermission_desc());
                _perEntity =rp.save(_perEntity);
            }
        }
        return _perEntity;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean deletePermissionById(PermissionEntity md){
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
    public List<PermissionEntity> getAllPermissions(){
        List<PermissionEntity> permissionEntityList = new ArrayList<PermissionEntity>();
        try{
            permissionEntityList= rp.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return permissionEntityList;
    }

}
