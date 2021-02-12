import React, { useState } from 'react';
import * as StyledComponent from './style';

const Bone = () => {
	const [darkMode, setDarkMode] = useState('light');

	const modeChange = () => {
		if (darkMode === 'light') {
			setDarkMode('dark');
		} else if (darkMode === 'dark') {
			setDarkMode('light');
		}
	};
	return (
		<StyledComponent.BoneContainer onClick={modeChange}>
			{darkMode === 'light' ? (
				<StyledComponent.BoneMoveContainer
					// eslint-disable-next-line react/jsx-indent-props
					onClick={modeChange}
				/>
			) : (
				<StyledComponent.BoneMoveDarkContainer onClick={modeChange} />
			)}
		</StyledComponent.BoneContainer>
	);
};

export default Bone;
