/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';
import colorType from '@src/utils/color';

export const CreateContainer = styled.div`
	display: flex;
	box-sizing: border-box;
	width: 50%;
	flex-direction: column;
	border: 3px solid ${colorType.gray};
	border-radius: 10px;
	align-items: center;
	margin: 90px auto;
`;

export const CreateSubTitle = styled.h1`
	font-size: 30px;
	line-height: 38px;
	text-align: center;
`;

export const CreateNameDiv = styled.div`
	width: 100%;
	display: flex;
	justify-content: center;
	& > label {
		font-size: 20px;
		line-height: 24px;
	}
	& input {
		margin-left: 10px;
		font-size: 20px;
		line-height: 24px;
		width: 34%;
		border: 3px solid ${colorType.gray};
	}
	& input:focus {
		outline: none;
	}
`;

export const ProjectLabel = styled.label.attrs((props) => {})`
	display: block;
	text-align: center;
	margin-top: 5px;
	font-size: 20px;
	line-height: 24px;
`;

export const CreateContent = styled.div`
	width: 54%;
	margin: 0 auto;
	height: 300px;
	border: 3px solid ${colorType.gray};
	overflow-y: auto;
`;

export const CreateContentContainer = styled.div`
	display: flex;
	width: 100%;
	justify-content: center;
`;

export const CreateTextArea = styled.p`
	background-color: ${colorType.gray};
`;

export const CreateImageButton = styled.button`
	width: 71%;
	height: 30px;
	background-color: #525d60;
	margin: 0 auto;
	border: 1px solid ${colorType.gray};
	color: #fff;
	&:hover {
		cursor: pointer;
		background-color: #50d56d;
	}
`;

export const PersonNumberLength = styled.span`
	display: block;
	text-align: center;
`;

export const CreateLeaderPosition = styled.div`
	display: flex;
	margin-top: 10px;
	justify-content: center;
	& > label {
		font-size: 20px;
		display: inline-block;
	}
`;
export const CreateLabel = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 10px;
	border-bottom: 1px soild ${colorType.gray};
`;

export const PersonNumber = styled.div`
	font-size: 20px;
`;

export const addButton = styled.button.attrs((props) => ({
	type: 'button',
}))`
	margin-left: 10px;
	width: 70px;
	height: 30px;
	background-color: #0f0;
	color: ${colorType.dark};
	outline: none;
	&:hover {
		background-color: ${colorType.gray};
		color: ${colorType.white};
		cursor: pointer;
	}
`;

export const removeButton = styled.button.attrs((props) => ({
	type: 'button',
}))`
	margin-left: 10px;
	width: 70px;
	height: 30px;
	background-color: #0f0;
	color: ${colorType.dark};
	outline: none;
	&:hover {
		background-color: ${colorType.gray};
		color: ${colorType.white};
		cursor: pointer;
	}
`;

export const AttributeLabel = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-around;
	margin-top: 20px;
	padding-top: 10px;
	border-top: 1px solid ${colorType.gray};
	& > label {
		font-size: 20px;
	}
`;

export const PersonContainer = styled.div`
	display: flex;
	justify-content: center;
`;
