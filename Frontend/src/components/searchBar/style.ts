import styled from 'styled-components';

// eslint-disable-next-line import/prefer-default-export
export const SearchBarContainer = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 50px;
`;

export const SearchBarInput = styled.input`
	width: 600px;
	height: 50px;
	border-radius: 50px;
	border-color: rgba(29, 192, 120, 0.12);
	background-color: rgba(29, 192, 120, 0.12);
	font-size: 20px;
	padding-left: 15px;
	box-shadow: 0px 4px 10px 0 rgb(0 0 0 / 10%);
	&:focus {
		background-color: #fff;
		transition: all 0.2s ease;
		box-shadow: 0px 10px 10px 0 rgb(0 0 0 / 10%);
		outline: none;
	}
`;
