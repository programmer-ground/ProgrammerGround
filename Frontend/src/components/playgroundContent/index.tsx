/* eslint-disable react/require-default-props */
/* eslint-disable react/prop-types */
import React from 'react';
import * as StyledComponent from './style';

interface Playground {
	title: string;
	date: string;
	src?: string;
}

const PlaygroundContent = ({ title, date, src }: Playground) => {
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
			</StyledComponent.PlaygroundContent>
		</>
	);
};

export default PlaygroundContent;
