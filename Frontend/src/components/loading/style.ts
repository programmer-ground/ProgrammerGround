import styled, { keyframes } from 'styled-components';

export const Container = styled.div`
	width: 100%;
	height: 100%;
	position: fixed;
	left: 0;
	top: 0;
	z-index: 10;
	background-color: rgba(37, 23, 46, 0.24);
`;

export const InnerContainer = styled.div`
	position: absolute;
	z-index: 11;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
`;
const fallAnimation = keyframes`
  50%{
    transform: rotate(-30deg);
  }
`;

const riseAnimation = keyframes`
  50%{
    transform: rotate(30deg);
  }
`;
export const Stick = styled.div`
	width: 30px;
	height: 3px;
	background-color: #04c584;
	display: inline-block;
	&:nth-child(n) {
		transform: rotate(30deg);
		-webkit-transform: rotate(30deg);
		-moz-transform: rotate(30deg);
		-webkit-animation: ${fallAnimation} 2s infinite;
		-moz-animation: ${fallAnimation} 2s infinite;
	}
	&:nth-child(2n) {
		transform: rotate(-30deg);
		-webkit-transform: rotate(-30deg);
		-moz-transform: rotate(-30deg);
		-webkit-animation: ${riseAnimation} 2s infinite;
		-moz-animation: ${riseAnimation} 2s infinite;
	}
`;

const fade = keyframes`
50% {
  opacity: 0.5;
}
100% {
  opacity: 1;
}
`;
export const LoadingText = styled.h1`
	font-family: 'Lato';
	color: #000;
	text-transform: uppercase;
	font-size: 20px;
	line-height: 24px;
	letter-spacing: 1.5px;
	text-align: center;
	width: 155px;
	margin-top: 20px;
	-webkit-animation: ${fade} 1s infinite;
	-moz-animation: ${fade} 1s infinite;
`;
