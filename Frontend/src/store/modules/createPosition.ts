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
	position: string;
	personNumber: string;
	positionLevel: string;
	positionLanguage: [];
}
export interface PositionState {
	persons: Person[];
}
const initialState = {
	persons: [
		{
			id: 0,
			position: '프론트엔드',
			personNumber: 0,
			positionLevel: 'junior',
			positionLanguage: [],
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

				persons: state.persons.concat({
					id: action.payload,
					position: '프론트엔드',
					personNumber: 0,
					positionLevel: 'junior',
					positionLanguage: [],
				}),
			};
		},
		[DELETE_POSITION]: (
			state: PositionState = initialState,
			action: deleteAction,
		) => {
			return {
				...state,
				persons: state.persons.filter((item) => item.id !== action.payload),
			};
		},
		[CHANGE_POSITION]: (
			state: PositionState = initialState,
			action: changeAction,
		) => {
			return {
				...state,
				persons: state.persons.map((item) =>
					item.id === action.payload.index
						? { ...item, personNumber: action.payload.currentValue }
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
				persons: state.persons.map((item) =>
					item.id === action.payload.index
						? { ...item, positionLevel: action.payload.positionLevel }
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
				persons: state.persons.map((item) =>
					item.id === action.payload.index
						? { ...item, position: action.payload.position }
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
				persons: state.persons.map((item) =>
					item.id === action.payload.index
						? { ...item, positionLanguage: action.payload.positionLanguage }
						: item,
				),
			};
		},
	},
	initialState,
);
