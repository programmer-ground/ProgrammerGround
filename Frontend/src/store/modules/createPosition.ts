/* eslint-disable import/prefer-default-export */
import { createAction, handleActions } from 'redux-actions';

const CREATE_POSITION = 'modal/CREATE_POSITION';

export const createPosition = createAction(CREATE_POSITION);

type Action = ReturnType<typeof createPosition>;

interface Person {
	position: string;
	personNumber: string;
	id: number;
}
export interface PositionState {
	persons: Person[];
}
const initialState = {
	persons: [{ position: '프론트엔드', personNumber: '3', id: 1 }],
};

export const positionReducer = handleActions(
	{
		[CREATE_POSITION]: (
			state: PositionState = initialState,
			action: Action,
		) => {
			return { ...state, persons: action.payload };
		},
	},
	initialState,
);
