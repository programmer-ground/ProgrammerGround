/* eslint-disable import/prefer-default-export */
import styled, { createGlobalStyle, keyframes } from 'styled-components';
import { lightTheme, darkTheme } from '@src/utils/theme';
import logo from '../../assets/programmerground.png';
import projectIcon from '../../assets/projectIcon.png';
import alarm from '../../assets/alarm.png';
import user from '../../assets/user.png';

export const GlobalStyle = createGlobalStyle`
  body{
    margin:0;
    padding:0;
	}
`;

export const HeaderImg = styled.img.attrs({
	src: logo,
})`
	width: 200px;
	height: 50px;
	padding-top: 20px;
	cursor: pointer;
	@media screen and (max-width: 330px) {
		position: absolute;
		margin-left: -60px;
	}
`;

export const HeaderContainer = styled.header`
	display: flex;
	justify-content: space-around;
	min-width: 1100px;
	height: 100px;
	padding: 0px 8px;
	background-color: #333;
	z-index: 100;
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
`;

export const HeaderMenuContainer = styled.div`
	display: flex;
	position: relative;
	justify-content: center;
	height: 50px;
	padding-top: 20px;

	@media screen and (max-width: 330px) {
		display: none;
	}
`;

export const CreateLink = styled.a.attrs((props) => ({
	href: 'http://localhost:3000/playground',
}))`
	box-sizing: border-box;
	display: inline-block;
	font-size: 12px;
	padding: 9px 11px;
	margin-top: 20px;
	height: 32px;
	text-decoration: none;
	background-color: #04c584;
	color: #fff;
`;

export const PlaygroundSearchSection = styled.div`
	margin-top: 15px;
`;

export const UserMenu = styled.div`
	display: flex;
	flex-direction: column;
	position: absolute;
	top: 80px;
	left: 0;
	z-index: 200;
	background-color: #fff;
	border-radius: 4px;
	border: 1px solid rgba(0, 0, 0, 0.1);
	box-shadow: 0 4px 6px 0 rgba(0, 7, 120, 0.1);
	& > a {
		&:not(:first-child) {
			border-top: 1px solid rgba(0, 0, 0, 0.1);
		}
		padding: 16px 10px;
		font-size: 18px;
		font-family: Roboto, sans-serif;
		min-width: 120px;
		min-height: 28px;
		cursor: pointer;
		color: #000;
		text-decoration: none;
	}

	& > button {
		&:not(:first-child) {
			border-top: 1px solid rgba(0, 0, 0, 0.1);
		}
		padding: 16px 10px;
		font-size: 18px;
		font-family: Roboto, sans-serif;
		min-width: 120px;
		min-height: 28px;
		cursor: pointer;
		color: #000;
		background-color: #fff;
		text-decoration: none;
		border: 0;
	}
`;

const infoKeyframes = keyframes`
	0% {
		transform: translateX(390px);
	}

	100% {
		transform: translateX(0);
	}
`;

export const InfoMenu = styled.div`
	z-index: 1000;
	background-color: #f6f8fa;
	border: 1px solid #e9e9e9;
	position: absolute;
	right: 0;
	width: 360px;
	top: 80px;
	animation: ${infoKeyframes} 1s forwards;
`;

export const InfoMenuList = styled.div`
	padding: 8px 15px;
`;

export const InfoMenuLink = styled.a`
   display: inline-block;
	 padding: 5px 15px;
	 cursor: pointer;
`;

export const InfoTitleContainer = styled.div`
	display: flex;
	padding: 8px 16px;
`;

export const InfoTitleCloseButton = styled.button`
	padding: 5px;
	background-color: #fff;
	border: 1px solid #e9e9e9;
`;

export const InfoTitleName = styled.span`
	flex: 1 1 auto;
	&:not(:last-child) {
		margin-top: 4px;
	}
`;
export const UserProfileLink = styled.a``;

export const InfoBodyContainer = styled.div`
	  &:not(:first-child) {
			border-top: 1px solid #e9e9e9;
		}
`; 

export const InfoBodyContent = styled.div`
		padding: 4px 16px;
		&:not(:first-child) {
			border-top: 1px solid #e9e9e9;
		}
`;

export const InfoBodyTitle = styled.strong`
		font-weight: normal;

`;

export const InfoBodyAuthor = styled.div`
		&:not(:first-child) {
			margin-top: 4px;
		}
`;

export const InfoAuthorName = styled.span`
		font-size: 5px;
		&:not(:last-child) {
			margin-right: 5px;
		}
`;

export const InfoNameEmphasis = styled.em`
		font-weight: normal;
		color: #0abe16;
`;


export const InfoAuthorPosition = styled.span`
		&::before {
			content: '';
			margin: 0 5px 2px 0;
			display: inline-block;
			width: 2px;
			height: 2px;
			background-color: #000;
		}
		font-size: 5px;
`;

export const InfoAuthorContainer = styled.div`
		display: flex;
`;

export const InfoContainerItem = styled.div`
		flex: 1 1 auto;
		&:not(:last-child) {
			margin-right: 10px;
		}
		&:not(:first-child) {
			line-height: 40px;
		}
`;

export const InfoAcceptButton = styled.button`
		border: 1px solid #e9e9e9;
		background-color: #fff;
		border-radius: 10px;
		cursor: pointer;

		&:hover {
			color: #fff;
			background-color: #00bcd4;
		}
`;

export const InfoRejectButton = styled.button`
	  &:not(:first-child) {
			margin-left: 5px;
		}
		border: 1px solid #e9e9e9;
		background-color: #fff;
		border-radius: 10px;
		cursor: pointer;
		&:hover {
			color: #fff;
			background-color: #00bcd4;
		}
`;