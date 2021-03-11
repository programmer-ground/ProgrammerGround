/* eslint-disable jsx-a11y/label-has-associated-control */
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
						<StyledComponent.ModalBody>
							<form action="/">
								<div>
									<label>이름:</label>
									<input type="text" name="name" />
								</div>
								<div>
									<label>타이틀:</label>
									<input type="text" name="name" />
								</div>
								<div>최대 0명</div>
							</form>
						</StyledComponent.ModalBody>
					</StyledComponent.ModalContent>
				</StyledComponent.Container>
			) : (
				''
			)}
		</div>
	);
};

export default ModalWrapper;
