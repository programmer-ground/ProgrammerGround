import React, { useState, useEffect } from 'react';
import queryString from 'query-string';
import axios, { AxiosResponse } from 'axios';
import { useHistory } from 'react-router-dom';
import { getOptions } from '@src/lib/api';
import { url } from '@src/lib/axios/playground';
import useCookie from '@src/hooks/useCookie';
import LoadingSpinner from '@src/components/loading';
import * as StyledComponent from './style';

const LoginPage = () => {
	const history = useHistory();
	const [loading, setLoading] = useState(null);

	useEffect(() => {
		const local = location.search;
		const params = queryString.parse(local);
		const accessToken = useCookie('access_token');

		if (accessToken[0] !== '') {
			history.push('/');
		}

		if (Object.keys(params).length > 0) {
			const getToken = async () => {
				try {
					setLoading(true);
					await axios.post(`${url.GET_JWT_TOKEN}`, params, getOptions);
					setLoading(false);
					history.push('/');
				} catch (e) {
					console.error(e);
				}
			};
			getToken();
		}
	}, []);
	return (
		<>
			{loading ? <LoadingSpinner /> : ''}

			<StyledComponent.LoginAllContainer>
				<StyledComponent.LoginContainer>
					<StyledComponent.LoginLogo />
					<StyledComponent.LoginButtonContainer>
						<StyledComponent.LoginLink href={url.GET_OAUTH_TOKEN}>
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
