/* eslint-disable react/jsx-pascal-case */
/* eslint-disable array-callback-return */
import React from 'react';
import Header from '@src/components/header';
import SearchBar from '@src/components/searchBar';
import Bone from '@src/components/Common/bone';
import PlaygroundContent from '@src/components/playgroundContent';
import playgroundData from '@src/data/playground';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	const groundData = playgroundData.content;
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
					{groundData.map((v) => {
						return (
							<PlaygroundContent
								key={v.id}
								title={v.title}
								date={v.date}
								src={v.src}
								position={v.position}
								personnel={v.personnel}
								language={v.language}
							/>
						);
					})}
				</StyledComponent.PlayGroundContainer>
			</StyledComponent.mainContainer>
		</>
	);
};
export default PlayGroundPage;
