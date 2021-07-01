/* eslint-disable react/no-array-index-key */
/* eslint-disable react/jsx-key */
/* eslint-disable react/require-default-props */
/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import OnePlaygroundModal from '@src/components/Common/modal/onePlaygroundModal';
import { getOnePlayground } from '@src/lib/axios/playground';
import * as StyledComponent from './style';

interface Playground {
	id: number;
	title: string;
	date: string;
	src?: string;
	position: string;
	personnel: string;
	language: string[];
}

const PlaygroundContent = ({
	id,
	title,
	date,
	src,
	position,
	personnel,
	language,
}: Playground) => {
	const [openState, setOpenState] = useState(false);
	const createModalFunc = async (playgroundId: number, event: any) => {
		setOpenState(true);
		const onePlayground = await getOnePlayground(playgroundId);
		console.log(onePlayground);
	};
	return (
		<>
			<StyledComponent.PlaygroundContent
				onClick={(e) => createModalFunc(id, e)}
			>
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
							<StyledComponent.PlaygroundTechList key={i}>
								{v}
							</StyledComponent.PlaygroundTechList>
						);
					})}
				</StyledComponent.PlaygroundTechListContainer>
			</StyledComponent.PlaygroundContent>
			{openState ? <OnePlaygroundModal /> : ''}
		</>
	);
};

export default PlaygroundContent;
