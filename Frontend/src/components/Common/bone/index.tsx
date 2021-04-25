import React, { useState } from 'react';
import useChange from '@src/hooks/useChange';
import * as StyledComponent from './style';

const Bone = () => {
	const [darkMode, setDarkMode] = useState('light');

	const modeChange = () => {
		if (darkMode === 'light') {
			useChange('dark', setDarkMode);
		} else if (darkMode === 'dark') {
			useChange('light', setDarkMode);
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
			<div>
				<StyledComponent.MoonImg />
				<StyledComponent.SunImg />
			</div>
		</StyledComponent.BoneContainer>
	);
};

export default Bone;
