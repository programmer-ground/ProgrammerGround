import styled, { createGlobalStyle, keyframes } from 'styled-components';


export const InfoBodyContainer = styled.div`
	  &:not(:first-child) {
			border-top: 1px solid #e9e9e9;
		}
`; 

export const InfoBodyContent = styled.div`
		padding: 4px 16px;
		&:not(:first-child) {
			border-top: 1px solid #e9e9e9;
		}
`;

export const InfoBodyTitle = styled.strong`
		font-weight: normal;
		position: relative; 

		&::after {
			position: absolute;
			bottom: 1px;
			left: 0;
			width: 100%;
			height: 7px;
			background-color: #78ffe0;
			opacity: 0.5;
			content: '';

		}
`;

export const InfoBodyAuthor = styled.div`
		&:not(:first-child) {
			margin-top: 4px;
		}
`;

export const InfoAuthorName = styled.span`
		font-size: 5px;
		&:not(:last-child) {
			margin-right: 5px;
		}
`;

export const InfoNameEmphasis = styled.em`
		font-weight: normal;
		color: #0abe16;
`;


export const InfoAuthorPosition = styled.span`
		&::before {
			content: '';
			margin: 0 5px 2px 0;
			display: inline-block;
			width: 2px;
			height: 2px;
			background-color: #000;
		}
		font-size: 5px;
`;

export const InfoAuthorContainer = styled.div`
		display: flex;
`;

export const InfoContainerItem = styled.div`
		flex: 1 1 auto;
		&:not(:last-child) {
			margin-right: 10px;
		}
		&:not(:first-child) {
			line-height: 40px;
		}
`;

export const InfoAcceptButton = styled.button`
		border: 1px solid #e9e9e9;
		background-color: #fff;
		border-radius: 10px;
		cursor: pointer;

		&:hover {
			color: #fff;
			background-color: #00bcd4;
		}
`;

export const InfoRejectButton = styled.button`
	  &:not(:first-child) {
			margin-left: 5px;
		}
		border: 1px solid #e9e9e9;
		background-color: #fff;
		border-radius: 10px;
		cursor: pointer;
		&:hover {
			color: #fff;
			background-color: #00bcd4;
		}
`;

export const InfoTitleBody = styled.div`
		display: flex;
`;

export const InfoBodyDate = styled.span`
		&:not(:first-child) {
			margin-left: auto;
			padding-top: 3px;
		}

		font-size: 10px;
		line-height: 15px;
`;

