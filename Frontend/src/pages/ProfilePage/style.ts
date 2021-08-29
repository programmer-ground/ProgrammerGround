import styled from 'styled-components';

// eslint-disable-next-line import/prefer-default-export
export const ProfileContainer = styled.div`
	max-width: 960px;
	margin: 0 auto;
`;

export const ProfileHeader = styled.div`
	&:not(:first-child) {
		margin-top: 150px;
	}
`;

export const ProfileTitle = styled.h2`
	font-size: 20px;
	font-family: 나눔고딕, 'Nanum Gothic', 'Malgun Gothic', 맑은고딕,
		'Apple SD Gothic Neo', Roboto, -apple-system, NanumGothic, Dotum, 돋움,
		sans-serif, system-ui, BlinkMacSystemFont, 'Segoe UI', 'Helvetica Neue',
		Arial, sans-serif;
`;

export const ProfileInfo = styled.div`
	display: flex;
	&:not(:first-child) {
		margin-top: 20px;
	}
`;

export const ProfileLeftInfo = styled.div`
	display: flex;
	flex-direction: column;
`;

export const ProfileLeftInfoData = styled.div`
	background-color: #fff;
	text-align: center;
	padding: 10px 20px;
	color: #333;
	border: 1px solid #e6ecf5;
	& > img {
		border-radius: 50%;
		margin-top: 30px;
	}

	& > h3 {
		width: 240px;
		margin-top: 20px;
	}
`;

export const ProfileGeneralInfo = styled.div`
	&:not(:first-child) {
		margin-left: 20px;
	}
	display: flex;
	flex: 1 1 auto;
	flex-direction: column;
	border: 1px solid #e6ecf5;
	background-color: #fff;
`;

export const ProfileGeneralTitle = styled.h3`
	padding: 15px 20px;
`;

export const ProfileGeneralAttribute = styled.div`
	&:not(:first-child) {
		border-top: 1px solid #e6ecf5;
	}
	display: flex;
	padding: 30px 20px;
`;

export const ProfileGeneralName = styled.span`
	font-weight: bold;
	flex: 1 1 25%;
`;

export const ProfileGeneralValue = styled.span`
	flex: 1 1 75%;
`;
