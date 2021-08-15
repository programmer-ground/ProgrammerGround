/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const SameContainer = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 150px;
`;

export const SameModifyButton = styled.button.attrs((_props) => ({
	type: 'button',
}))`
	background-color: #e7e7e7;
	color: #000;
	padding: 15px;
	font-size: 20px;
	line-height: 28px;
	cursor: pointer;
	border-radius: 5px;
`;

export const SameDeleteButton = styled.button.attrs((_props) => ({
	type: 'button',
}))`
	&:not(:first-child) {
		margin-left: 20px;
	}
	background-color: #008cba;
	padding: 15px;
	font-size: 20px;
	line-height: 28px;
	cursor: pointer;
	border-radius: 5px;
`;
