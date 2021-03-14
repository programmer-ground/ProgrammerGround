import { createAction, handleActions } from 'redux-actions';

const CHANGE_MODAL = 'modal/CHANGE_MODAL';

export const changeModalMode = createAction(CHANGE_MODAL);

type Action = ReturnType<typeof changeModalMode>;

export interface ModalState {
	show: boolean;
}
const initialState = {
	show: false,
};

export const modalReducer = handleActions(
	{
		[CHANGE_MODAL]: (state: ModalState = initialState, action: Action) => {
			return { ...state, show: !state.show };
		},
	},
	initialState,
);
