import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import OtherUI from '@src/components/otherUI/';
import Header from '@src/components/header';
import SameUI from '@src/components/sameUI';

const PlaygroundIdPage = () => {
	const location = useLocation();
	const {
		playgroundTitle,
		data,
		loginUserName,
		src,
		createDate,
		positionList,
		id,
	} = location.state as any;
	console.log(id, loginUserName, data, playgroundTitle);
	return (
		<>
			<Header />
			{loginUserName.oauth_name === data.userInfoList[0].oauthName ? (
				<SameUI
					playgroundTitle={playgroundTitle}
					src={src}
					createDate={createDate}
					data={data}
					positionList={positionList}
					id={id}
				/>
			) : (
				<OtherUI
					playgroundTitle={playgroundTitle}
					src={src}
					createDate={createDate}
					data={data}
					positionList={positionList}
					id={id}
				/>
			)}
		</>
	);
};

export default PlaygroundIdPage;
