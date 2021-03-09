/* eslint-disable react/destructuring-assignment */
import React from 'react';
import * as StyledComponent from './style';

const Button = (props: { text: string }) => {
	return (
		<>
			<StyledComponent.CreateButton>{props.text}</StyledComponent.CreateButton>
		</>
	);
};

export default Button;
