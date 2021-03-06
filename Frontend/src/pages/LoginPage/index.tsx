import React, { useLayoutEffect } from 'react';
import queryString from 'query-string';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import * as StyledComponent from './style';

const LoginPage = () => {
	const history = useHistory();
	if (localStorage.getItem('token')) {
		history.push('/playground');
	}
	useLayoutEffect(() => {
		const local = location.search;
		const params = queryString.parse(local);
		const options = {
			mode: 'cors',
			credentials: 'include',
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*',
			},
		};

		if (Object.keys(params).length > 0) {
			const getToken = async () => {
				const v: any = await axios
					.post('http://localhost:8080/jwtLogin', params, options)
					.then((response) => {
						localStorage.setItem('token', response.headers.token);
					});
			};
			getToken();
			history.push('/playground');
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
