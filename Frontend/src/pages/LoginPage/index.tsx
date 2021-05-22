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
			<StyledComponent.LoginContainer>
				<StyledComponent.LoginLogo />
				<StyledComponent.LoginButtonContainer>
					<StyledComponent.LoginButton>
						<a href="http://localhost:8080/oauth2/authorization/github">
							Sign In With GitHub
							<StyledComponent.GithubLogo />
						</a>
					</StyledComponent.LoginButton>
				</StyledComponent.LoginButtonContainer>
			</StyledComponent.LoginContainer>
		</>
	);
};

export default LoginPage;
