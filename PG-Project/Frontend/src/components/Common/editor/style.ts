/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';
import Background from '@src/assets/css_sprites.png';

export const ButtonBoldImage = styled.span`
	display: inline-block;
	width: 35px;
	height: 35px;
	background: url('${Background}') -54px -10px;
	position: relative;
	outline: none;
	margin-left: 30px;
	cursor: pointer;
	background-color: #fff;
	box-sizing: border-box;
	margin-top: 12px;
	&:hover {
		opacity: 0.5;
	}
`;

export const ButtonItalicImage = styled.span`
	display: inline-block;
	width: 35px;
	height: 35px;
	background: url('${Background}') -10px -54px;
	position: relative;
	outline: none;
	margin-left: 30px;
	cursor: pointer;
	background-color: #fff;
	box-sizing: border-box;
	&:hover {
		opacity: 0.5;
	}
`;

export const ButtonTextImage = styled.span`
	display: inline-block;
	background: url('${Background}') -10px -10px;
	position: relative;
	width: 35px;
	height: 35px;
	outline: none;
	margin-left: 30px;
	cursor: pointer;
	background-color: #fff;
	box-sizing: border-box;
	&:hover {
		opacity: 0.5;
	}
`;
