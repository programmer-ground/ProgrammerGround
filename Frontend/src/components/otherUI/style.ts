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
  position: relative;
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

export const ApplyModalContainer = styled.div`
	position: fixed;
	top: 0; 
	right: 0;
	bottom: 0;
	left: 0;
  background-color: rgba(0, 0, 0, 0.7);
`;

export const ApplyModalContent = styled.div`
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #fff;
	border-radius: 1px;
`;

export const ApplyModalHead = styled.div`
	display: flex;
	padding: 15px;
`;

export const ApplyModalTitle = styled.strong`
    flex: 1 1 auto;
		font-size: 20px;
		line-height: 35px;
	  font-weight: normal;

		&:not(:last-child) {
			margin-right: 10px;
		}
`;

export const ApplyModalButton = styled.button.attrs((props) => {
  type: 'button'
})`
  flex: 1 1 auto;
  padding: 10px;
	border: none;
	background-color: transparent;
	cursor: pointer;
`;

export const ApplyModalBody = styled.div`
	 &:not(:first-child) {
		 margin-top: 20px;
	 }
	 padding: 0 15px 15px;
`;

export const ApplyModalInput = styled.input.attrs((props) => {
	type: 'checkbox'
})`
 clip: rect(0 0 0 0);
 position: absolute;
 width: 1px;
 height: 1px;
 margin: -1px;
 overflow: hidden;
 
`;

export const ApplyModalLabel = styled.label.attrs((props) => {

})`
  display: inline-block;
  width: 33.3%;
	height: 40px;
	line-height: 40px;
	padding: 0 2px;
	box-sizing: border-box;
	text-align: center;
	border: 1px solid #d9d9d9;
	color: ${props=> props.checkState? '#fff': '#515254'};
	background-color: ${props=> props.checkState? '#4c90dd' : '#fff'}
`;