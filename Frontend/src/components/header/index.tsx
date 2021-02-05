import React from 'react';
import * as StyledComponent from './style';

const Header = () => {
	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.HeaderContainer>
				<StyledComponent.HeaderImg />
			</StyledComponent.HeaderContainer>
		</>
	);
};

export default Header;
