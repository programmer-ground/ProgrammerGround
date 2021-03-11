import { combineReducers } from 'redux';
import { modalReducer, ModalState } from '@src/store/modules/modal';

export interface RootState {
	modalReducer: ModalState;
}

export default combineReducers({
	modalReducer,
});
