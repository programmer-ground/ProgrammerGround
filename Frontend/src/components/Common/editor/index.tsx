/* eslint-disable react/destructuring-assignment */
/* eslint-disable react/no-unused-prop-types */
import React from 'react';
import * as StyledComponent from './style';

// eslint-disable-next-line react/prop-types
const Editor = (props: { data: string }) => {
	return (
		<>
			{props.data === 'bold' && <StyledComponent.ButtonBoldImage />}
			{props.data === 'italic' && <StyledComponent.ButtonItalicImage />}
			{props.data === 'text' && <StyledComponent.ButtonTextImage />}
		</>
	);
};

export default Editor;
