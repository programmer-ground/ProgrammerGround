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
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 40px;
		height: 20px;
		margin-top: -10px;
		margin-left: -10px;
	}
`;

export const SunImg = styled.img.attrs({
	src: sun,
})`
	width: 20px;
	height: 20px;
	padding-top: 5px;
	padding-right: 5px;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 15px;
		height: 15px;
		padding-top: 3px;
		padding-right: 1px;
	}
`;

export const MoonImg = styled.img.attrs({
	src: moon,
})`
	width: 23px;
	height: 23px;
	padding-top: 4px;
	padding-left: 5px;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 15px;
		height: 15px;
		padding-top: 3px;
		padding-right: 2px;
	}
`;

export const BoneMoveContainer = styled.div`
	width: 28px;
	height: 30px;
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	left: 0px;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 20px;
		height: 20px;
	}
`;
export const BoneMoveDarkContainer = styled.div`
	width: 28px;
	height: 30px;
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	left: 22px;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 20px;
		height: 20px;
	}
`;
