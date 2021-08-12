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
	display: flex;
	justify-content: space-around;
	min-width: 1100px;
	height: 100px;
	padding: 0px 8px;
	background-color: #747474;
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
	position: absolute;
	top: 80px;
	left: 0;
	z-index: 200;
	background-color: #fff;
	border-radius: 4px;
	border: 1px solid rgba(0, 0, 0, 0.1);
	box-shadow: 0 4px 6px 0 rgba(0, 7, 120, 0.1);
`;

export const UserItem = styled.div`
	display: flex;
	align-items: center;
	&:not(:first-child) {
		border-top: 1px solid rgba(0, 0, 0, 0.1);
	}
	padding: 16px 10px;
	font-size: 18px;
	font-family: Roboto, sans-serif;
	text-align: center;
	min-width: 120px;
	min-height: 48px;
	cursor: pointer;
	& > a {
		display: block;
		color: #000;
		text-decoration: none;
	}

	&:hover {
		background-color: rgba(0, 0, 0, 0.1);
	}
`;

export const UserProfileLink = styled.a``;
