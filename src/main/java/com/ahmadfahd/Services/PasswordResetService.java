package com.ahmadfahd.Services;

import com.ahmadfahd.dto.PasswordResetDTO;

public interface PasswordResetService {

    void passResetRequest(String username);
    void passReset(PasswordResetDTO dto, String id);
    boolean isActive(String id);

}
