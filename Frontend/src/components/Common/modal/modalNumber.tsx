/* eslint-disable no-shadow */
/* eslint-disable func-names */
/* eslint-disable radix */
/* eslint-disable react/destructuring-assignment */
import React, { useState, useEffect, useRef } from 'react';

const ModalNumberInput = (props: {
	placeholder: string;
	setMaxPersonNumber: any;
}) => {
	const [value, setValue] = useState(0);
	const ref = useRef(null);

	const onChange = (e: any) => {
		setValue(e.target.value);
	};

	useEffect(() => {
		const getData = document.querySelectorAll('.input-Ref input:nth-child(2n)');
		let sum = 0;
		getData.forEach(function (item: any, index: number) {
			sum += +item.getAttribute('value');
		});
		props.setMaxPersonNumber(sum);
	}, []);
	return (
		<input
			type="text"
			name="number"
			placeholder={props.placeholder}
			ref={ref}
			onChange={onChange}
			value={value}
		/>
	);
};

export default ModalNumberInput;
