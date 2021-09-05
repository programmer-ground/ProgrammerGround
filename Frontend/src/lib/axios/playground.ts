import { AxiosResponse } from 'axios';
import { getOptions } from '@src/lib/api';
import { getData, patchData, postData, putData, deleteData } from './request';

const url = {
	GET_ALL_PLAYGROUND: 'http://localhost:9000/playground',
	CREATE_PLAYGROUND: 'http://localhost:9000/playground',
	GET_ONE_PLAYGROUND: 'http://localhost:9000/playground/',
	CREATE_IMAGE_PLAYGROUND: 'http://localhost:9000/images/pgmainimg/',
	GET_ONE_USER: 'http://localhost:9000/user',
	PUT_ONE_USER: 'http://localhost:9000/user',
	DELETE_ONE_PLAYGROUND: 'http://localhost:9000/playground/',
};

// eslint-disable-next-line import/prefer-default-export
export const getAllPlaygrounds = async () => {
	const playgrounds = await getData(url.GET_ALL_PLAYGROUND);
	return playgrounds;
};

export const createPlayground = async (playgroundData: any, type: string) => {
	const playground = await postData(
		url.CREATE_PLAYGROUND,
		playgroundData,
		type,
	);
	return playground;
};

export const getOnePlayground = async (playgroundId: number) => {
	const playground = await getData(`${url.GET_ONE_PLAYGROUND}${playgroundId}`);
	return playground;
};

export const getOneUser = async () => {
	const user = await getData(`${url.GET_ONE_USER}`);
	return user;
};

export const deleteOnePlayground = async (playgroundId: number) => {
	const playground = await deleteData(`${url.DELETE_ONE_PLAYGROUND}${playgroundId}`, 'delete');
	return playground;
};

export const PutOneUser = async(userName: string, type:string) => {
	const user = await putData(`${url.PUT_ONE_USER}`, userName, type);
	console.log(user);
	return user;
}