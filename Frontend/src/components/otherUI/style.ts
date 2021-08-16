import styled from 'styled-components';

export const ApplyContainer = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 150px;
`;

export const ApplyButton = styled.button.attrs((_props) => ({
	type: 'button',
}))`
	background-color: #e7e7e7;
	color: #000;
	padding: 15px;
	font-size: 20px;
	line-height: 28px;
	cursor: pointer;
	border-radius: 5px;
`;
