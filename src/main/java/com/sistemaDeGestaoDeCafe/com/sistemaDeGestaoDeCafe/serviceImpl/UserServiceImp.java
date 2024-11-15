package com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.POJO.User;
import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.constents.CafeConstants;
import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.dao.UserDao;
import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.service.UserService;
import com.sistemaDeGestaoDeCafe.com.sistemaDeGestaoDeCafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                // Verifica se o email já existe
                User user = (User) userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    // Salva o novo usuário
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully registered", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Método para validar os campos necessários
    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
               requestMap.containsKey("contactNumber") &&
               requestMap.containsKey("email") &&
               requestMap.containsKey("password");
    }

    // Converte o requestMap para um objeto User
    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");  // Utilizando boolean para status
        user.setRole("user");
        return user;
    }
}
