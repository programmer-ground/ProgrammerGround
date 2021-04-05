/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const CreateContainer = styled.div`
	display: flex;
	box-sizing: border-box;
	flex-direction: column;
	width: 50%;
	height: 500px;
	border: 1px solid #e9e9e9;
	border-radius: 10px;
	justify-content: center;
	margin: 90px auto;
`;

export const CreateSubTitle = styled.h1`
	font-size: 30px;
	text-align: center;
`;

export const CreateNameDiv = styled.div`
	margin: 0 20%;
	width: 700px;
	& > label {
		font-size: 20px;
	}
	& input {
		margin-left: 10px;
		font-size: 20px;
		width: 50%;
		border: 2px solid #e9e9e9;
	}
	& input:focus {
		outline: none;
	}
`;
