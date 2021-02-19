import styled from 'styled-components';
import searchImage from '@src/assets/searchImage.png';
// eslint-disable-next-line import/prefer-default-export
export const SearchBarSection = styled.section``;
export const SearchBarContainer = styled.div`
	position: relative;
	display: flex;
	max-width: 600px;
	width: 100%;
	margin-top: 50px;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		margin-left: 50px;
	}
`;

export const SearchBarInput = styled.input`
	width: 600px;
	height: 50px;
	border-radius: 50px;
	border: 1px solid rgba(29, 192, 120, 0.12);
	background-color: rgba(29, 192, 120, 0.12);
	font-size: 20px;
	padding-left: 15px;
	box-shadow: 0px 4px 10px 0 rgb(0 0 0 / 10%);
	&:focus {
		background-color: #fff;
		transition: all 0.2s ease;
		box-shadow: 0px 10px 10px 0 rgb(0 0 0 / 10%);
		outline: none;
	}
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 500px;
		height: 40px;
		font-size: 15px;
		pading-left: 30px;
		margin-left: 30px;
	}
`;

export const SearchButton = styled.button`
	background-color: inherit;
	border: 0px;
	outline: none;
	position: absolute;
	right: 20px;
	top: 20%;
	@media screen and (max-width: 735px) and (min-width: 600px) {
		width: 20px;
		height: 20px;
		left: 500px;
	}
	@media screen and (max-width: 600px) and (min-width: 570px) {
		left: 480px;
	}
`;

export const SearchImg = styled.img.attrs({
	src: searchImage,
})`
	width: 30px;
	height: 30px;
	cursor: pointer;
	@media screen and (max-width: 735px) and (min-width: 0px) {
		width: 20px;
		height: 20px;
	}
`;
