/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const SameMainContainer = styled.div`
	padding: 150px 400px 0;
`;

export const SameContainer = styled.div`
	display: flex;
	justify-content: center;
	&:not(:first-child) {
		margin-top: 30px;
	}
`;

export const SameModifyButton = styled.button.attrs((_props) => ({
	type: 'button',
}))`
	width: 100%;
	background-color: #3b7cf5;
	color: #fff;
	padding: 15px;
	font-size: 20px;
	line-height: 28px;
	cursor: pointer;
`;

export const SameDeleteButton = styled.button.attrs((_props) => ({
	type: 'button',
}))`
	&:not(:first-child) {
		margin-left: 10px;
	}
	width: 100%;
	background-color: #fff;
	padding: 15px;
	font-size: 20px;
	line-height: 28px;
	cursor: pointer;
	color: #333;
`;

export const SameTitle = styled.h2`
	font-size: 30px;
	line-height: 38px;
	margin-top: 150px;
	&:not(:nth-child(2)) {
		margin-top: 30px;
	}
	text-align: center;
`;

export const SameHightlightTitle = styled.span`
	position: relative;
	&::after {
		content: '';
		position: absolute;
		left: 0;
		right: 0;
		height: 8px;
		bottom: 5px;
		background-color: rgba(159, 242, 241, 0.43);
	}
`;

export const SameTitleImage = styled.img.attrs((props) => ({
	src: props.src,
}))`
	display: block;
	margin: 0 auto;
	&:not(:first-child) {
		margin-top: 30px;
	}
	width: 100%;
	height: 100%;
`;

export const SameContent = styled.div`
	margin: 0 auto;
	background-color: #fff;
	&:not(:first-child) {
		margin-top: 50px;
	}
	text-align: center;
	font-size: 20px;
	line-height: 28px;
	font-family: Roboto, sans-serif;
	padding: 10px;
	color: #333;
	border: 1px solid rgba(0, 0, 0, 0.12);
`;

export const SameContentText = styled.div`
	text-align: center;
	display: inline-block;
	padding: 0 20px;
`;
