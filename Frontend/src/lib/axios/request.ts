/* eslint-disable camelcase */
/* eslint-disable import/prefer-default-export */
/* eslint-disable consistent-return */
import axios from 'axios';
import useCookie from '@src/hooks/useCookie';

const informError = (error: Error) => {
	const message = error.message
		? error.message
		: '오류가 발생하여 요청에 실패하였습니다';
};

const getOptions = () => {
	let cookie = useCookie('access_token');
	if (cookie[0] === '') {
		getReissued();
		cookie = useCookie('access_token');
	}

	const options = setOptions(cookie[0]);
	return options;
};

const setOptions = (token: string) => {
	const options = {
		mode: 'cors',
		credentials: 'include',
		withCredentials: true,
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Access-Control-Allow-Origin': '*',
			Authorization: `Bearer ${token}`,
		},
	};
	return options;
};

const getReissued = async () => {
	const refreshToken = useCookie('refresh_token');
	const options = setOptions(refreshToken[0]);
	await axios.post('http://localhost:8080/reissued', options);
};

export const getData = async (url: string) => {
	const options = getOptions();
	try {
		const response = await axios.get(url, options);
		return response.data.data;
	} catch (error) {
		informError(error);
	}
};

export const postData = async (url: string, body: string) => {
	const options = getOptions();

	try {
		const response = await axios.post(url, body, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};

export const patchData = async (url: string, body: string) => {
	const options = getOptions();

	try {
		const response = await axios.patch(url, body, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};

export const putData = async (url: string, body: string) => {
	const options = getOptions();

	try {
		const response = await axios.put(url, body, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};

export const deleteData = async (url: string) => {
	const options = getOptions();

	try {
		const response = await axios.delete(url, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};
