import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import LoginPage from '@src/pages/LoginPage';
import PlayGroundPage from '@src/pages/PlayGroundPage';

const App = () => {
	return (
		<>
			<Router>
				<Switch>
					<Route exact path="/" component={LoginPage} />
					<Route exact path="/playground" component={PlayGroundPage} />
				</Switch>
			</Router>
		</>
	);
};
export default App;
