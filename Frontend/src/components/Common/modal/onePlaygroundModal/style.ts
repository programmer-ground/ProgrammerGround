/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const ModalContainer = styled.div`
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	z-index: 100;
	background-color: rgba(0, 0, 0, 0.4);
`;

export const ModalContent = styled.div`
	width: 80%;
	margin: 0 auto;
	padding: 0 30px;
	background-color: #fefefe;
	border: 1px solid #888;
	border-radius: 10px;
	overflow-y: auto;
`;

export const ModalClose = styled.div`
	position: absolute;
	top: 0;
	right: 10px;
	padding: 10px;
	margin: -10px;
	font-size: 28px;
	line-height: 36px;
	font-weight: bold;
	cursor: pointer;
	border-radius: 10px;
`;

export const ModalHeader = styled.header`
	display: flex;
	position: relative;
	justify-content: center;
`;

export const ModalTitle = styled.div`
	font-size: 40px;
	line-height: 50px;
	text-align: center;
	box-sizing: border-box;
`;

export const ModalInputItem = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 30px;
	& > .project_title {
		font-size: 30px;
		font-weight: bold;
		line-height: 40px;
	}

	& > .project_date {
		border-bottom: 1px solid #707070;
	}

	& > .project_description {
		display: block;
		width: 500px;
		height: 500px;
		margin-left: 30px;
		border: 1px solid #707070;
		color: #999;
		font-size: 20px;
		line-height: 27px;
		padding: 5px;
	}
`;
