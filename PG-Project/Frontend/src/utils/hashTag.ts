/* eslint-disable no-param-reassign */

import { MutableRefObject } from 'react';

// eslint-disable-next-line import/prefer-default-export
export const checkValue = (
	str: string,
	placeholderSection: MutableRefObject<HTMLElement>,
) => {
	if (str !== '') {
		placeholderSection.current.style.display = 'none';
	}
};
