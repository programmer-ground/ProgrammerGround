/* eslint-disable react/button-has-type */
import React from 'react';
import * as StyledComponent from './style';

const SearchBar = () => {
	return (
		<>
			<StyledComponent.SearchBarSection>
				<StyledComponent.SearchBarContainer>
					<StyledComponent.SearchBarInput placeholder="검색하고 싶은 프로젝트를 입력하세요" />
					<StyledComponent.SearchButton>
						<StyledComponent.SearchImg />
					</StyledComponent.SearchButton>
				</StyledComponent.SearchBarContainer>
			</StyledComponent.SearchBarSection>
		</>
	);
};

export default SearchBar;
