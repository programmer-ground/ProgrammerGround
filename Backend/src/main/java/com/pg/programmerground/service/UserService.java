package com.pg.programmerground.service;

import com.pg.programmerground.config.MyUserDetails;
import com.pg.programmerground.entity.Oauth2AuthorizedClient;
import com.pg.programmerground.entity.User;
import com.pg.programmerground.repository.Oauth2AuthorizedClientRepository;
import com.pg.programmerground.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;

    /**
     * OAuthID를 통해 UserDetails 가져옴
     */
    public UserDetails loadUserByOAuthId(Long OAuthId) {
        //OAuthID를 통해 User정보를 가져온다.
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(OAuthId)
                .orElseThrow(() -> new EntityNotFoundException("OAuth 존재하지 않음"));
        User user = userRepository.findByOauth2AuthorizedClient(authorizedClient);
        return new MyUserDetails(user);
    }

    /**
     * UserId를 통해 유저 Entity 가져옴
     */
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}

