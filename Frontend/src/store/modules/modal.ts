import { createAction, handleActions } from 'redux-actions';

const CHANGE_MODAL = 'modal/CHANGE_MODAL';
const PLAYGROUND_MODAL = 'modal/PLAYGROUND_MODAL';

export const changeModalMode = createAction(CHANGE_MODAL);
export const playgroundModalMode = createAction(PLAYGROUND_MODAL);

type Action = ReturnType<typeof changeModalMode>;
type playgroundAction = ReturnType<typeof playgroundModalMode>;

export interface ModalState {
	show: boolean;
	playgroundShow: boolean;
}
const initialState = {
	show: false,
	playgroundShow: false,
};

export const modalReducer = handleActions(
	{
		[CHANGE_MODAL]: (state: ModalState = initialState, action: Action) => {
			return { ...state, show: !state.show };
		},
		[PLAYGROUND_MODAL]: (
			state: ModalState = initialState,
			action: playgroundAction,
		) => {
			return { ...state, playgroundShow: !state.playgroundShow };
		},
	},

	initialState,
);
