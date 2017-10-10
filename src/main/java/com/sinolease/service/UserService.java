package com.sinolease.service;

import com.sinolease.model.po.BiUserPo;
import com.sinolease.model.po.SinoLeaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AlbertLy on 2016/11/17.
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    @Autowired
    private BiUserService biUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BiUserPo userInfo = biUserService.getUserInfoByUserName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (userInfo.getState() != null && userInfo.getState() != 1) {
            throw new UsernameNotFoundException("用户未生效");
        }
        List<BiUserPo> listOfUser=biUserService.selectRoleByUserId(userInfo.getUserId());
        List<GrantedAuthority> authoritys= new ArrayList<GrantedAuthority>();
        for(int i=0;i<listOfUser.size();i++){
            GrantedAuthority authority = new SimpleGrantedAuthority(listOfUser.get(i).getRole());
            authoritys.add(authority);
        }
        UserDetails userDetails = new SinoLeaseUser(userInfo.getUserName(),
                userInfo.getPassword(), authoritys, userInfo.getUserId(), userInfo.getPhone(),
                userInfo.getUserNameZh(), userInfo.getEmail(), userInfo.getDepartId(), userInfo.getTitleId());

        return userDetails;
    }
}

