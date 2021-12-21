/* eslint-disable import/prefer-default-export */
/* eslint-disable consistent-return */
export const getItem = (key: string) => {
	return document.cookie
		.split('; ')
		.reduce((total: string, currentCookie: string) => {
			const item = currentCookie.split('=');
			const storedKey = item[0];
			const storedValue = item[1];
			return key === storedKey ? storedValue : total;
		}, '');
};
