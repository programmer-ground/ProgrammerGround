import { getData, patchData, postData, putData, deleteData } from './request';

export const url = {
	GET_JWT_TOKEN: `${process.env.REACT_APP_GET_OAUTH_TOKEN}`,
	GET_OAUTH_TOKEN: `${process.env.REACT_APP_GET_JWT_TOKEN}`,
	GET_ALL_PLAYGROUND: `${process.env.REACT_APP_API_ADDRESS}/playground`,
	CREATE_PLAYGROUND: `${process.env.REACT_APP_API_ADDRESS}/playground`,
	GET_ONE_PLAYGROUND: `${process.env.REACT_APP_API_ADDRESS}/playground/`,
	CREATE_IMAGE_PLAYGROUND: `${process.env.REACT_APP_API_ADDRESS}/images/pgmainimg/`,
	GET_ONE_USER: `${process.env.REACT_APP_API_ADDRESS}/user`,
	PATCH_ONE_USER: `${process.env.REACT_APP_API_ADDRESS}/user`,
	DELETE_ONE_PLAYGROUND: `${process.env.REACT_APP_API_ADDRESS}/playground`,
	GET_POSITION_LIST:`${process.env.REACT_APP_API_ADDRESS}/playground`,
	APPLY_PLAYGROUND: `${process.env.REACT_APP_API_ADDRESS}/playground`,
	GET_NOTICE_LEADER: `${process.env.REACT_APP_API_ADDRESS}/user/notices/leader`,
	GET_NOTICE_WAITING: `${process.env.REACT_APP_API_ADDRESS}/user/notices/waitings`,
	GET_NOTICE_RESULT: `${process.env.REACT_APP_API_ADDRESS}/user/notices/results`,
	PUT_APPLY_ACCEPT: `${process.env.REACT_APP_API_ADDRESS}/playground/applicants/`,
	PUT_APPLY_REJECT: `${process.env.REACT_APP_API_ADDRESS}/playground/applicants/`,
	CREATE_REPOSITORY: `${process.env.REACT_APP_API_ADDRESS}/playground/`
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
}

export const getNoticeWaitingList = async () => {
	const waitList = await getData(`${url.GET_NOTICE_WAITING}`);
	return waitList;
}

export const getNoticeResult = async () => {
	const resultList = await getData(`${url.GET_NOTICE_RESULT}`);
	return resultList;
}

export const applyAcceptPlayground = async (playgroundApplyId: number) => {
	const acceptPlayground = await putData(`${url.PUT_APPLY_ACCEPT}${playgroundApplyId}/accept`);
	return acceptPlayground;
}

export const applyRejectPlayground = async (playgroundApplyId: number) => {
	const rejectPlayground = await putData(`${url.PUT_APPLY_REJECT}${playgroundApplyId}/reject`);
	return rejectPlayground;
}

export const createRepository = async (playgroundId: number, githubRepoVo: any) => {
	const playground = await postData(`${url.CREATE_REPOSITORY}${playgroundId}/repo`, githubRepoVo, 'repository');
	return playground;
}
