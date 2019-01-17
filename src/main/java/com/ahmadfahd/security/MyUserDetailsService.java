package com.ahmadfahd.security;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServices userServices;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UsersDTO user = userServices.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        rolesRepository.findByUserUsername(s)
                .forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        UserDetails userDetails = new org.springframework.security.core.userdetails.
        User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
}
