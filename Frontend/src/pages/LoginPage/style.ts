/* eslint-disable import/no-unresolved */
import React from 'react';
import styled, { createGlobalStyle } from 'styled-components';
import logo from '@src/assets/logo.svg';
import githublogo from '@src/assets/github.svg';

export const GlobalStyle = createGlobalStyle`
  body, h1, h2, h3, h4, h5, h6,
	ul, ol, dl, dd, p, fieldset, legend{
		margin:0;
		padding:0;
	}
	html, body{
		height:100%;
    background-color:#e9e9e9;
  }

	#root{
		height:100%;
	}
	body, input, textarea, select, button{
		font-size:14px;
		font-family: Dotum, '돋움', Helvetica, "Apple SD Gothic Neo", sans-serif;
	}

	ul, ol{
		list-style:none;
	}
	
	table{
		border-collapse: collapse;
	}

	em{
		font-style:normal;
	}

	a{
		color:inherit;
		text-decoration:none;
	}

	img{
		vertical-align:top;
	}

	fieldset{
		border:0;
	}
`;

export const LoginAllContainer = styled.div`
	display: flex;
	height: 100%;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

export const LoginContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	width: 40%;
	height: 400px;
	border: 1px solid black;
	background-color: #fff;
`;

export const LoginLogo = styled.img.attrs({
	src: logo,
})`
	width: 300px;
	height: 250px;
	margin: 0 auto;
`;
export const LoginButtonContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: center;
`;
export const LoginLink = styled.a`
	display: inline-block;
	width: 100%;
	height: 30px;
	background-color: #000;
	text-align: center;
	margin: 20px auto;
	color: #fff;
	font-weight: bold;
	font-size: 30px;
	line-height: 30px;
	&:hover {
		cursor: pointer;
	}
`;
export const GithubLogo = styled.img.attrs({
	src: githublogo,
})`
	width: 25px;
	height: 25px;
	margin-left: 10px;
`;

export const DetailComment = styled.p`
	display: inline-block;
	text-align: center;
	font-size: 20px;
	line-height: 25px;
	font-weight: bold;
	color: #000;
`;
