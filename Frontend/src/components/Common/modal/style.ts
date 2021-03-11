/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const Container = styled.div`
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	z-index: 100;
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
`;
export const ModalContent = styled.div`
	background-color: #fefefe;
	width: 50%;
	height: 400px;
	margin: 15% auto;
	border: 1px solid #888;
	border-radius: 10px;
`;

export const ModalHeader = styled.header`
	width: 100%;
	height: 50px;
	display: flex;
	justify-content: space-between;
	margin: 0 auto;
`;

export const ModalClose = styled.div`
	font-size: 28px;
	height: 10px;
	color: #aaa;
	font-weight: bold;
	cursor: pointer;
	border-radius: 10px;
`;

export const ModalTitle = styled.div`
	font-size: 20px;
`;
