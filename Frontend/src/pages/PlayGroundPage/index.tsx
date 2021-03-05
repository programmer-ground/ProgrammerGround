import React from 'react';
import Header from '@src/components/header';
import SearchBar from '@src/components/searchBar';
import Bone from '@src/components/Common/bone';
import PlaygroundContent from '@src/components/playgroundContent';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	return (
		<>
			<Header />
			<StyledComponent.mainContainer>
				<StyledComponent.SearchContainer>
					<SearchBar />
					<StyledComponent.ModeContainer>
						<Bone />
					</StyledComponent.ModeContainer>
				</StyledComponent.SearchContainer>
				<StyledComponent.PlayGroundContainer>
					<PlaygroundContent />
					<PlaygroundContent />
					<PlaygroundContent />
					<PlaygroundContent />
				</StyledComponent.PlayGroundContainer>
			</StyledComponent.mainContainer>
		</>
	);
};
export default PlayGroundPage;
