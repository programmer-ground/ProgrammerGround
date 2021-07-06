/* eslint-disable no-unused-expressions */
/* eslint-disable @typescript-eslint/ban-ts-comment */
/* eslint-disable consistent-return */
/* eslint-disable no-return-await */
/* eslint-disable react/jsx-pascal-case */
/* eslint-disable array-callback-return */
// @ts-nocheck
import React, { useState, useEffect, useRef } from 'react';
import Header from '@src/components/header';
import SearchBar from '@src/components/searchBar';
import Bone from '@src/components/Common/bone';
import { useDispatch, useSelector } from 'react-redux';
import PlaygroundContent from '@src/components/playgroundContent';
import { getAllPlaygrounds, getOnePlayground } from '@src/lib/axios/playground';
import useShow from '@src/hooks/useShow';
import OnePlaygroundModal from '@src/components/Common/modal/onePlaygroundModal';
import { RootState } from '@src/store/modules';
import { getAllPlayground } from '@src/store/modules/Playground';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	const [show, dispatch] = useShow();
	const [playgrounds, setPlaygrounds] = useState([]);
	const { playgroundShow } = useSelector(
		(state: RootState) => state.modalReducer,
	);

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
								id={v.playground_id}
							/>
						);
					})}
				</StyledComponent.PlayGroundContainer>
			</StyledComponent.mainContainer>
			{playgroundShow ? <OnePlaygroundModal /> : ''}
		</>
	);
};
export default PlayGroundPage;
