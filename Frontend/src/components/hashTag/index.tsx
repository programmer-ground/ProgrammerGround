/* eslint-disable react/jsx-pascal-case */

import React, { useEffect, useRef } from 'react';
import { checkValue } from '@src/utils/hashTag';
import * as StyledComponent from './style';
import './tag.scss';

const HashTag = () => {
	const tagSection = useRef(null);
	const placeholderSection = useRef(null);

	const inputFunc = (e: React.ChangeEvent<HTMLElement>) => {
		let str = e.target.innerText;
		checkValue(str, placeholderSection);

		str = str
			.replace(/^(@\S+)/g, "<span class='mention'>$1</span>")
			.replace(/\n/g, '&nbsp;\n<br />')
			.replace(/^(@\S+)/g, "<span class='mention'>$1</span>")
			.replace(/([^\&])(@\S+)/g, "$1<span class='mention'>$2</span>");

		const sel = window.getSelection();
		const range = sel.getRangeAt(0);

		sel.removeAllRanges();
		sel.addRange(range);

		tagSection.current.innerHTML = str;
	};

	const keyDownFunc = (e: React.ChangeEvent<HTMLElement>) => {
		console.log('keydown');
	};

	useEffect(() => {
		tagSection.current.addEventListener('input', inputFunc);
		tagSection.current.addEventListener('keydown', keyDownFunc);
		return () => {
			tagSection.current.removeListener('input', inputFunc);
			tagSection.current.removeListener('keydown', keyDownFunc);
		};
	}, [tagSection]);

	return (
		<>
			<StyledComponent.Hash>
				<StyledComponent.EditorBody>
					<StyledComponent.Placeholder ref={placeholderSection}>
						내용을 입력하세요 (@멘션)
					</StyledComponent.Placeholder>
					<StyledComponent.Inp ref={tagSection} />
					<StyledComponent.outp />
				</StyledComponent.EditorBody>
			</StyledComponent.Hash>
		</>
	);
};

export default HashTag;
