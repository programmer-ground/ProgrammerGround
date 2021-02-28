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
					<PlaygroundContent title="TypeScript Making" date="2021.01.09" />
					<PlaygroundContent title="SVG Making" date="2021.01.17" />
					<PlaygroundContent title="Spring Core" date="2021.01.27" />
					<PlaygroundContent title="React" date="2021.02.28" />
				</StyledComponent.PlayGroundContainer>
			</StyledComponent.mainContainer>
		</>
	);
};
export default PlayGroundPage;
