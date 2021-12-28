/* eslint-disable react/destructuring-assignment */
/* eslint-disable react/no-unused-prop-types */
import React from 'react';
import * as StyledComponent from './style';

interface DataTypes {
	data: string;
}
// eslint-disable-next-line react/prop-types
const EdiTor = ({ data }: DataTypes) => {
	return (
		<>
			{data === 'bold' && <StyledComponent.ButtonBoldImage />}
			{data === 'italic' && <StyledComponent.ButtonItalicImage />}
			{data === 'text' && <StyledComponent.ButtonTextImage />}
		</>
	);
};

export default EdiTor;
