import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import LoginPage from '@src/pages/LoginPage';
import PlayGroundPage from '@src/pages/PlayGroundPage';
import ModalWrapper from '@src/components/Common/modal';
import { GlobalStyle } from './Global';

const App = () => {
	return (
		<>
			<GlobalStyle />
			<Router>
				<Switch>
					<Route exact path="/" component={LoginPage} />
					<Route exact path="/playground" component={PlayGroundPage} />
				</Switch>
			</Router>
			<ModalWrapper />
		</>
	);
};
export default App;
