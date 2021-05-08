import { getData, patchData, postData, putData, deleteData } from './request';

const url = {
	GET_ALL_PLAYGROUND: 'http://localhost:9000/playground',
};

// eslint-disable-next-line import/prefer-default-export
export const getAllPlaygrounds = async () => {
	const playgrounds = await getData(url.GET_ALL_PLAYGROUND);
	return playgrounds;
};
