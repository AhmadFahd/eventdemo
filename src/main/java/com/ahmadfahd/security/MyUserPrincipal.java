//package com.ahmadfahd.security;
//
//import com.ahmadfahd.Services.RoleServices;
//import com.ahmadfahd.Services.UserServices;
//import com.ahmadfahd.dto.RolesDTO;
//import com.ahmadfahd.dto.UsersDTO;
//import com.ahmadfahd.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class MyUserPrincipal implements UserDetails {
//
//    @Autowired
//    private RoleServices rolesRepository;
//    @Autowired
//    private UserServices userServices;
//    @Autowired
//    private UsersRepository usersRepository;
//
//    private static final long serialVersionUID = 1L;
//
//
//    public UsersDTO user;
//
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities ;
//        authorities = new ArrayList<>();
//        for (RolesDTO role : rolesRepository.findByUser(userServices.findByUsername(user.getUsername()))) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return  true;
//    }
//
//    @Override
//    public boolean isEnabled() { return true; }
//
//}
