/* eslint-disable import/prefer-default-export */
import styled from 'styled-components';

export const Hash = styled.div`
	height: 10px;
	font-family: sans-serif;
`;

export const EditorBody = styled.div`
	width: 100%;
	height: 100%;
	overflow-y: auto;
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
	width: 100%;
	height: auto;
	min-height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	overflow-y: visible;
	&:focus {
		outline: none;
	}
`;

export const Inp = styled.div.attrs((props) => ({
	contentEditable: 'true',
}))`
	z-index: 1;
	text-align: left;
	width: 100%;
	height: auto;
	min-height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	overflow-y: visible;
	&:focus {
		outline: none;
	}
`;

export const outp = styled.div.attrs((props) => ({}))`
	z-index: 2;
	text-align: left;
	width: 100%;
	height: auto;
	min-height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	overflow-y: visible;
	pointer-events: none;
	&:focus {
		outline: none;
	}
`;

export const mention = styled.span`
	background: #69c;
	color: #fff;
	border-radius: 0.5em;
	padding: 0 0.2em;
	margin: 0 -0.2em;
`;
