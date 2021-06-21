/* eslint-disable import/prefer-default-export */
import { createAction, handleActions } from 'redux-actions';

const CREATE_POSITION = 'modal/CREATE_POSITION';
const DELETE_POSITION = 'modal/DELETE_POSITION';

export const createPosition = createAction(CREATE_POSITION);
export const deletePosition = createAction(DELETE_POSITION);

type Action = ReturnType<typeof createPosition>;
type deleteAction = ReturnType<typeof deletePosition>;

interface Person {
	id: number;
	position: string;
	personNumber: string;
}
export interface PositionState {
	persons: Person[];
}
const initialState = {
	persons: [{ id: 0, position: '프론트엔드', personNumber: '3' }],
};

export const positionReducer = handleActions(
	{
		[CREATE_POSITION]: (
			state: PositionState = initialState,
			action: Action,
		) => {
			console.log(action.payload, state);
			return {
				...state,

				persons: state.persons.concat({
					id: action.payload,
					position: '프론트엔드',
					personNumber: '3',
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
	},
	initialState,
);
