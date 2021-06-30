/* eslint-disable no-unused-expressions */
/* eslint-disable @typescript-eslint/ban-ts-comment */
/* eslint-disable consistent-return */
/* eslint-disable no-return-await */
/* eslint-disable react/jsx-pascal-case */
/* eslint-disable array-callback-return */
// @ts-nocheck
import React, { useState, useEffect, useLayoutEffect } from 'react';
import Header from '@src/components/header';
import SearchBar from '@src/components/searchBar';
import Bone from '@src/components/Common/bone';
import PlaygroundContent from '@src/components/playgroundContent';
import Button from '@src/components/Common/button';
import { getAllPlaygrounds } from '@src/lib/axios/playground';
import useShow from '@src/hooks/useShow';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	const [show, dispatch] = useShow();
	const [playgrounds, setPlaygrounds] = useState([]);
	useEffect(() => {
		const fetchData = async () => {
			try {
				const data = await getAllPlaygrounds();
				setPlaygrounds(data.playground_card);
			} catch (e) {
				console.log(e);
			}
		};
		fetchData();
	}, []);
	return (
		<>
			<Header />

			<StyledComponent.mainContainer>
				<StyledComponent.SearchContainer>
					<SearchBar />
					<StyledComponent.ModeContainer>
						<Bone />
					</StyledComponent.ModeContainer>
					<Button text="생성" />
					<StyledComponent.CreateLink>방 생성</StyledComponent.CreateLink>
				</StyledComponent.SearchContainer>
				<StyledComponent.PlayGroundContainer>
					{playgrounds.map((v) => {
						return (
							<PlaygroundContent
								key={v.playground_id}
								title={v.title}
								position={v.position_list[0].position_name}
								language={v.position_list[0].language}
							/>
						);
					})}
				</StyledComponent.PlayGroundContainer>
			</StyledComponent.mainContainer>
		</>
	);
};
export default PlayGroundPage;
