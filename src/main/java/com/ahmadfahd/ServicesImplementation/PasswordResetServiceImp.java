package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.PasswordResetService;
import com.ahmadfahd.dto.PasswordResetDTO;
import com.ahmadfahd.entity.PasswordResetEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.PasswordResetRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetServiceImp implements PasswordResetService {
    @Autowired
    private PasswordResetRepository passwordResetRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private NotificationService notificationService;


    @Override
    public void passResetRequest(String username,String origin){

// TODO: 1/10/2019  check if (1 day or less) request exist
        PasswordResetEntity passwordResetEntity = new PasswordResetEntity();
        if(username.contains("@")) {
            if(usersRepository.existsByEmail(username)) {
                UsersEntity usersEntity = usersRepository.findByEmail(username);
                passwordResetEntity.setUser(usersEntity);
                passwordResetEntity.setTime(LocalDateTime.now());
                passwordResetRepository.save(passwordResetEntity);
                notificationService.sendRequestEmail(usersEntity, passwordResetEntity.getId(), origin);
            }
        } else {
            if (usersRepository.existsByUsername(username)) {
                UsersEntity usersEntity = usersRepository.findByUsername(username);
                passwordResetEntity.setUser(usersEntity);
                passwordResetEntity.setTime(LocalDateTime.now());
                passwordResetRepository.save(passwordResetEntity);
                notificationService.sendRequestEmail(usersEntity, passwordResetEntity.getId(), origin);
            }
        }
    }


    @Override
    public void passReset(PasswordResetDTO dto, String id) {
        PasswordResetEntity passwordResetEntity = passwordResetRepository.findById(id).get();
        UsersEntity usersEntity = usersRepository.findById(passwordResetEntity.getUser().getId()).get();
        usersEntity.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        usersRepository.save(usersEntity);
        passwordResetEntity.setDone(true);
        passwordResetRepository.save(passwordResetEntity);
    }

    @Override
    public boolean isActive(String id) {
        return passwordResetRepository.existsByIdAndAndTimeAfterAndDoneFalse(id,LocalDateTime.now().minusHours(1));
    }
}
