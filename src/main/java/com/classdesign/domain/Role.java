package com.classdesign.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author:zyh
 * @Time:2021-05-20-21:55
 * @email:1269231889@qq.com
 */
@Data
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 8253847826167375L;
    @Id
    private Integer id;
    @NotNull
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
