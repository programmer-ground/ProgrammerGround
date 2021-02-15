import React, { useState, useEffect } from 'react';
import * as StyledComponent from './style';

const Bone = () => {
	const [darkMode, setDarkMode] = useState('light');

	const modeChange = () => {
		if (darkMode === 'light') {
			setDarkMode('dark');
			localStorage.setItem('color-theme', 'dark');
			document.documentElement.setAttribute('color-theme', 'dark');
		} else if (darkMode === 'dark') {
			setDarkMode('light');
			localStorage.setItem('color-theme', 'light');
			document.documentElement.setAttribute('color-theme', 'light');
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
