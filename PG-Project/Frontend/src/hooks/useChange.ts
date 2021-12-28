import React from 'react';

const useChange = (
	afterMode: string,
	setDarkMode: (value: React.SetStateAction<string>) => void,
) => {
	setDarkMode(afterMode);
	localStorage.setItem('color-theme', afterMode);
	document.documentElement.setAttribute('color-theme', afterMode);
};

export default useChange;
