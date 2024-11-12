package ru.netology.DiplomCloudStorage.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.DiplomCloudStorage.exception.UnauthorizedException;
import ru.netology.DiplomCloudStorage.model.entity.User;
import ru.netology.DiplomCloudStorage.repository.LoginRepository;

@Slf4j
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = loginRepository.findByUsername(userName);
        if (user == null) {
            log.error("User Service: Unauthorized");
            throw new UnauthorizedException("User Service: Unauthorized");
        }
        return user;
    }
}
