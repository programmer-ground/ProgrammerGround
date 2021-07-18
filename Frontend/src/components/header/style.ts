/* eslint-disable import/prefer-default-export */
import styled, { createGlobalStyle } from 'styled-components';
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
	position: fixed;
	right: 0;
	left: 0;
	top: 0;
	display: flex;
	justify-content: space-around;
	height: 100px;
	padding: 0px 8px;
	background-color: #fff;
	z-index: 100;
`;

export const HeaderMenuContainer = styled.div`
	display: flex;
	justify-content: center;
	width: 200px;
	height: 50px;
	padding-top: 20px;

	& > div {
		border: 1px solid #e9e9e9;
		border-radius: 50%;
		cursor: pointer;
		&:not(:first-child) {
			margin-left: 10px;
		}
	}

	@media screen and (max-width: 330px) {
		display: none;
	}
`;

export const ProjectIcon = styled.div`
	@include sprite($projectIcon);
`;

export const AlarmIcon = styled.img.attrs({
	src: alarm,
})``;

export const ProfileIcon = styled.img.attrs({
	src: user,
})``;

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
