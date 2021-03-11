import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@src/store/modules/index';

const useShow = () => {
	const { show } = useSelector((state: RootState) => state.modalReducer);
	const dispatch = useDispatch();
	return [show, dispatch] as const;
};

export default useShow;
