/* eslint-disable react/destructuring-assignment */
import React from 'react';
import { changeModalMode } from '@src/store/modules/modal';
import useShow from '@src/hooks/useShow';
import * as StyledComponent from './style';

const Button = (props: { text: string }) => {
	const [show, dispatch] = useShow();
	const onClick = () => {
		dispatch(changeModalMode());
	};
	return (
		<>
			<StyledComponent.CreateButton onClick={onClick}>
				{props.text}
			</StyledComponent.CreateButton>
		</>
	);
};

export default Button;
