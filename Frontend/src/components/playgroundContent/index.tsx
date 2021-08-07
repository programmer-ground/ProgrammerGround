/* eslint-disable react/no-array-index-key */
/* eslint-disable react/jsx-key */
/* eslint-disable react/require-default-props */
/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import useShow from '@src/hooks/useShow';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@src/store/modules';
import { useHistory } from 'react-router-dom';
import { playgroundModalMode } from '@src/store/modules/modal';
import { getOnePlayground } from '@src/lib/axios/playground';
import { getOnePlaygroundItem } from '@src/store/modules/Playground';
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
	const [show, dispatch] = useShow();
	const history = useHistory();
	const [image, setImage] = useState(null);
	const createModalFunc = async (
		playgroundId: number,
		event: any,
		playgroundTitle: string,
	) => {
		const data = await getOnePlayground(playgroundId);
		dispatch(getOnePlaygroundItem(data));
		history.push({
			pathname: `/playground/${playgroundId}`,
			state: { playgroundTitle },
		});
	};
	return (
		<>
			<StyledComponent.PlaygroundContent
				onClick={(e) => createModalFunc(id, e, title)}
			>
				<StyledComponent.PlaygroundHeader>
					<StyledComponent.PlaygroundTitle>
						{title}
					</StyledComponent.PlaygroundTitle>
					<StyledComponent.PlaygroundDate>
						{date}
					</StyledComponent.PlaygroundDate>
				</StyledComponent.PlaygroundHeader>
				<StyledComponent.PlaygroundImg
					src={`http://localhost:9000/images/pgmainimg/${src}`}
				/>
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
		</>
	);
};

export default PlaygroundContent;
