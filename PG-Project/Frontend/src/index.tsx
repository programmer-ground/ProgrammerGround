/* eslint-disable import/order */
/* eslint-disable react/jsx-filename-extension */
/* eslint-disable import/no-unresolved */
/* eslint-disable no-use-before-define */
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './root.css';
import { Provider } from 'react-redux';
import store from '@src/store';

ReactDOM.render(
	<Provider store={store}>
		<App />
	</Provider>,
	document.getElementById('root'),
);
