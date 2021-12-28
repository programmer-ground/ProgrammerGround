import React from 'react';
import { render } from 'react-dom';
import Header from '../components/header';

it('Header Component - snapshot', () => {
	const el = document.createElement('div');
	render(<Header />, el);
	expect(el.innerHTML).toMatchSnapshot();
});
