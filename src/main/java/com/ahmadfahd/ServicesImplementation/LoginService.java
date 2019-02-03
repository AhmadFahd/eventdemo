package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.LoginBody;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserServices userServices;

    public Map loginWithUser(LoginBody loginBody) {
        if (!userServices.isActiveUser(loginBody.getUsername())) {
            if (userServices.isUser(loginBody.getUsername())) {
                throw new RuntimeException("User is disabled");
            }
            throw new RuntimeException("User not found");
        }
        UsersDTO usersDTO = userServices.findByUsername(loginBody.getUsername());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginBody.getUsername());
        Map map = getMap(loginBody, usersDTO, userDetails);
        if (map != null){
            return map;
        }
        throw new RuntimeException("Password Incorrect!");
    }

    private Map getMap(LoginBody loginBody, UsersDTO usersDTO, UserDetails userDetails) {
        if (new BCryptPasswordEncoder().matches(loginBody.getPassword(), usersDTO.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("userAuth", userDetails.getAuthorities());
            map.put("userId", usersDTO.getId());
            return map;
        }
        return null;
    }
}
