// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck
import React, { useState, useEffect } from 'react';
import * as StyledComponent from './style';
import './headerImage.scss';
import useCookie from '@src/hooks/useCookie';
import { useHistory } from 'react-router-dom';
import { getOneUser, getNoticeLeaderList } from '@src/lib/axios/playground';

const Header = () => {
	const [isAlarm, setAlarm] = useState(false);
	const [isUser, setUser] = useState(false);
	const [info, setInfo] = useState(false);
	const [noticeItem, setNoticeItem] = useState([]);
	const history = useHistory();

	const userClickHandler = (e: any) => {
		setUser(!isUser);
		if (isAlarm) setAlarm(!isAlarm);
	};
	const alarmClickHandler = (e: any) => {
		setAlarm(!isAlarm);
		if (isUser) setUser(!isUser);
	};

	const onClickInfoHandler = (e: any) => {
		setInfo(!info);
	};

	const onClickCloseHandler = (e: any) => {
		setInfo(!info);
	};

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

	useEffect(() => {
		const getData = async () => {
			const noticeData = await getNoticeLeaderList();
			setNoticeItem(noticeData.user_notice);
		}
		getData();
	}, []);
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
							<a href="#">
								<i className="repo_icon" />
								<span>레포 생성</span>
							</a>
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
					<StyledComponent.InfoTitleContainer>
						<StyledComponent.InfoTitleName>
							My Alarm Info
						</StyledComponent.InfoTitleName>
						<StyledComponent.InfoTitleCloseButton onClick={onClickCloseHandler}>
							닫기
						</StyledComponent.InfoTitleCloseButton>
					</StyledComponent.InfoTitleContainer>
					<StyledComponent.InfoBodyContainer>
					{noticeItem.map((v,i)=> {
							return (
								<StyledComponent.InfoBodyContent key={i}>
									<StyledComponent.InfoBodyTitle>{v.playground_title}</StyledComponent.InfoBodyTitle>
									<StyledComponent.InfoBodyAuthor>
										 <StyledComponent.InfoAuthorName>
											  <StyledComponent.InfoNameEmphasis>{v.user_name}</StyledComponent.InfoNameEmphasis>
											님</StyledComponent.InfoAuthorName>
										 <StyledComponent.InfoAuthorPosition>{v.position}</StyledComponent.InfoAuthorPosition>
									</StyledComponent.InfoBodyAuthor>
							  </StyledComponent.InfoBodyContent>
							)
					})}
					</StyledComponent.InfoBodyContainer>

				</StyledComponent.InfoMenu>
			)}
		</>
	);
};

export default Header;
