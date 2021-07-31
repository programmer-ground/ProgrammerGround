import React from 'react';
import * as StyledComponent from './style';
import './headerImage.scss';
import SearchBar from '@src/components/searchBar/index';

const Header = () => {
	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.HeaderContainer>
				<a href="http://localhost:3000/">
					<StyledComponent.HeaderImg />
				</a>
				<StyledComponent.PlaygroundSearchSection>
					<SearchBar />
				</StyledComponent.PlaygroundSearchSection>
				<StyledComponent.CreateLink>방 생성</StyledComponent.CreateLink>
				<StyledComponent.HeaderMenuContainer>
					<div className="user_icon" />
					<div className="alarm_icon" />
				</StyledComponent.HeaderMenuContainer>
			</StyledComponent.HeaderContainer>
		</>
	);
};

export default Header;
