import React from 'react';
import * as StyledComponent from './style';

const LoadingSpinner = () => {
	return (
		<>
			<StyledComponent.Container>
				<StyledComponent.InnerContainer>
					<StyledComponent.Stick />
					<StyledComponent.Stick />
					<StyledComponent.Stick />
					<StyledComponent.Stick />
					<StyledComponent.Stick />
					<StyledComponent.Stick />
					<StyledComponent.LoadingText>로딩중</StyledComponent.LoadingText>
				</StyledComponent.InnerContainer>
			</StyledComponent.Container>
		</>
	);
};

export default LoadingSpinner;
