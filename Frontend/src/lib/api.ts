/* eslint-disable import/prefer-default-export */
import { AxiosRequestConfig } from 'axios';

export const getOptions: AxiosRequestConfig = {
	mode: 'cors',
	credentials: 'include',
	withCredentials: true,
	headers: {
		'Content-Type': 'application/json',
		'Access-Control-Allow-Origin': '*',
	},
};
