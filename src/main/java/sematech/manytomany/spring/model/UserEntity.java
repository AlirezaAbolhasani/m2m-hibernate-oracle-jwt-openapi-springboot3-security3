package sematech.manytomany.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @auteur ALireza Abolhasani
 * @date: 8/27/2024
 * @time: 4:10 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

@Table(name = "SPR_USER")
@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component(value = "user")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long user_id;

    @Column(name = "NAME")
    private  String name;

    @Column(name = "FAMILY")
    private String family;

    @Column(name = "USERNAME")
    private  String username="";

    @Column(name = "PASSWORD")
    private String password="";

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "SPR_USER_PERMISSION",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id") })
    private List<PermissionEntity> up = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "SPR_USER_ROLE",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<RoleEntity> ur = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;//UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;//UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;//UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return  true;//UserDetails.super.isEnabled();
    }



}
