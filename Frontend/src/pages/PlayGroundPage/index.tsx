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
	const [infiniteData, setInfiniteData] = useState({
		playgroundList: [],
		itemLength: 15,
		prevIndex: 0,
	});
	const [playgrounds, setPlaygrounds] = useState([]);
	const { playgroundShow } = useSelector(
		(state: RootState) => state.modalReducer,
	);
	const infiniteScroll = () => {
		const scrollHeight = Math.max(
			document.documentElement.scrollHeight,
			document.body.scrollHeight,
		);
		const scrollTop = Math.max(
			document.documentElement.scrollTop,
			document.body.scrollTop,
		);

		const { clientHeight } = document.documentElement;

		if (scrollTop + clientHeight >= scrollHeight) {
			setInfiniteData({
				...infiniteData,
				prevIndex: infiniteData.itemLength,
				itemLength: infiniteData.itemLength + 15,
			});
			fetchData();
		}
	};

	const fetchData = async () => {
		try {
			const data = await getAllPlaygrounds();
			const result = data.playground_card.slice(
				infiniteData.prevIndex,
				infiniteData.itemLength,
			);
			setInfiniteData({
				...infiniteData,
				playgroundList: [...infiniteData.playgroundList, ...result],
			});
			setPlaygrounds(infiniteData.playgroundList);
		} catch (e) {
			console.log(e);
		}
	};
	useEffect(() => {
		fetchData();
	}, []);

	useEffect(() => {
		window.addEventListener('scroll', infiniteScroll, true);
		return () => {
			window.removeEventListener('scroll', infiniteScroll, true);
		};
	}, [infiniteScroll]);
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
					{infiniteData.playgroundList.map((v) => {
						return (
							<PlaygroundContent
								key={Math.random() * 10000}
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
