/* eslint-disable react/jsx-key */
/* eslint-disable react/require-default-props */
/* eslint-disable react/prop-types */
import React from 'react';
import * as StyledComponent from './style';

interface Playground {
	title: string;
	date: string;
	src?: string;
	position: string;
	personnel: string;
	language: string[];
}

const PlaygroundContent = ({
	title,
	date,
	src,
	position,
	personnel,
	language,
}: Playground) => {
	return (
		<>
			<StyledComponent.PlaygroundContent>
				<StyledComponent.PlaygroundHeader>
					<StyledComponent.PlaygroundTitle>
						{title}
					</StyledComponent.PlaygroundTitle>
					<StyledComponent.PlaygroundDate>
						{date}
					</StyledComponent.PlaygroundDate>
				</StyledComponent.PlaygroundHeader>
				<StyledComponent.PlaygroundImg src={src} />
				<StyledComponent.PlaygroundPersonInfo>
					<span>{position}</span>
					<span>{personnel}</span>
				</StyledComponent.PlaygroundPersonInfo>
				<StyledComponent.PlaygroundTechListContainer>
					{language.map((v, i) => {
						return (
							<StyledComponent.PlaygroundTechList>
								{v}
							</StyledComponent.PlaygroundTechList>
						);
					})}
				</StyledComponent.PlaygroundTechListContainer>
			</StyledComponent.PlaygroundContent>
		</>
	);
};

export default PlaygroundContent;
