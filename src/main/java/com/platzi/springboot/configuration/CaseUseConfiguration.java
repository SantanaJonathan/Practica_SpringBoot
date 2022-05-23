package com.platzi.springboot.configuration;

import com.platzi.springboot.caseuse.GetUser;
import com.platzi.springboot.caseuse.GetUserImplement;
import com.platzi.springboot.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser (UserService userService){
        return new GetUserImplement(userService);
    }

}
