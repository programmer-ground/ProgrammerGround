package com.pg.programmerground.service;

import com.pg.programmerground.auth.MyUserDetails;
import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.dto.user.api_req.ReviseUserApi;
import com.pg.programmerground.dto.user.response.UserResponse;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.Oauth2AuthorizedClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

	/**
	 * 유저 정보
	 */
	public UserResponse getUserInfo(Long userId) {
		return UserResponse.of(oAuthUserRepository.findById(userId).orElseThrow());
	}

	/**
	 * 유저 정보 수정
	 */
	@Transactional
	public Boolean updateUserInfo(Long oauthUserId, ReviseUserApi userApi) {
		OAuthUser user = oAuthUserRepository.findById(oauthUserId).orElseThrow((() -> new NoSuchElementException("잘못된 유저 요청")));
		user.updateUser(userApi);
		return true;
	}
}

