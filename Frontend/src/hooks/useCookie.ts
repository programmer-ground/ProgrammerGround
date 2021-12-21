import { getItem } from '@src/utils/getCookie';

const useCookie = (key: string) => {
	const getCookie = () => getItem(key);
	const cookie = getCookie();
	return [cookie];
};

export default useCookie;
