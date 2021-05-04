/* eslint-disable import/prefer-default-export */
/* eslint-disable consistent-return */
export const getItem = (key: string) => {
	if (
		document.cookie.split('; ').length === 1 &&
		document.cookie.split('=')[0] === 'access_token'
	)
		return document.cookie.split('=')[1];
	document.cookie.split('; ').reduce((total: string, currentCookie: string) => {
		const item = currentCookie.split('=');
		const storedKey = item[0];
		const storedValue = item[1];
		return key === storedKey ? decodeURIComponent(storedValue) : total;
	}, '');
};
