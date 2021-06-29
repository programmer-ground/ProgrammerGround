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
import playgroundData from '@src/data/playground';
import Button from '@src/components/Common/button';
import { getAllPlaygrounds } from '@src/lib/axios/playground';
import useShow from '@src/hooks/useShow';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	const groundData = playgroundData.content;
	const [show, dispatch] = useShow();
	const [datas, setData] = useState([]);
	useEffect(() => {
		const fetchData = async () => {
			try {
				const data = await getAllPlaygrounds();
				setData(data.playground_card);
			} catch (e) {
				console.log(e);
			}
		};
		fetchData();
	}, []);
	return (
		<>
			<Header />
			{datas.map((v) => {
				return <h2>{v.playground_id}</h2>;
			})}
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
