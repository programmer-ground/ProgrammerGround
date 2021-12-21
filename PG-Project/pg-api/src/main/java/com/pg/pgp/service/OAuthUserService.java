package com.pg.pgp.service;

import com.pg.pgp.auth.MyUserDetails;
import com.pg.pgp.domain.OAuthUser;
import com.pg.pgp.domain.github.Oauth2AuthorizedClient;
import com.pg.pgp.dto.user.api_req.ReviseUserApi;
import com.pg.pgp.dto.user.response.UserResponse;
import com.pg.pgp.model.OAuthUserRepository;
import com.pg.pgp.model.Oauth2AuthorizedClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public
class OAuthUserService {
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
		return UserResponse.of(oAuthUserRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.")));
	}

	/**
	 * 유저 정보 수정
	 */
	@Transactional
	public Boolean updateUserInfo(Long oauthUserId, ReviseUserApi userApi) {
		oAuthUserRepository.findById(oauthUserId)
				.orElseThrow((() -> new NoSuchElementException("잘못된 유저 요청")))
				.updateUser(userApi);
		return true;
	}
}

