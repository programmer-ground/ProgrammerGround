/* eslint-disable import/prefer-default-export */
import styled, { createGlobalStyle } from 'styled-components';
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

export const HeaderContainer = styled.div`
	display: flex;
	justify-content: space-around;
	border-bottom: 1px solid #a2b8e1;
	height: 80px;
	padding: 0px 8px;
`;

export const HeaderMenuContainer = styled.div`
	display: flex;
	justify-content: center;
	width: 200px;
	height: 50px;
	padding-top: 20px;
	& img {
		padding-left: 5px;
		margin-left: 10px;
		width: 30px;
		height: 30px;
		margin-top: 5px;
		cursor: pointer;
		border: 1px solid #e9e9e9;
		border-radius: 50px;
		padding-right: 5px;
	}
	& img:hover {
		background-color: #06c471;
	}
	@media screen and (max-width: 330px) {
		display: none;
	}
`;

export const ProjectIcon = styled.img.attrs({
	src: projectIcon,
})``;

export const AlarmIcon = styled.img.attrs({
	src: alarm,
})``;

export const ProfileIcon = styled.img.attrs({
	src: user,
})``;
