package com.webkorps.restrosecurityservice.service;

import com.webkorps.restrosecurityservice.entity.RestroUserDetails;
import com.webkorps.restrosecurityservice.entity.User;
import com.webkorps.restrosecurityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestroUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("No user found with Email : "+username);
        }
        return new RestroUserDetails(user);
    }
}
