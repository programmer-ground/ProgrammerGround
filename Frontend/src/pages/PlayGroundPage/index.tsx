import React from 'react';
import Header from '@src/components/header';
import SearchBar from '@src/components/searchBar';
import Bone from '@src/components/Common/bone';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	return (
		<>
			<Header />
			<StyledComponent.SearchContainer>
				<SearchBar />
				<StyledComponent.ModeContainer>
					<Bone />
				</StyledComponent.ModeContainer>
			</StyledComponent.SearchContainer>
		</>
	);
};
export default PlayGroundPage;
