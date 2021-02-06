import React from 'react';
import * as StyledComponent from './style';

const Header = () => {
	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.HeaderContainer>
				<StyledComponent.HeaderImg />
				<StyledComponent.HeaderMenuContainer>
					<StyledComponent.ProjectIcon />
				</StyledComponent.HeaderMenuContainer>
			</StyledComponent.HeaderContainer>
		</>
	);
};

export default Header;
