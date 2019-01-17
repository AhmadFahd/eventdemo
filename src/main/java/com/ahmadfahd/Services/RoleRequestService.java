package com.ahmadfahd.Services;

import com.ahmadfahd.dto.RoleRequestDTO;

import java.util.List;

public interface RoleRequestService {

    List<RoleRequestDTO> getAllRequests();
    List<RoleRequestDTO> getRoleRequests(String role);

    void addRequest(Long uid, String role);
    void acceptRoleRequest(Long rid);
    }
