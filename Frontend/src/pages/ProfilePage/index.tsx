import React from 'react';
import Header from '@src/components/header';
import { useLocation } from 'react-router-dom';
import * as StyledComponent from './style';

const ProfilePage = () => {
	const location = useLocation();
	const user = (location.state as any).userData;
	console.log(user);
	return (
		<StyledComponent.ProfileContainer>
			<Header />
			<StyledComponent.ProfileHeader>
				<StyledComponent.ProfileTitle>프로필</StyledComponent.ProfileTitle>
				<StyledComponent.ProfileInfo>
					<StyledComponent.ProfileLeftInfo>
						<StyledComponent.ProfileLeftInfoData>
							<img
								src={user.profile_img}
								alt="프로필 이미지"
								width="117px"
								height="117px"
							/>
							<h3>{user.oauth_name}</h3>
						</StyledComponent.ProfileLeftInfoData>
					</StyledComponent.ProfileLeftInfo>
					<StyledComponent.ProfileGeneralInfo>
						<StyledComponent.ProfileGeneralTitle>
							General Info
						</StyledComponent.ProfileGeneralTitle>
						<StyledComponent.ProfileGeneralAttribute>
							<StyledComponent.ProfileGeneralName>
								Name
							</StyledComponent.ProfileGeneralName>
							<StyledComponent.ProfileGeneralValue>
								{user.oauth_name}
							</StyledComponent.ProfileGeneralValue>
						</StyledComponent.ProfileGeneralAttribute>
						<StyledComponent.ProfileGeneralAttribute>
							<StyledComponent.ProfileGeneralName>
								GitHub
							</StyledComponent.ProfileGeneralName>
							<StyledComponent.ProfileGeneralValue>
								{user.github_page}
							</StyledComponent.ProfileGeneralValue>
						</StyledComponent.ProfileGeneralAttribute>
					</StyledComponent.ProfileGeneralInfo>
				</StyledComponent.ProfileInfo>
			</StyledComponent.ProfileHeader>
		</StyledComponent.ProfileContainer>
	);
};

export default ProfilePage;
