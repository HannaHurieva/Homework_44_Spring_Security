package com.alevel.homework44.security.jwt;

import com.alevel.homework44.model.Role;
import com.alevel.homework44.model.User;
import com.alevel.homework44.repository.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
       /* Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());*/

        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getRoles()
                //mapToGrantedAuthorities(roles)
        );
    }

/*    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<String> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.valueOf(Role.))
                ).collect(Collectors.toSet());
    }*/
}
