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
import OnePlaygroundModal from '@src/components/Common/modal/onePlaygroundModal';
import { RootState } from '@src/store/modules';
import { throttling } from '@src/utils/throttle';
import * as StyledComponent from './style';

const PlayGroundPage = () => {
	const [result, setResult] = useState<any[] | any>([]);
	const [item, setItem] = useState<any[] | any>([]);
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
			const throttler = throttling();
			throttler.throttle(fetchMoreData, 500);
		}
	};

	const fetchData = async () => {
		try {
			const data = await getAllPlaygrounds();
			let response = data.playground_card;
			setResult(response.slice(0, 15));
			response = response.slice(15);
			setItem(response);
		} catch (e) {
			console.log(e);
		}
	};

	const fetchMoreData = async () => {
		setResult(result.concat(item.slice(0, 15)));
		setItem(item.slice(15));
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
					{/* <StyledComponent.ModeContainer>
						<Bone />
					</StyledComponent.ModeContainer> */}
					<StyledComponent.CreateLink>방 생성</StyledComponent.CreateLink>
				</StyledComponent.SearchContainer>
				<StyledComponent.PlayGroundContainer>
					{result.map((v) => {
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
