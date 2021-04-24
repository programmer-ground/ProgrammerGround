import { combineReducers } from 'redux';
import { modalReducer, ModalState } from '@src/store/modules/modal';
import {
	positionReducer,
	PositionState,
} from '@src/store/modules/createPosition';

export interface RootState {
	modalReducer: ModalState;
	positionReducer: PositionState;
}

export default combineReducers({
	modalReducer,
	positionReducer,
});
