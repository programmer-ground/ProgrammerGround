/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const BoneContainer = styled.div`
	position: relative;
	width: 50px;
	height: 30px;
	margin-top: 5px;
	margin-bottom: 5px;
	background-color: #d9dfe2;
	border-radius: 50px;
	border: 1px solid #d9dfe2;
	cursor: pointer;
`;

export const BoneMoveContainer = styled.div`
	width: 28px;
	height: 30px;
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	left: 0px;
	transition: 1s;
`;
export const BoneMoveDarkContainer = styled.div`
	width: 28px;
	height: 30px;
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	left: 22px;
	transition: 1s;
`;
