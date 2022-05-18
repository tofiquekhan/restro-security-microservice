package com.webkorps.restrosecurityservice.controller;

import com.webkorps.restrosecurityservice.entity.JwtResponse;
import com.webkorps.restrosecurityservice.entity.User;
import com.webkorps.restrosecurityservice.service.RestroUserDetailService;
import com.webkorps.restrosecurityservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestroUserDetailService restroUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

@PostMapping("/public/")
    public ResponseEntity<?> generateToken(@RequestBody User user) throws Exception {
    System.out.println(user.getEmail());
    System.out.println(user.getPassword());
try{
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

}catch (Exception e){
    e.printStackTrace();
    throw new Exception("Bad Credentials");
}
    UserDetails userDetails = restroUserDetailService.loadUserByUsername(user.getEmail());
    String token = jwtUtil.generateToken(userDetails);
    System.out.println(token);

    return ResponseEntity.ok(new JwtResponse(token));
}

@GetMapping("/private")
    public String getMsg(){
    return "JSON Token is Working";
}


}
