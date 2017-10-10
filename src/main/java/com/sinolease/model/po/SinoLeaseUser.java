package com.sinolease.model.po;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by AlbertLy on 2016/11/28.
 */
public class SinoLeaseUser extends User {
    private Integer userId;

    private String phone;

    private String userNameZh;

    private String email;

    private Long departId;

    private Long titleId;

    public SinoLeaseUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SinoLeaseUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public SinoLeaseUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                         Integer userId, String phone, String userNameZh, String email, Long departId, Long titleId) {
        super(username, password, authorities);
        this.userId = userId;
        this.phone = phone;
        this.userNameZh = userNameZh;
        this.email = email;
        this.departId = departId;
        this.titleId = titleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserNameZh() {
        return userNameZh;
    }

    public void setUserNameZh(String userNameZh) {
        this.userNameZh = userNameZh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
