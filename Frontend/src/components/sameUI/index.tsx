import React from 'react';
import * as StyledComponent from './style';

const SameUI = () => {
	return (
		<StyledComponent.SameContainer>
			<StyledComponent.SameModifyButton>
				수정하기
			</StyledComponent.SameModifyButton>
			<StyledComponent.SameDeleteButton>
				삭제하기
			</StyledComponent.SameDeleteButton>
		</StyledComponent.SameContainer>
	);
};

export default SameUI;
