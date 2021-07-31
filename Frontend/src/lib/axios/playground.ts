import { AxiosResponse } from 'axios';
import { getOptions } from '@src/lib/api';
import { getData, patchData, postData, putData, deleteData } from './request';

const url = {
	GET_ALL_PLAYGROUND: 'http://localhost:9000/playground',
	CREATE_PLAYGROUND: 'http://localhost:9000/playground',
	GET_ONE_PLAYGROUND: 'http://localhost:9000/playground/',
};

// eslint-disable-next-line import/prefer-default-export
export const getAllPlaygrounds = async () => {
	const playgrounds = await getData(url.GET_ALL_PLAYGROUND);
	return playgrounds;
};

export const createPlayground = async (playgroundData: any) => {
	const playground = await postData(url.CREATE_PLAYGROUND, playgroundData);
	return playground;
};

export const getOnePlayground = async (playgroundId: number) => {
	const playground = await getData(`${url.GET_ONE_PLAYGROUND}${playgroundId}`);
	return playground;
};
