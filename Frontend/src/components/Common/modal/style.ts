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
	margin: 10% auto;
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
	margin-top: 20px;
	margin-right: 10px;
`;

export const ModalTitle = styled.div`
	font-size: 30px;
	margin-top: 20px;
	box-sizing: border-box;
`;

export const ModalBody = styled.div`
	padding-top: 20px;

	font-size: 20px;
	& hr {
		width: 100%;
	}
`;
export const InputSection = styled.div`
	margin-top: 20px;
	display: flex;
	justify-content: center;
`;
export const ModalCreateSection = styled.div`
	display: flex;
	flex-direction: column;
`;

export const ModalCreateSectionTitle = styled.div`
	display: flex;
	justify-content: space-around;
	& > div {
		margin-left: 35px;
	}
	& > button {
		width: 55px;
		height: 32px;
		cursor: pointer;
		background-color: #04c584;
		font-size: 12px;
		color: #fff;
		border: 0px;
	}
`;

export const ModalCreateSectionBody = styled.div`
	display: flex;
	& input {
		background-color: transparent;
		border: 0px;
		border-bottom: 1px solid #000;
		height: 20px;
		width: 100px;
		margin-left: 56px;
	}
`;
