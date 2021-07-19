import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import LoginPage from '@src/pages/LoginPage';
import PlayGroundPage from '@src/pages/PlayGroundPage';
import CreatePage from '@src/pages/CreatePage';
import PlaygroundIdPage from '@src/pages/PlaygroundIdPage';
import ModalWrapper from '@src/components/Common/modal';
import { GlobalStyle } from './Global';

const App = () => {
	return (
		<>
			<GlobalStyle />
			<Router>
				<Switch>
					<Route exact path="/login" component={LoginPage} />
					<Route exact path="/" component={PlayGroundPage} />
					<Route exact path="/playground" component={CreatePage} />
					<Route exact path="/playground/:id" component={PlaygroundIdPage} />
				</Switch>
			</Router>
			<ModalWrapper />
		</>
	);
};
export default App;
