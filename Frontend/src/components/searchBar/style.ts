import styled from 'styled-components';
import searchImage from '@src/assets/searchImage.png';
// eslint-disable-next-line import/prefer-default-export
export const SearchBarSection = styled.section``;
export const SearchBarContainer = styled.div`
	position: relative;
	max-width: 600px;
	width: 100%;
	margin-top: 50px;
`;

export const SearchBarInput = styled.input`
	width: 600px;
	height: 50px;
	border-radius: 50px;
	border-color: rgba(29, 192, 120, 0.12);
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
`;

export const SearchButton = styled.button`
	position: absolute;
	top: 23%;
	right: 20px;
	background-color: inherit;
	border: 0px;
	outline: none;
`;

export const SearchImg = styled.img.attrs({
	src: searchImage,
})`
	width: 30px;
	height: 30px;
	cursor: pointer;
`;