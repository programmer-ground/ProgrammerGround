/* eslint-disable jsx-a11y/label-has-associated-control */
/* eslint-disable react/prop-types */
import React from 'react';
import useShow from '@src/hooks/useShow';
import { changeModalMode } from '@src/store/modules/modal';
import ModalInput from '@src/components/Common/modalInput';
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
									<ModalInput name="name" placeholder="이름을 적어주세요!" />
								</div>
								<div>
									<label>타이틀:</label>
									<ModalInput
										name="title"
										placeholder="프로젝트에 대한 상세 설명을 해주세요!"
									/>
								</div>
								<div>
									<label>leader 포지션:</label>
									<ModalInput
										name="position"
										placeholder="리더의 포지션을 입력해주세요!"
									/>
								</div>
								<div>최대 0명</div>
								<hr />
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
