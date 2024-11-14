package com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.restImpl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.rest.UserRest;
import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.service.UserService;
import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.utils.CafeUtils;

@RestController
public class UserRestImpl  implements  UserRest{

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch(Exception e){
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity("",HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
