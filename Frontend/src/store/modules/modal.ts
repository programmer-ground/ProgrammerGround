import { createAction, handleActions } from 'redux-actions';

const CHANGE_MODAL = 'modal/CHANGE_MODAL';
const PLAYGROUND_MODAL = 'modal/PLAYGROUND_MODAL';
const REPOSITORY_MODAL = 'modal/REPOSITORY_MODAL';

export const changeModalMode = createAction(CHANGE_MODAL);
export const playgroundModalMode = createAction(PLAYGROUND_MODAL);
export const repositoryModalMode = createAction(REPOSITORY_MODAL);

type Action = ReturnType<typeof changeModalMode>;
type playgroundAction = ReturnType<typeof playgroundModalMode>;
type repositoryAction = ReturnType<typeof repositoryModalMode>;

export interface ModalState {
	show: boolean;
	playgroundShow: boolean;
	repositoryShow: boolean;
}

const initialState = {
	show: false,
	playgroundShow: false,
	repositoryShow: false
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
		[REPOSITORY_MODAL]: (
			state: ModalState = initialState,
			action: repositoryAction, 
		) => {
			return { ...state, repositoryShow: !state.repositoryShow };
		}
	},

	initialState,
);
