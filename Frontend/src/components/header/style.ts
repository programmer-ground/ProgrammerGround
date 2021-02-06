/* eslint-disable import/prefer-default-export */
import styled, { createGlobalStyle } from 'styled-components';
import logo from '@src/assets/programmerground.png';
import projectIcon from '@src/assets/projectIcon.png';

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
`;

export const HeaderContainer = styled.div`
	display: flex;
	justify-content: space-around;
	border-bottom: 1px solid #a2b8e1;
	height: 80px;
`;

export const HeaderMenuContainer = styled.div`
	display: flex;
	justify-content: center;
	width: 200px;
	height: 100px;
	padding-top: 20px;
`;

export const ProjectIcon = styled.img.attrs({
	src: projectIcon,
})`
	width: 40px;
	height: 40px;
	cursor: pointer;
`;
