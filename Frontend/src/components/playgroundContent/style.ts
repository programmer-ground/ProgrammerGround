/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const PlaygroundContent = styled.a`
	display: flex;
	flex-direction: column;
	width: 300px;
	background-color: #fff;
	cursor: pointer;
	border: 1px solid rgba(0, 0, 0, 0.1);
	border-radius: 4px;
	&:not(:nth-child(3n + 1)) {
		margin-left: 16px;
	}
	&:not(:nth-child(-n + 3)) {
		margin-top: 60px;
	}
`;
export const PlaygroundHeader = styled.header`
	display: flex;
	padding: 10px 0;
	border-bottom: 1px solid blue;
	justify-content: space-between;
`;
export const PlaygroundTitle = styled.div`
	&:not(:first-child) {
		margin-top: 10px;
	}
	display: -webkit-box;
	font-size: 30px;
	line-height: 36px;
	font-weight: 400;
	color: #000;
	overflow: hidden;
	text-overflow: ellipsis;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
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
	padding: 16px;
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

export const PlaygroundStatus = styled.span`
	padding: 0 12px;
	background-color: #00bcd4;
	border-radius: 15px;
`;

export const PlaygroundUserContainer = styled.div`
	display: flex;
	justify-content: space-between;
`;

export const PlaygroundUserName = styled.span`
	font-size: 19px;
	line-height: 26px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
`;

export const PlaygroundCreateDate = styled.span`
	font-size: 19px;
	line-height: 26px;
`;
