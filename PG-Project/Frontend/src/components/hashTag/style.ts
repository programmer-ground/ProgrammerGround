/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const Hash = styled.div`
	height: 10px;
	font-family: sans-serif;
`;

export const EditorBody = styled.div`
	display: flex;
	justify-content: space-around;
	width: 100%;
	height: 100%;
	overflow-x: none;
	border-top: 1px solid #e9e9e9;
	border-bottom: 1px solid #e9e9e9;
	position: relative;
	box-sizing: border-box;
	min-height: 5em;
`;

export const Placeholder = styled.div`
	pointer-events: none;
	text-align: left;
	width: 20%;
	min-height: 100%;
	position: absolute;
	top: 20%;
	left: 80%;
	box-sizing: border-box;
	border-bottom: 1px solid #e4e4e4;
`;

export const Inp = styled.div.attrs((props) => ({
	contentEditable: 'true',
}))`
	top: 20%;
	left: 80%;
	width: 150px;
	height: 20px;
	position: absolute;
	overflow-y: visible;
	border-bottom: 1px solid #e4e4e4;
`;

export const mention = styled.span`
	background: #69c;
	color: #fff;
	border-radius: 0.5em;
	padding: 0 0.2em;
	margin: 0 -0.2em;
`;

export const inputComponent = styled.input.attrs((props) => ({
	type: 'text',
}))`
	width: 70px;
	height: 20px;
	margin-top: 10px;
`;
