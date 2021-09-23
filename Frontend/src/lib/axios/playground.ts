import { AxiosResponse } from 'axios';
import { getOptions } from '@src/lib/api';
import { getData, patchData, postData, putData, deleteData } from './request';

const url = {
	GET_ALL_PLAYGROUND: 'http://localhost:9000/playground',
	CREATE_PLAYGROUND: 'http://localhost:9000/playground',
	GET_ONE_PLAYGROUND: 'http://localhost:9000/playground/',
	CREATE_IMAGE_PLAYGROUND: 'http://localhost:9000/images/pgmainimg/',
	GET_ONE_USER: 'http://localhost:9000/user',
	PATCH_ONE_USER: 'http://localhost:9000/user',
	DELETE_ONE_PLAYGROUND: 'http://localhost:9000/playground/',
	GET_POSITION_LIST:'http://localhost:9000/playground/',
	APPLY_PLAYGROUND: 'http://localhost:9000/playground/',
	GET_NOTICE_LEADER: 'http://localhost:9000/user/notices/leader',
	PUT_APPLY_ACCEPT: 'http://localhost:9000/playground/applicants/',
	PUT_APPLY_REJECT: 'http://localhost:9000/playground/applicants/'
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

export const patchOneUser = async(userName: string, type:string) => {
	const user = await patchData(`${url.PATCH_ONE_USER}`, userName, type);
	return user;
}

export const getPositionList = async (playgroundId: number) => {
	const positionList = await getData(`${url.GET_POSITION_LIST}${playgroundId}/slots`);
	return positionList;
}

export const createApplyRequest = async (applyPlayground: any, playgroundId: number) => {
	const createApply = await postData(`${url.APPLY_PLAYGROUND}${playgroundId}/apply`, applyPlayground, 'apply');
	return createApply;
}

export const getNoticeLeaderList = async () => {
	const noticeList = await getData(`${url.GET_NOTICE_LEADER}`);
	return noticeList;
export const applyAcceptPlayground = async (playgroundApplyId: number) => {
	const acceptPlayground = await putData(`${url.PUT_APPLY_ACCEPT}/${playgroundApplyId}/accept`);
	return acceptPlayground;
}

export const applyRejectPlayground = async (playgroundApplyId: number) => {
	const rejectPlayground = await putData(`${url.PUT_APPLY_REJECT}/${playgroundApplyId}/reject`);
	return rejectPlayground;
}