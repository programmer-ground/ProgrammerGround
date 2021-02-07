import React from 'react';
import * as StyledComponent from './style';

const SearchBar = () => {
	return (
		<>
			<StyledComponent.SearchBarContainer>
				<StyledComponent.SearchBarInput placeholder="검색하고 싶은 프로젝트를 입력하세요" />
			</StyledComponent.SearchBarContainer>
		</>
	);
};

export default SearchBar;
