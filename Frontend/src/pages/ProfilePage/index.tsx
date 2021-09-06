import React, {useState} from 'react';
import Header from '@src/components/header';
import { useLocation } from 'react-router-dom';
import * as StyledComponent from './style';
import {patchOneUser} from '@src/lib/axios/playground';

const ProfilePage = () => {
	const location = useLocation();
	const user = (location.state as any).userData;
	const [userName, setUserName] = useState('');
	const [edit, setEdit] = useState(false);
  const ProfileEditHandler = () => {
	   setEdit(true);
	}

	const ProfileSaveHandler = async () => {
		 if(userName.length >= 1 && userName.length <= 5) {
			 alert('글자 수가 너무 짧습니다. 다시 입력해주세요');
			 return;
		 }
		 const response = await patchOneUser(userName, 'profile');
		 setEdit(false);
	}

	const ProfileInput = (e: any) => {
		setUserName(e.target.value);
	}
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
							<h3>{user.user_name}</h3>
						</StyledComponent.ProfileLeftInfoData>
					</StyledComponent.ProfileLeftInfo>
					<StyledComponent.ProfileGeneralInfo>
						<StyledComponent.ProfileGeneralTitle>
							General Info
						</StyledComponent.ProfileGeneralTitle>
						{edit?<StyledComponent.ProfileGeneralAttribute>

							<StyledComponent.ProfileGeneralName>
								Name
							</StyledComponent.ProfileGeneralName>
							<StyledComponent.ProfileInput onChange={ProfileInput}>
							</StyledComponent.ProfileInput>
							<StyledComponent.ProfileButton onClick={ProfileSaveHandler}>Save</StyledComponent.ProfileButton>
						</StyledComponent.ProfileGeneralAttribute> :
						
						<StyledComponent.ProfileGeneralAttribute>
							<StyledComponent.ProfileGeneralName>
								Name
							</StyledComponent.ProfileGeneralName>
							<StyledComponent.ProfileGeneralValue>
								{user.user_name}
							</StyledComponent.ProfileGeneralValue>
							<StyledComponent.ProfileButton onClick={ProfileEditHandler}>Edit</StyledComponent.ProfileButton>
						</StyledComponent.ProfileGeneralAttribute> 
            }	
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
