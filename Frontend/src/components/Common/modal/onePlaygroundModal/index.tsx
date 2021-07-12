import React from 'react';
import { playgroundModalMode } from '@src/store/modules/modal';
import useShow from '@src/hooks/useShow';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@src/store/modules';
import * as StyledComponent from './style';

const OnePlaygroundModal = () => {
	const [show, dispatch] = useShow();
	const { onePlayground } = useSelector(
		(state: RootState) => state.playgroundReducer,
	);
	const closeHandler = async () => {
		console.log(onePlayground);
		dispatch(playgroundModalMode());
	};
	return (
		<>
			<StyledComponent.ModalContainer>
				<StyledComponent.ModalContent>
					<StyledComponent.ModalHeader>
						<StyledComponent.ModalTitle>
							{onePlayground.title}
						</StyledComponent.ModalTitle>
						<StyledComponent.ModalClose onClick={closeHandler}>
							&times;
						</StyledComponent.ModalClose>
					</StyledComponent.ModalHeader>
					<StyledComponent.ModalInputItem>
						<span className="project_title">프로젝트 생성일</span>
						<span className="project_date" />
					</StyledComponent.ModalInputItem>
					<StyledComponent.ModalInputItem>
						<span className="project_title">프로젝트 설명</span>
						<span className="project_description">
							{onePlayground.description}
						</span>
					</StyledComponent.ModalInputItem>
				</StyledComponent.ModalContent>
			</StyledComponent.ModalContainer>
		</>
	);
};

export default OnePlaygroundModal;
