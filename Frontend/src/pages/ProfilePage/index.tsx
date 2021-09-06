import React, {useState} from 'react';
import Header from '@src/components/header';
import { useLocation, useHistory } from 'react-router-dom';
import * as StyledComponent from './style';
import {patchOneUser, getOneUser} from '@src/lib/axios/playground';

const ProfilePage = () => {
	const location = useLocation();
	const user = (location.state as any).userData;
	const history = useHistory();
	const [userName, setUserName] = useState('');
	const [edit, setEdit] = useState(false);
  const ProfileEditHandler = () => {
	   setEdit(true);
	}

	const ProfileSaveHandler = async () => {
		 if(userName.length > 5) {
			 alert('글자 수가 너무 깁니다. 다시 입력해주세요');
			 return;
		 }
		 const response = await patchOneUser(userName, 'profile');
		 setEdit(false);
		 const userData = await getOneUser();
		 history.push({
			pathname: '/profile',
			state: { userData },
		});
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
