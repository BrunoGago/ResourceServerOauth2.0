package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import com.appsdeveloperblog.ws.api.ResourceServer.model.UserResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status(){
        return "working...";
    }

    //@PreAuthorize("hasRole('developer')")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")//you can use both annotations
    //@Secured("ROLE_developer")//Just users with the role provided can access this method (Note: The "ROLE_" must be used in this annotation)
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "Deleted user with id" + id + "and JWT Subject" + jwt.getSubject();//The subject is mostly equals to the ID
    }

    @PostAuthorize("returnObject.id == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserResponse getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserResponse("Bruno", "Gago", "");
    }

}
