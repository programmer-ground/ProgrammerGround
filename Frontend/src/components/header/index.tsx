// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck
import React, { useState } from 'react';
import * as StyledComponent from './style';
import './headerImage.scss';
import SearchBar from '@src/components/searchBar/index';

const Header = () => {
	const [isAlarm, setAlarm] = useState(false);
	const [isUser, setUser] = useState(false);
	const userClickHandler = (e: any) => {
		setUser(!isUser);
	};
	const alarmClickHandler = (e: any) => {
		console.log('alarm');
	};

	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.HeaderContainer>
				<a href="http://localhost:3000/">
					<StyledComponent.HeaderImg />
				</a>
				<StyledComponent.PlaygroundSearchSection>
					<SearchBar />
				</StyledComponent.PlaygroundSearchSection>
				<StyledComponent.HeaderMenuContainer>
					<button
						type="button"
						className="user_icon"
						onClick={userClickHandler}
					/>
					{isUser && (
						<StyledComponent.UserMenu>
							<StyledComponent.UserItem>
								<a href="#">
									<i className="user_profile" />
									<span>나의 프로필</span>
								</a>
							</StyledComponent.UserItem>
							<StyledComponent.UserItem>
								<a href="http://localhost:3000/playground">
									<i className="user_room" />
									<span>방 생성</span>
								</a>
							</StyledComponent.UserItem>
							<StyledComponent.UserItem>
								<a href="#">
									<i className="user_logout" />
									<span>로그아웃</span>
								</a>
							</StyledComponent.UserItem>
						</StyledComponent.UserMenu>
					)}
					<button
						type="button"
						className="alarm_icon"
						onClick={alarmClickHandler}
					/>
				</StyledComponent.HeaderMenuContainer>
			</StyledComponent.HeaderContainer>
		</>
	);
};

export default Header;
