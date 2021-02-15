/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';
import sun from '@src/assets/sun.svg';
import moon from '@src/assets/moon.svg';

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
	div {
		display: flex;
	}
`;

export const SunImg = styled.img.attrs({
	src: sun,
})`
	width: 20px;
	height: 20px;
	padding-top: 5px;
	padding-right: 5px;
`;

export const MoonImg = styled.img.attrs({
	src: moon,
})`
	width: 23px;
	height: 23px;
	padding-top: 4px;
	padding-left: 5px;
`;

export const BoneMoveContainer = styled.div`
	width: 28px;
	height: 30px;
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	left: 0px;
	transition: all 1s ease-out;
`;
export const BoneMoveDarkContainer = styled.div`
	width: 28px;
	height: 30px;
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	left: 22px;
	transition: all 0.25s linear;
`;
