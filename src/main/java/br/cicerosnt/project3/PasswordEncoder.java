package br.cicerosnt.project3;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static String encode(String[] args){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("0ias"));

        return passwordEncoder.encode(args[0]);
    }

}


