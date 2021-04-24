/* eslint-disable import/prefer-default-export */
import axios, { AxiosRequestConfig } from 'axios';

export const getOptions: AxiosRequestConfig = {
	headers: {
		'Content-Type': 'application/json',
		'Access-Control-Allow-Origin': '*',
	},
};
