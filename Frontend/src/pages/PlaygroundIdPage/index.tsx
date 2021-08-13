import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const PlaygroundIdPage = () => {
	const location = useLocation();
	const { playgroundTitle, data, loginUserName } = location.state as any;

	return (
		<>
			{loginUserName.oauth_name === data.userInfoList[0].oauthName ? (
				<div>ee</div>
			) : (
				<div>실패</div>
			)}
			<div>{playgroundTitle}</div>
			<div>ddd</div>
		</>
	);
};

export default PlaygroundIdPage;
