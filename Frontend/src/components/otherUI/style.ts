import styled from 'styled-components';

export const ApplyContainer = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 150px;
`;

export const ApplyButton = styled.button.attrs((_props) => ({
	type: 'button',
}))`
	background-color: #edeff2;
	color: #000;
	padding: 15px;
	font-size: 20px;
	line-height: 28px;
	cursor: pointer;
	border-radius: 5px;
`;

export const ApplyMainContainer = styled.div`
	padding-top: 150px;
	width: 960px;
	margin: 0 auto;
`;

export const SameContainer = styled.div`
	display: flex;
	justify-content: center;
	&:not(:first-child) {
		margin-top: 30px;
	}
`;

export const ApplyTitle = styled.h2`
	font-size: 30px;
	line-height: 38px;
	margin-top: 150px;
	&:not(:nth-child(2)) {
		margin-top: 30px;
	}
	text-align: center;
`;

export const ApplyHightlightTitle = styled.span`
	position: relative;
	&::after {
		content: '';
		position: absolute;
		left: 0;
		right: 0;
		height: 8px;
		bottom: 5px;
		background-color: rgba(159, 242, 241, 0.43);
	}
`;

export const ApplyTitleImage = styled.img.attrs((props) => ({
	src: props.src,
}))`
	display: block;
	margin: 0 auto;
	&:not(:first-child) {
		margin-top: 30px;
	}
	width: 100%;
	height: 100%;
`;

export const ApplyContent = styled.div`
	margin: 0 auto;
	background-color: #fff;
	&:not(:first-child) {
		margin-top: 50px;
	}
	text-align: center;
	font-size: 20px;
	line-height: 28px;
	font-family: Roboto, sans-serif;
	padding: 10px;
	color: #333;
	border: 1px solid rgba(0, 0, 0, 0.12);
`;

export const ApplySeveralContent = styled.div`
	border: 1px solid #e7e7e7;
	padding: 10px;
`;

export const ApplyContentText = styled.div`
	text-align: center;
	display: inline-block;
	padding: 0 20px;
`;

export const ApplyContentTextInner = styled.div`
	&:not(:first-child) {
		margin-top: 15px;
	}
	text-align: left;
`;
export const ApplyContentTextContainer = styled.div``;

export const ApplyContentTextLabel = styled.span`
	font-size: 20px;
	line-height: 28px;
`;

export const ApplyContentValue = styled.span`
	&:not(:first-child) {
		margin-left: 10px;
	}
	font-size: 14px;

	background-color: red;
	border-radius: 20px;
	padding: 5px;
	color: #fff;
`;

export const ApplyContentLanguageLabel = styled.span`
	&:not(:first-child) {
		margin-left: 10px;
	}
	background: ${(props) => props.color};
	font-size: 14px;
	border-radius: 20px;
	padding: 5px;
	color: #fff;
`;
