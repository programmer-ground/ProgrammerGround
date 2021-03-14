/* eslint-disable react/destructuring-assignment */
import React from 'react';
import * as StyledComponents from './style';

const ModalInput = (props: { name: string; placeholder: string }) => {
	return (
		<StyledComponents.Input name={props.name} placeholder={props.placeholder} />
	);
};

export default ModalInput;
