import React from 'react';
import * as StyledComponent from './style';
import './headerImage.scss';

const Header = () => {
	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.HeaderContainer>
				<a href="http://localhost:3000/playground">
					<StyledComponent.HeaderImg />
				</a>
				<StyledComponent.HeaderMenuContainer>
					<div className="projection_icon" />
					<StyledComponent.AlarmIcon />
					<StyledComponent.ProfileIcon />
				</StyledComponent.HeaderMenuContainer>
			</StyledComponent.HeaderContainer>
		</>
	);
};

export default Header;
