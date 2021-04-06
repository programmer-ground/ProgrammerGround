/* eslint-disable jsx-a11y/label-has-associated-control */
import React from 'react';
import Editor from '@src/components/Common/editor/';
import * as StyledComponent from './style';

const CreatePage = () => {
	return (
		<StyledComponent.CreateContainer>
			<StyledComponent.CreateSubTitle>
				플레이 그라운드 생성 페이지
			</StyledComponent.CreateSubTitle>
			<StyledComponent.CreateNameDiv>
				<label>프로젝트 이름: </label>
				<input type="text" placeholder="프로젝트 제목을 입력하세요" />
			</StyledComponent.CreateNameDiv>
			<StyledComponent.CreateNameDiv>
				<label>프로젝트 내용</label>
				<StyledComponent.CreateContent>
					<StyledComponent.CreateEditorMenu>
						<Editor data="bold" />
						<Editor data="italic" />
						<Editor data="text" />
						<Editor data="" />
						<Editor data="" />
					</StyledComponent.CreateEditorMenu>
				</StyledComponent.CreateContent>
			</StyledComponent.CreateNameDiv>
		</StyledComponent.CreateContainer>
	);
};
export default CreatePage;
