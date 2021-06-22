/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
/* eslint-disable no-param-reassign */
/* eslint-disable import/prefer-default-export */
import { createAction, handleActions } from 'redux-actions';

const CREATE_POSITION = 'modal/CREATE_POSITION';
const DELETE_POSITION = 'modal/DELETE_POSITION';
const CHANGE_POSITION = 'modal/CHANGE_POSITION';

export const createPosition = createAction(CREATE_POSITION);
export const deletePosition = createAction(DELETE_POSITION);
export const changePosition = createAction(CHANGE_POSITION);

type Action = ReturnType<typeof createPosition>;
type deleteAction = ReturnType<typeof deletePosition>;
type changeAction = ReturnType<typeof changePosition>;

interface Person {
	id: number;
	position: string;
	personNumber: string;
}
export interface PositionState {
	persons: Person[];
}
const initialState = {
	persons: [{ id: 0, position: '프론트엔드', personNumber: 0 }],
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
	},
	initialState,
);
