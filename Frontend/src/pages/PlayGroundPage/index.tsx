/* eslint-disable no-unused-expressions */
/* eslint-disable @typescript-eslint/ban-ts-comment */
/* eslint-disable consistent-return */
/* eslint-disable no-return-await */
/* eslint-disable react/jsx-pascal-case */
/* eslint-disable array-callback-return */
// @ts-nocheck
import React, { useState, useEffect, useRef } from 'react';
import Header from '@src/components/header';
import { useDispatch, useSelector } from 'react-redux';
import PlaygroundContent from '@src/components/playgroundContent';
import { getAllPlaygrounds, getOneUser } from '@src/lib/axios/playground';
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
			if (item.length !== 0) {
				throttler.throttle(fetchMoreData, 500);
			}
		}
	};

	const fetchData = async () => {
		try {
			const data = await getAllPlaygrounds();

			const response = data.playground_card;
			for (const card of response) {
				card.created_date = card.created_date.toString().slice(0, 10);
			}
			setResult(response.slice(0, 6));
			response = response.slice(6);
			setItem(response);
		} catch (e) {
			console.log(e);
		}
	};

	const fetchMoreData = async () => {
		setResult(result.concat(item.slice(0, 6)));
		setItem(item.slice(6));
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
				<StyledComponent.PlaygroundTitle>
					플레이 그라운드 정보
				</StyledComponent.PlaygroundTitle>
				<StyledComponent.PlayGroundContainer>
					{result.map((v) => {
						return (
							<PlaygroundContent
								key={Math.random() * 10000}
								title={v.title}
								position={v.position_list[0].position_name}
								language={v.position_list[0].language}
								positionList={v.position_list}
								src={v.logo_img_name}
								id={v.playground_id}
								user={v.leader_user_name}
								createDate={v.created_date}
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
