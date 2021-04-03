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
	display: flex;
	justify-content: space-between;
	margin: 50px auto;
	width: 80%;
	height: 100%;
	flex-wrap: wrap;
`;

export const CreateLink = styled.a.attrs((props) => ({
	href: 'http://localhost:3000/playground',
}))`
	box-sizing: border-box;
	display: inline-block;
	font-size: 12px;
	padding: 9px 11px;
	width: 55px;
	margin-top: 62px;
	margin-left: 12px;
	height: 32px;
	text-decoration: none;
	background-color: #04c584;
	color: #fff;
`;
