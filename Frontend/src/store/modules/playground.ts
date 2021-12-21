/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
/* eslint-disable no-param-reassign */
/* eslint-disable import/prefer-default-export */
import { createAction, handleActions } from 'redux-actions';

const GET_PLAYGROUND = 'modal/GET_ONE_PLAYGROUND';
const GET_ALL_PLAYGROUND = 'modal/GET_ALL_PLAYGROUND';

export const getOnePlaygroundItem = createAction(GET_PLAYGROUND);
export const getAllPlayground = createAction(GET_ALL_PLAYGROUND);

type Action = ReturnType<typeof getOnePlayground>;
type getAllAction = ReturnType<typeof getAllPlayground>;

interface Playground {
	id: number;
	description: string;
	maxUserNum: number;
	userInfoList: [];
	title: string;
}
export interface PlaygroundState {
	playground: Playground[];
	onePlayground: Playground;
}
const initialState = {
	playground: [],
	onePlayground: {
		id: 0,
		description: '',
		maxUserNum: 0,
		userInfoList: [],
		title: '',
	},
};

export const playgroundReducer = handleActions(
	{
		[GET_ALL_PLAYGROUND]: (
			state: PlaygroundState = initialState,
			action: getAllAction,
		) => {
			return {
				...state,
				playground: action.payload,
			};
		},
		[GET_PLAYGROUND]: (
			state: PlaygroundState = initialState,
			action: Action,
		) => {
			return {
				...state,
				onePlayground: action.payload,
			};
		},
	},
	initialState,
);
