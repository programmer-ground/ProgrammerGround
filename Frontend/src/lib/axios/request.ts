import axios from 'axios';
import useCookie from '@src/hooks/useCookie';

const informError = (error: Error) => {
	console.error(error);
	const message = error.response.data.message
		? error.response.data.message
		: '오류가 발생하여 요청에 실패하였습니다';
};

const getOptions = () => {
	const cookie = useCookie('access_token');

	const options = {
		mode: 'cors',
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			Authorization: `bearer ${cookie}`,
		},
	};

	return options;
};
