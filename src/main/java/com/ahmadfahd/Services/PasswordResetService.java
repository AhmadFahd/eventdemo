package com.ahmadfahd.Services;

import com.ahmadfahd.dto.PasswordResetDTO;

public interface PasswordResetService {

    void passResetRequest(String username,String origin);
    void passReset(PasswordResetDTO dto, String id);
    boolean isActive(String id);

}
