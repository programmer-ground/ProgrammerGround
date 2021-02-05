/* eslint-disable import/prefer-default-export */
import styled, { createGlobalStyle } from 'styled-components';
import logo from '@src/assets/programmerground.png';

export const GlobalStyle = createGlobalStyle`
  body{
    margin:0;
    padding:0;
  }
`;

export const HeaderImg = styled.img.attrs({
	src: logo,
})`
	width: 400px;
	height: 100px;
	cursor: pointer;
`;

export const HeaderContainer = styled.div`
	display: flex;
	justify-content: space-between;
	padding-left: 50px;
	border-bottom: 1px solid #a2b8e1;
`;
