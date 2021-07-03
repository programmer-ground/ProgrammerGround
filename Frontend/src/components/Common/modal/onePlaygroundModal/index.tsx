import React from 'react';
import { changeModalMode } from '@src/store/modules/modal';
import useShow from '@src/hooks/useShow';
import * as StyledComponent from './style';

const OnePlaygroundModal = () => {
	const [show, dispatch] = useShow();
	const closeHandler = () => {
		console.log('1');
	};
	return (
		<>
			<StyledComponent.ModalContainer>
				<StyledComponent.ModalContent>
					<StyledComponent.ModalHeader>
						<StyledComponent.ModalTitle>
							PlayGround 생성
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
