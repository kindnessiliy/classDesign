package com.classdesign.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-21:50
 * @email:1269231889@qq.com
 */
@Data
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 7440738202508941443l;
    @Id
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    @Size(min = 10, max = 16)
    private String password;
    private List<Role> roles;
    @Size(max = 2, message = "长度最长为2")
    private String gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
