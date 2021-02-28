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
`;

export const PlaygroundDate = styled.div`
	font-size: 20px;
	font-weight: bold;
`;
