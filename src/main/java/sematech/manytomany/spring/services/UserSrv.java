package sematech.manytomany.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sematech.manytomany.spring.model.RoleEntity;
import sematech.manytomany.spring.model.UserEntity;
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
public class UserSrv {

    @Autowired
    UserRipo rp;

    @Transactional(readOnly = true )
    public List<UserEntity> findByUsername(String username){
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        try{
            userEntityList= (List<UserEntity>) rp.findByUsername(username);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userEntityList;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public UserEntity saveNewUser(UserEntity uEy){
        UserEntity userEntity = new UserEntity();
        try{
            if(uEy != null) {
             userEntity =rp.save(uEy);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public UserEntity updateAnUser(long id,UserEntity md) {
        Optional<UserEntity> userEntityOptional = Optional.of(new UserEntity());
        UserEntity userEntity =new UserEntity();
        if((md != null) && (id > 0)) {
            userEntityOptional =rp.findById(id);
            if(userEntityOptional.isPresent()){
                userEntity = userEntityOptional.get();
                if(md.getUser_id()>0) {
                    userEntity.setUser_id(md.getUser_id());
                }
                if(md.getName()!= "") {
                    userEntity.setName(md.getName());
                }
                if(md.getFamily()!= "") {
                    userEntity.setFamily(md.getFamily());
                }
                int size_md= md.getUp().size();
                while(size_md >0) {
                    size_md -=1;
                    userEntity.getUp().get(size_md).setPermission_desc(md.getUp().get(size_md).getPermission_desc());
                    userEntity.getUr().get(size_md).setRole_Desc(md.getUr().get(size_md).getRole_Desc());
                }
                userEntity =rp.save(userEntity);
            }
        }
        return userEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public Boolean deleteUserById(UserEntity md){
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
    public List<UserEntity> getAllUsers(){
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        try{
            userEntityList= rp.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userEntityList;
    }
}
