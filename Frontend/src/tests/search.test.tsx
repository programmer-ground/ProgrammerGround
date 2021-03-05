import React from 'react';
import { render } from 'react-dom';
import SearchBar from '../components/searchBar';

it('Header Component - snapshot', () => {
	const el = document.createElement('div');
	render(<SearchBar />, el);
	expect(el.innerHTML).toMatchSnapshot();
});
