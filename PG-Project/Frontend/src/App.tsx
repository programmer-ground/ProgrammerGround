import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import LoginPage from '@src/pages/LoginPage';
import PlayGroundPage from '@src/pages/PlayGroundPage';
import CreatePage from '@src/pages/CreatePage';
import PlaygroundIdPage from '@src/pages/PlaygroundIdPage';
import ProfilePage from '@src/pages/ProfilePage';
import { GlobalStyle } from './Global';
import ModalComponent from '@src/components/Common/modalComponent';
import { useSelector } from 'react-redux';
import { RootState } from '@src/store/modules/index';

const App = () => {
	const { repositoryShow } = useSelector((state: RootState) => state.modalReducer);

	return (
		<>
			<GlobalStyle />
			<Router>
				<Switch>
					<Route exact path="/login" component={LoginPage} />
					<Route exact path="/" component={PlayGroundPage} />
					<Route exact path="/playground" component={CreatePage} />
					<Route exact path="/playground/:id" component={PlaygroundIdPage} />
					<Route exact path="/profile" component={ProfilePage} />
				</Switch>
			</Router>
			{repositoryShow && 	<ModalComponent title="레포지토리 생성"></ModalComponent> }
		
		</>
	);
};
export default App;
