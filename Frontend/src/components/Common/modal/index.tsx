/* eslint-disable radix */
/* eslint-disable react/jsx-one-expression-per-line */
/* eslint-disable react/jsx-key */
/* eslint-disable react/button-has-type */
/* eslint-disable jsx-a11y/label-has-associated-control */
/* eslint-disable react/prop-types */
import React, { useState, useEffect, useRef } from 'react';
import useShow from '@src/hooks/useShow';
import { changeModalMode } from '@src/store/modules/modal';
import { createPosition } from '@src/store/modules/createPosition';
import ModalInput from '@src/components/Common/modalInput';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@src/store/modules/index';
import ModalNumberInput from '@src/components/Common/modal/modalNumber';
import * as StyledComponent from './style';

const ModalWrapper = () => {
	const [show, dispatch] = useShow();
	const { persons } = useSelector((state: RootState) => state.positionReducer);
	const [maxPersonNumber, setMaxPersonNumber] = useState(0);

	const closeClick = () => {
		dispatch(changeModalMode());
	};
	const plusPosition = () => {
		dispatch(createPosition());
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
								<StyledComponent.InputSection>
									<label>이름:</label>
									<ModalInput name="name" placeholder="이름을 적어주세요!" />
								</StyledComponent.InputSection>
								<StyledComponent.InputSection>
									<label>타이틀:</label>
									<ModalInput
										name="title"
										placeholder="프로젝트에 대한 상세 설명을 해주세요!"
									/>
								</StyledComponent.InputSection>
								<StyledComponent.InputSection>
									<label>leader 포지션:</label>
									<select>
										<option value="Backend">Backend</option>
										<option value="Frontend">Frontend</option>
										<option value="Infra">Infra</option>
										<option value="UI/UX">UI/UX</option>
										<option value="디자이너">디자이너</option>
									</select>
								</StyledComponent.InputSection>
								<StyledComponent.InputSection>
									최대 {maxPersonNumber}명
								</StyledComponent.InputSection>

								<hr />
								<StyledComponent.ModalCreateSection>
									<StyledComponent.ModalCreateSectionTitle>
										<div>포지션</div>
										<div>인원</div>
										<button type="button" onClick={plusPosition}>
											추가
										</button>
									</StyledComponent.ModalCreateSectionTitle>
									{persons.map((v, i) => {
										return (
											<StyledComponent.ModalCreateSectionBody
  className="input-Ref"
												key={i}
											>
												<input
													type="text"
													name="position"
													placeholder={v.position}
												/>

												<ModalNumberInput
  placeholder={v.personNumber}
  setMaxPersonNumber={setMaxPersonNumber}
												/>
											</StyledComponent.ModalCreateSectionBody>
										);
									})}
								</StyledComponent.ModalCreateSection>
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
