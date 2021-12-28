/* eslint-disable react/destructuring-assignment */
import React from 'react';
import { changeModalMode } from '@src/store/modules/modal';
import useShow from '@src/hooks/useShow';
import * as StyledComponent from './style';

interface ButtonProps {
	text: string;
}
// eslint-disable-next-line react/prop-types
const Button = ({ text }: ButtonProps) => {
	const [show, dispatch] = useShow();
	const onClick = () => {
		dispatch(changeModalMode());
	};
	return (
		<>
			<StyledComponent.CreateButton onClick={onClick}>
				{text}
			</StyledComponent.CreateButton>
		</>
	);
};

export default Button;
