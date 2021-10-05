// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck
import React, { useState, useEffect } from 'react';
import * as StyledComponent from './style';
import './headerImage.scss';
import { useHistory } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { getOneUser } from '@src/lib/axios/playground';
import ApplyList from '@src/components/applyList';
import ResultList from '@src/components/resultList';
import WaitingList from '@src/components/waitingList';
import { RootState } from '@src/store/modules/index';
import useShow from '@src/hooks/useShow';
import { repositoryModalMode } from '@src/store/modules/modal';

const Header = () => {
	const [isAlarm, setAlarm] = useState(false);
	const [isUser, setUser] = useState(false);
	const [info, setInfo] = useState(false);
	const [menu, setMenu] = useState(1);
	const [show, dispatch] = useShow();

	const history = useHistory();
	const { repositoryShow } = useSelector((state: RootState) => state.modalReducer);

  const textArray = ["신청목록", "결과목록", "대기목록"];

	const userClickHandler = (e: React<MouseEvent>) => {
		setUser(!isUser);
		if (isAlarm) setAlarm(!isAlarm);
	};
	const alarmClickHandler = (e: React<MouseEvent>) => {
		setAlarm(!isAlarm);
		if (isUser) setUser(!isUser);
	};

	const onClickInfoHandler = (e:React<MouseEvent>) => {
		setInfo(!info);
	};

	const onClickCloseHandler = (e:React<MouseEvent>) => {
		setInfo(!info);
	};

	const onClickMakeRepoHandler = (e:React<MouseEvent>) => {
		dispatch(repositoryModalMode(!repositoryShow));
	}

	const onLogout = (e) => {
		document.cookie = `access_token=; Max-Age=0`;
		document.cookie = `refresh_token=; Max-Age=0`;
		history.push('/login');
	};
	const goProfilePage = async (e) => {
		const userData = await getOneUser();
		history.push({
			pathname: '/profile',
			state: { userData },
		});
	};

	const changeScreen = (e:any, menuNumber: number) => {
		  setMenu(menuNumber);
	}

	return (
		<>
			<StyledComponent.GlobalStyle />
			<StyledComponent.HeaderContainer>
				<a href="http://localhost:3000/">
					<StyledComponent.HeaderImg />
				</a>
				<StyledComponent.PlaygroundSearchSection />
				<StyledComponent.HeaderMenuContainer>
					<button
						type="button"
						className="user_icon"
						onClick={userClickHandler}
					/>
					{isUser && (
						<StyledComponent.UserMenu>
							<a onClick={(e) => goProfilePage()}>
								<i className="user_profile" />
								<span>나의 프로필</span>
							</a>
							<a href="http://localhost:3000/playground">
								<i className="user_room" />
								<span>방 생성</span>
							</a>
							<a onClick={onLogout}>
								<i className="user_logout" />
								<span>로그아웃</span>
							</a>
						</StyledComponent.UserMenu>
					)}
					<button
						type="button"
						className="alarm_icon"
						onClick={alarmClickHandler}
					/>
					{isAlarm && (
						<StyledComponent.UserMenu>
							<button onClick={onClickMakeRepoHandler}>
								<i className="repo_icon" />
								<span>레포 생성</span>
							</button>
							<button onClick={onClickInfoHandler}>
								<i className="my_alarm_icon" />
								<span>내 알림</span>
							</button>
						</StyledComponent.UserMenu>
					)}
				</StyledComponent.HeaderMenuContainer>
			</StyledComponent.HeaderContainer>
			{info && (
				<StyledComponent.InfoMenu>
					<StyledComponent.InfoMenuList>
						{textArray.map((v, i) => {
							return (
								<StyledComponent.InfoMenuLink key={i} selected={menu===i+1? true: ''} onClick={(e) => changeScreen(e, i+1)}>{v}</StyledComponent.InfoMenuLink>
							)
						})}
					</StyledComponent.InfoMenuList>
					<StyledComponent.InfoTitleContainer>
						<StyledComponent.InfoTitleName>
							My Alarm Info
						</StyledComponent.InfoTitleName>
						<StyledComponent.InfoTitleCloseButton onClick={onClickCloseHandler}>
							닫기
						</StyledComponent.InfoTitleCloseButton>
					</StyledComponent.InfoTitleContainer>
					<StyledComponent.InfoBodyContainer>
					{menu === 1 && <ApplyList/>}
				  {menu === 2 && <ResultList menu={menu}/>}
					{menu === 3 && <WaitingList menu={menu}/>}
					</StyledComponent.InfoBodyContainer>

				</StyledComponent.InfoMenu>
			)}
		</>
	);
};

export default Header;
