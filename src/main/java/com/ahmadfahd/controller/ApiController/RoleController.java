package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.ServicesImplementation.RoleServicesImp;
import com.ahmadfahd.entity.RolesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleServices roleServices;

    @Autowired
    public RoleController(RoleServicesImp roleServices){
        super();
        this.roleServices = roleServices; }


    @GetMapping("/AdminAccess/view")
    public List<RolesEntity> getAllRoles(){ return roleServices.getAllRoles(); }


    @GetMapping("/view/{roleid}")
    public Optional<RolesEntity> findById(@PathVariable Long roleid){
        return roleServices.findById(roleid);
    }

    @PostMapping("/add")
    public void addRole (@RequestBody RolesEntity rolesEntity){ roleServices.addRole(rolesEntity); }



    @PutMapping("/edit/{roleid}")
    public void updateRole(@RequestBody RolesEntity rolesEntity, @PathVariable String roleid) {
        roleServices.updateRole(rolesEntity, roleid); }

        // No need for HardDelete
//    @RequestMapping(method = RequestMethod.DELETE,value = "/roles/{roleid}")
//    public void deleteRole(@PathVariable Long roleid){ roleServices.deleteById(roleid); }


}