import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import OtherUI from '@src/components/otherUI/';
import Header from '@src/components/header';
import SameUI from '@src/components/sameUI';

const PlaygroundIdPage = () => {
	const location = useLocation();
	const { playgroundTitle, data, loginUserName } = location.state as any;

	return (
		<>
			<Header />
			{loginUserName.oauth_name === data.userInfoList[0].oauthName ? (
				<SameUI />
			) : (
				<OtherUI />
			)}
		</>
	);
};

export default PlaygroundIdPage;
