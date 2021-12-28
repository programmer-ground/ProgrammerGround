/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
/* eslint-disable no-param-reassign */
/* eslint-disable import/prefer-default-export */
import { createAction, handleActions } from 'redux-actions';

const CREATE_POSITION = 'modal/CREATE_POSITION';
const DELETE_POSITION = 'modal/DELETE_POSITION';
const CHANGE_POSITION = 'modal/CHANGE_POSITION';
const CHANGE_LEVEL = 'modal/CHANGE_LEVEL';
const CHANGE_SEVERAL_POSITION = 'modal/CHANGE_SEVERAL_POSITION';
const CHANGE_LANGUAGE = 'modal/CHANGE_LANGUAGE';

export const createPosition = createAction(CREATE_POSITION);
export const deletePosition = createAction(DELETE_POSITION);
export const changePosition = createAction(CHANGE_POSITION);
export const changeLevel = createAction(CHANGE_LEVEL);
export const changeSeveralPosition = createAction(CHANGE_SEVERAL_POSITION);
export const changeLanguage = createAction(CHANGE_LANGUAGE);

type Action = ReturnType<typeof createPosition>;
type deleteAction = ReturnType<typeof deletePosition>;
type changeAction = ReturnType<typeof changePosition>;
type levelAction = ReturnType<typeof changeLevel>;
type severalPositionAction = ReturnType<typeof changeSeveralPosition>;
type languageAction = ReturnType<typeof changeLanguage>;

interface Person {
	id: number;
	position_name: string;
	position_max_num: string;
	position_level: string;
	position_language: [];
}
export interface PositionState {
	title: string;
	description: string;
	max_user_num: number;
	leader_position: string;
	position: Person[];
}
const initialState = {
	title: '',
	description: '',
	max_user_num: 0,
	leader_position: '',
	position: [
		{
			id: 0,
			position_name: '',
			position_max_num: 0,
			position_level: '',
			position_language: [],
		},
	],
};

export const positionReducer = handleActions(
	{
		[CREATE_POSITION]: (
			state: PositionState = initialState,
			action: Action,
		) => {
			return {
				...state,

				position: state.position.concat({
					id: action.payload,
					position_name: 'BACKEND',
					position_max_num: 0,
					position_level: 'JUNIOR',
					position_language: [],
				}),
			};
		},
		[DELETE_POSITION]: (
			state: PositionState = initialState,
			action: deleteAction,
		) => {
			return {
				...state,
				position: state.position.filter((item) => item.id !== action.payload),
			};
		},
		[CHANGE_POSITION]: (
			state: PositionState = initialState,
			action: changeAction,
		) => {
			return {
				...state,
				position: state.position.map((item) =>
					item.id === action.payload.index
						? { ...item, position_max_num: action.payload.currentValue }
						: item,
				),
			};
		},
		[CHANGE_LEVEL]: (
			state: PositionState = initialState,
			action: levelAction,
		) => {
			return {
				...state,
				position: state.position.map((item) =>
					item.id === action.payload.index
						? { ...item, position_level: action.payload.positionLevel }
						: item,
				),
			};
		},
		[CHANGE_SEVERAL_POSITION]: (
			state: PositionState = initialState,
			action: severalPositionAction,
		) => {
			return {
				...state,
				position: state.position.map((item) =>
					item.id === action.payload.index
						? { ...item, position_name: action.payload.position }
						: item,
				),
			};
		},

		[CHANGE_LANGUAGE]: (
			state: PositionState = initialState,
			action: languageAction,
		) => {
			return {
				...state,
				position: state.position.map((item) =>
					item.id === action.payload.index
						? { ...item, position_language: action.payload.positionLanguage }
						: item,
				),
			};
		},
	},
	initialState,
);
