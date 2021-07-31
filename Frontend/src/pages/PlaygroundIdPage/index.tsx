import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const PlaygroundIdPage = () => {
	const location = useLocation();
	const [title, setTitle] = useState('');
	console.log((location.state as any).playgroundTitle);
	useEffect(() => {
		setTitle((location.state as any).playgroundTitle);
	}, []);
	return (
		<>
			<div>{title}</div>
			<div>ddd</div>
		</>
	);
};

export default PlaygroundIdPage;
