/* eslint-disable react/no-array-index-key */
/* eslint-disable react/jsx-key */
/* eslint-disable react/require-default-props */
/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import useShow from '@src/hooks/useShow';

import OnePlaygroundModal from '@src/components/Common/modal/onePlaygroundModal';
import { getOnePlayground } from '@src/lib/axios/playground';
import { useDispatch, useSelector } from 'react-redux';
import { playgroundModalMode } from '@src/store/modules/modal';
import { RootState } from '@src/store/modules';
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
	const [show, dispatch] = useShow();
	const { playgroundShow } = useSelector(
		(state: RootState) => state.modalReducer,
	);
	const createModalFunc = async (playgroundId: number, event: any) => {
		dispatch(playgroundModalMode());
		const onePlayground = await getOnePlayground(playgroundId);
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
			{playgroundShow ? <OnePlaygroundModal /> : ''}
		</>
	);
};

export default PlaygroundContent;
