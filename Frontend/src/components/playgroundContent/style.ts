/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const PlaygroundContent = styled.div`
	display: flex;
	flex-direction: column;
	width: 30%;
	height: 100%;
	background-color: #fff;
	margin-top: 50px;
	box-shadow: 0px 4px 10px 0 rgb(0 0 0 / 10%);
	cursor: pointer;
	transition: 1s all;
	&:hover {
		box-shadow: 5px 5px 10px 10px rgb(0 0 0 / 10%);
	}
`;
export const PlaygroundHeader = styled.header`
	display: flex;
	padding: 10px 0;
	border-bottom: 1px solid blue;
	justify-content: space-between;
`;
export const PlaygroundTitle = styled.div`
	font-size: 20px;
	font-weight: bold;
	color: #000;
`;

export const PlaygroundDate = styled.div`
	font-size: 20px;
	font-weight: bold;
	color: #000;
`;

export const PlaygroundImg = styled.img.attrs((props) => ({
	src: props.src,
}))`
	width: 100%;
	height: 200px;
`;

export const PlaygroundPersonInfo = styled.div`
	display: flex;
	justify-content: space-between;
	& span {
		margin-left: 10px;
		font-weight: bold;
		font-size: 20px;
		margin-top: 10px;
		color: #000;
	}
`;
export const PlaygroundTechListContainer = styled.div`
	display: flex;
`;
export const PlaygroundTechList = styled.div`
	margin-left: 10px;
	box-sizing: border-box;
	text-align: center;
	padding-top: 10px;
	width: 90px;
	height: 40px;
	background-color: #4572c4;
	font-weight: bold;
	color: #fff;
`;
