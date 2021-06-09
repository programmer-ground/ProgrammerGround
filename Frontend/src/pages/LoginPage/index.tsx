import React, { useLayoutEffect } from 'react';
import queryString from 'query-string';
import axios, { AxiosResponse } from 'axios';
import { useHistory } from 'react-router-dom';
import { getOptions } from '@src/lib/api';
import useCookie from '@src/hooks/useCookie';
import * as StyledComponent from './style';

const LoginPage = () => {
	const history = useHistory();

	useLayoutEffect(() => {
		const local = location.search;
		const params = queryString.parse(local);
		const accessToken = useCookie('access_token');
		if (accessToken[0] !== '') {
			history.push('/');
		}
		if (Object.keys(params).length > 0) {
			const getToken = async () => {
				await axios
					.post('http://localhost:8080/jwtLogin', params, getOptions)
					.then((response: AxiosResponse) => {
						history.push('/');
					});
			};
			getToken();
		}
	}, []);
	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.LoginAllContainer>
				<StyledComponent.LoginContainer>
					<StyledComponent.LoginLogo />
					<StyledComponent.LoginButtonContainer>
						<StyledComponent.LoginLink href="http://localhost:8080/oauth2/authorization/github">
							Sign In With GitHub
							<StyledComponent.GithubLogo />
						</StyledComponent.LoginLink>
					</StyledComponent.LoginButtonContainer>
					<StyledComponent.DetailComment>
						사용하는 GitHub 계정으로 로그인해주세요
					</StyledComponent.DetailComment>
				</StyledComponent.LoginContainer>
			</StyledComponent.LoginAllContainer>
		</>
	);
};

export default LoginPage;
