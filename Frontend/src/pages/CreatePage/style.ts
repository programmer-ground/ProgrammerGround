/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';
import colorType from '@src/utils/color';

export const CreateContainer = styled.div`
	padding: 30px 20px;
`;

export const CreateSubTitle = styled.div`
	.create_project_title {
		display: inline-block;
		font-size: 30px;
		font-weight: normal;
		line-height: 38px;
	}
	.create_comment_title {
		display: inline-block;
		vertical-align: middle;
		margin-left: 10px;
		font-size: 15px;
		line-height: 22px;
		letter-spacing: -0.3px;
		color: #ccc;
	}
`;

export const CreateNameDiv = styled.div`
	display: flex;
	margin-top: 20px;
	.project_text_name {
		font-size: 20px;
		line-height: 24px;
		width: 100%;
		border: none;
		border-bottom: 1px solid #707070;
		&:focus {
			outline: none;
			border-bottom: 1px solid #4382f7;
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
		width: 120px;
		flex: 1 0 auto;
		display: block;
		font-size: 20px;
		line-height: 24px;
		color: #666;
	}

	.project_editor_text {
		flex: 1 1 auto;
		margin: 0;
		padding: 0;
		width: 100%;
		height: 300px;
		border: 1px solid #707070;
		color: #f00;
		overflow-y: auto;
	}

	.project_position {
		width: 100%;
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
	margin-top: 20px;
	font-size: 20px;
	line-height: 24px;

	.person_max_text {
		display: inline-block;
		width: 120px;
		color: #666;
	}

	.person_number {
		margin-left: 59px;
		color: #dbdbdb;
	}

	.person_unit {
		display: inline-block;
	}

	.person_number_info {
		flex: 1 1 auto;
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
	width: 100%;
	padding: 10px 20px;
	background-color: #4182fa;
	font-size: 20px;
	font-weight: bold;
	line-height: 28px;
	color: #fff;
	outline: none;
	cursor: pointer;
`;

export const removeButton = styled.button.attrs((props) => ({
	type: 'button',
}))`
	width: 100%;
	padding: 10px 20px;
	background-color: #edeff2;
	font-size: 20px;
	font-weight: bold;
	line-height: 28px;
	color: #999;
	outline: none;
	cursor: pointer;
	border-left: 0;
`;

export const AttributeLabel = styled.div`
	&:not(:first-child) {
		margin-top: 20px;
	}
	padding-top: 10px;
	width: 100%;
	display: flex;
	justify-content: space-around;
	border-top: 1px solid ${colorType.gray};
	& > label {
		font-size: 20px;
	}
`;

export const PersonContainer = styled.div`
	display: flex;
	justify-content: space-between;
	.person_position {
		width: 100%;
		border: none;
		font-weight: bold;
		border-bottom: 1px solid #707070;
		color: #f00;
	}
	.project_text_name {
		font-size: 20px;
		line-height: 24px;
		width: 100%;
		border: none;
		border-bottom: 1px solid #707070;
		&:focus {
			outline: none;
			border-bottom: 1px solid #4382f7;
		}
		&::placeholder {
			color: #f00;
			font-size: 15px;
			font-weight: bold;
			line-height: 22px;
		}
	}
`;

export const SubmitButton = styled.button.attrs((props) => ({
	type: 'submit',
}))`
	width: 100%;
	margin-top: 30px;
	padding: 10px 20px;
	font-size: 20px;
	font-weight: bold;
	line-height: 28px;
	color: #ccc;
	background-color: #000;
	cursor: pointer;
`;
