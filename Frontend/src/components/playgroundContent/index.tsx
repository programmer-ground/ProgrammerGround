/* eslint-disable react/prop-types */
import React from 'react';
import * as StyledComponent from './style';

interface Playground {
	title: string;
	date: string;
}

const PlaygroundContent = ({ title, date }: Playground) => {
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
			</StyledComponent.PlaygroundContent>
		</>
	);
};

export default PlaygroundContent;
