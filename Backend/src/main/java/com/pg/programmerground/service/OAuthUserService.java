package com.pg.programmerground.service;

import com.pg.programmerground.auth.MyUserDetails;
import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.dto.user.response.UserResponse;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.Oauth2AuthorizedClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OAuthUserService {
	private final OAuthUserRepository oAuthUserRepository;
	private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;

	/**
	 * OAuthID를 통해 UserDetails 가져옴
	 */
	public UserDetails loadUserByOAuthId(Long OAuthId) {
		//OAuthID를 통해 User정보를 가져온다.
		Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
			.findById(OAuthId)
			.orElseThrow(() -> new EntityNotFoundException("OAuth 존재하지 않음"));
		OAuthUser oAuthUser = oAuthUserRepository.findByOauth2AuthorizedClient(authorizedClient);
		return new MyUserDetails(oAuthUser);
	}

	public UserResponse getUserInfo(Long userId) {
		return UserResponse.of(oAuthUserRepository.findById(userId).orElseThrow());
	}
}

