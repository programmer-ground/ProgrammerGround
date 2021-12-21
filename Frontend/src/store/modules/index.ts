import { combineReducers } from 'redux';
import { modalReducer, ModalState } from '@src/store/modules/modal';
import {
	positionReducer,
	PositionState,
} from '@src/store/modules/createPosition';

import {
	playgroundReducer,
	PlaygroundState,
} from '@src/store/modules/Playground';

export interface RootState {
	modalReducer: ModalState;
	positionReducer: PositionState;
	playgroundReducer: PlaygroundState;
}

export default combineReducers({
	modalReducer,
	positionReducer,
	playgroundReducer,
});
