/* eslint-disable react/prop-types */
import React from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '@src/store/modules/index';
import * as StyledComponent from './style';

const ModalWrapper = () => {
	const { show } = useSelector((state: RootState) => state.modalReducer);

	return <div>{show ? <StyledComponent.Container /> : ''}</div>;
};

export default ModalWrapper;
