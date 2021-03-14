/* eslint-disable react/prop-types */
import React from 'react';
import useShow from '@src/hooks/useShow';
import { changeModalMode } from '@src/store/modules/modal';
import * as StyledComponent from './style';

const ModalWrapper = () => {
	const [show, dispatch] = useShow();
	const closeClick = () => {
		dispatch(changeModalMode());
	};
	return (
		<div>
			{show ? (
				<StyledComponent.Container>
					<StyledComponent.ModalContent>
						<StyledComponent.ModalHeader>
							<div />
							<StyledComponent.ModalTitle>
								PlayGround 생성
							</StyledComponent.ModalTitle>
							<StyledComponent.ModalClose onClick={closeClick}>
								&times;
							</StyledComponent.ModalClose>
						</StyledComponent.ModalHeader>
					</StyledComponent.ModalContent>
				</StyledComponent.Container>
			) : (
				''
			)}
		</div>
	);
};

export default ModalWrapper;
