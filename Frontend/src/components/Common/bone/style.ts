/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';
import sun from '@src/assets/sun.svg';
import moon from '@src/assets/moon.svg';

export const BoneContainer = styled.div`
	position: relative;
	width: 50px;
	height: 30px;
	margin: 5px 0;
	background-color: #d9dfe2;
	border: 1px solid #d9dfe2;
	border-radius: 50px;
	cursor: pointer;

	div {
		display: flex;
	}

	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 40px;
		height: 20px;
		margin: -10px 0 0 -10px;
	}
`;

export const SunImg = styled.img.attrs({
	src: sun,
})`
	width: 20px;
	height: 20px;
	padding: 5px 5px 0 0;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 15px;
		height: 15px;
		padding: 3px 0 0 8px;
	}
`;

export const MoonImg = styled.img.attrs({
	src: moon,
})`
	width: 23px;
	height: 23px;
	padding: 4px 0 0 5px;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 15px;
		height: 15px;
		padding: 3px 2px 0 0;
	}
`;

export const BoneMoveContainer = styled.div`
	width: 28px;
	height: 30px;
	left: ${(props) => (props.color === 'dark' ? '0px' : '22px')};
	border-radius: 50px;
	background-color: #fff;
	position: absolute;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 20px;
		height: 20px;
	}
`;
