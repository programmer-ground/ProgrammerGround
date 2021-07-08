/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';
import colorType from '@src/utils/color';

export const CreateContainer = styled.div`
	display: flex;
	box-sizing: border-box;
	width: 80%;
	flex-direction: column;
	border: 3px solid ${colorType.gray};
	border-radius: 10px;
	margin: 0 auto;
`;

export const CreateSubTitle = styled.div`
	display: flex;
	justify-content: center;
	.create_project_title {
		font-size: 30px;
		line-height: 38px;
		text-align: center;
	}
	.create_comment_title {
		margin: 30px 0 0 20px;
		font-size: 15px;
		line-height: 22px;
		letter-spacing: -0.3px;
		color: #999;
	}
`;

export const CreateNameDiv = styled.div`
	display: flex;
	justify-content: center;
	&:not(:first-child) {
		margin-top: 20px;
	}
	.project_text_name {
		font-size: 20px;
		line-height: 24px;
		width: 34%;
		border: none;
		border-bottom: 1px solid #707070;
		&:focus {
			outline: none;
		}
		&::placeholder {
			color: #f00;
			font-size: 15px;
			font-weight: bold;
			line-height: 22px;
		}
	}
	&:first-child .project_label_name {
		margin-top: 5px;
	}
	.project_label_name {
		width: 130px;
		display: block;
		text-align: center;
		font-size: 20px;
		line-height: 24px;
	}

	.project_editor_text {
		width: 34%;
		margin: 0;
		padding: 0;
		height: 300px;
		border: 1px solid #707070;
		color: #f00;
		overflow-y: auto;
	}

	.project_position {
		width: 34%;
		border: none;
		font-weight: bold;
		border-bottom: 1px solid #707070;
		color: #f00;
	}
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
	display: flex;
	justify-content: center;
	text-align: center;
	margin-top: 20px;
	font-size: 20px;
	line-height: 24px;

	.person_max_text {
		display: inline-block;
		width: 130px;
	}

	.person_number_info {
		margin-left: 21%;
	}

	.person_number {
		margin-left: 59px;
		color: #dbdbdb;
	}

	.person_unit {
		display: inline-block;
	}
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
