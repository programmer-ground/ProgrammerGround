import React from 'react';
import { playgroundModalMode } from '@src/store/modules/modal';
import useShow from '@src/hooks/useShow';
import { getAllPlaygrounds, getOnePlayground } from '@src/lib/axios/playground';
import { getAllPlayground } from '@src/store/modules/Playground';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@src/store/modules';
import * as StyledComponent from './style';

const OnePlaygroundModal = () => {
	const [show, dispatch] = useShow();
	const { onePlayground } = useSelector(
		(state: RootState) => state.playgroundReducer,
	);
	const closeHandler = async () => {
		dispatch(playgroundModalMode());
	};
	return (
		<>
			<StyledComponent.ModalContainer>
				<StyledComponent.ModalContent>
					<StyledComponent.ModalHeader>
						<StyledComponent.ModalTitle>
							{onePlayground.title}
							{onePlayground.description}
						</StyledComponent.ModalTitle>
						<StyledComponent.ModalClose onClick={closeHandler}>
							&times;
						</StyledComponent.ModalClose>
					</StyledComponent.ModalHeader>
				</StyledComponent.ModalContent>
			</StyledComponent.ModalContainer>
		</>
	);
};

export default OnePlaygroundModal;
