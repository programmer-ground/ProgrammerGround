/* eslint-disable react/jsx-pascal-case */
import React from 'react';
import * as StyledComponent from './style';

const HashTag = () => {
	return (
		<>
			<StyledComponent.Hash>
				<StyledComponent.EditorBody>
					<StyledComponent.Placeholder>
						내용을 입력하세요 (@멘션)
					</StyledComponent.Placeholder>
					<StyledComponent.Inp />
					<StyledComponent.outp />
				</StyledComponent.EditorBody>
			</StyledComponent.Hash>
		</>
	);
};

export default HashTag;
