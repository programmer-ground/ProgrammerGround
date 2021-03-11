/* eslint-disable react/destructuring-assignment */
import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@src/store/modules/index';
import { changeModalMode } from '@src/store/modules/modal';
import * as StyledComponent from './style';

const Button = (props: { text: string }) => {
	const { show } = useSelector((state: RootState) => state.modalReducer);
	const dispatch = useDispatch();
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
