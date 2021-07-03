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
	background-color: #fefefe;
	border: 1px solid #888;
	border-radius: 10px;
`;

export const ModalClose = styled.div`
	font-size: 28px;
	line-height: 36px;
	height: 10px;
	color: #aaa;
	font-weight: bold;
	cursor: pointer;
	border-radius: 10px;
	margin: 20px 10px 0 0;
`;

export const ModalHeader = styled.header`
	width: 100%;
	height: 50px;
	display: flex;
	justify-content: space-between;
	margin: 0 auto;
`;

export const ModalTitle = styled.div`
	font-size: 30px;
	margin-top: 20px;
	box-sizing: border-box;
`;
