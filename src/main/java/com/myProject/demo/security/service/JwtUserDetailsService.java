//package com.myProject.demo.security.service;
//
//import com.myProject.demo.model.UserModel;
//import com.myProject.demo.repository.UserRepository;
//import com.myProject.demo.security.util.CustomUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserModel account = userRepo.findByEmail(email);
//        if (account == null) {
//            throw new UsernameNotFoundException("User not found with username: " + email);
//        }
//
//        String role = "ROLE_" + account.getFkAccountType().getDescription().toUpperCase();
//
//        return new CustomUserDetails(account, role);
//    }
//}
//
