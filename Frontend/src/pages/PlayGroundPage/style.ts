/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const SearchContainer = styled.div`
	display: flex;
	justify-content: center;
`;

export const mainContainer = styled.div`
	display: flex;
	height: 320px;
	flex-direction: column;
`;

export const ModeContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 70px;
	height: 56px;
	margin-top: 50px;
	margin-left: 40px;
`;

export const PlayGroundContainer = styled.div`
	&:not(:nth-child(3n-1)){
		margin:left:20px;
	}
  display: flex;
	margin: 100px auto;
	width: 80%;
	height: 100%;
	flex-wrap: wrap;
`;
