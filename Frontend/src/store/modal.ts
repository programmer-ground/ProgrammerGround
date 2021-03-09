const SHOW_MODAL = 'modal/SHOW_MODAL';
const DROP_MODAL = 'modal/DROP_MODAL';

export const showModal = (element: HTMLElement) => ({
	type: SHOW_MODAL,
	payload: element,
});
export const dropModal = () => ({ type: DROP_MODAL });

const initialState = {
	show: false,
	element: null,
};

export default function snackbar(state = initialState, action: any) {
	switch (action.type) {
		case SHOW_MODAL:
			document.querySelector('body').style.overflow = 'hidden';
			return { ...state, show: true, element: action.payload };
		case DROP_MODAL:
			document.querySelector('body').removeAttribute('style');
			return { ...state, show: false };
		default:
			return state;
	}
}
