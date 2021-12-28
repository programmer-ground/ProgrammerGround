/* eslint-disable camelcase */
/* eslint-disable import/prefer-default-export */
/* eslint-disable consistent-return */
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck

import axios from 'axios';
import useCookie from '@src/hooks/useCookie';

const informError = (error: Error) => {
	const message = error.message
		? error.message
		: '오류가 발생하여 요청에 실패하였습니다';
	console.info(message);
};

export const getOptions = async (type?: string) => {
	const refreshToken = useCookie('refresh_token');
	if (refreshToken[0] === '') {
		document.cookie = 'access_token=; Max-Age=0';
		history.pushState(null, null, '/login');
		location.reload();
	}

	let cookie = useCookie('access_token');

	if (cookie[0] === '') {
		await getReissued();
		cookie = useCookie('access_token');
	}
	if(type === 'delete') {
		return cookie[0];
	}
	const options = setOptions(cookie[0], type);
	return options;
};

const setOptions = (token: string, type: string): any => {
	const headers = {};
	if (type === 'image') {
		headers = {
			'Content-Type': 'multipart/form-data',
			'Access-Control-Allow-Origin': '*',
			Accept: 'application/json',
			Authorization: `Bearer ${token}`,
		};
	}  else{
		headers = {
			'Content-Type': 'application/json;charset=UTF-8',
			'Access-Control-Allow-Origin': '*',
			Accept: 'application/json',
			Authorization: `Bearer ${token}`,
		};
	}
	const options = {
		mode: 'cors',
		credentials: 'include',
		withCredentials: true,
		headers,
	};
	return options;
};

const getReissued = async () => {
	const refreshToken = useCookie('refresh_token');

	const options = setOptions(refreshToken[0]);
	try {
		const data = await axios.post(
			'http://localhost:8080/reissued',
			'',
			options,
		);
	} catch (err) {
		console.log('response: ', err.response.data);
	}
};

export const getData = async (url: string) => {
	const options = await getOptions();
 	try {
		const response = await axios.get(url, options);
		return response.data.data;
	} catch (error) {
		informError(error);
	}
};

export const postData = async (url: string, body: any, type: string) => {
	const options = await getOptions(type);
	try {
		const response = await axios.post(url, body, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};

export const patchData = async (url: string, body: string, type: string) => {
	const options = await getOptions(type);
	const data = {
		'userName': body
	}
	try {
		const response = await axios.patch(url, data, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};

export const putData = async (url: string) => {
	const options = await getOptions();
	try {
		const response = await axios.put(url, url, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};

export const deleteData = async (url: string, type: string) => {
	const options = await getOptions();
	try {
		const response = await axios.delete(url, options);
		return response.data;
	} catch (error) {
		informError(error);
	}
};
