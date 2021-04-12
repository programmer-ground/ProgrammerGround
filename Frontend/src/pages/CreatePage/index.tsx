/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
/* eslint-disable jsx-a11y/label-has-associated-control */

import React from 'react';
import EdiTor from '@src/components/Common/editor/';
import Editor from 'rich-markdown-editor';
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
					{/* <StyledComponent.CreateEditorMenu>
						<EdiTor data="bold" />
						<EdiTor data="italic" />
						<EdiTor data="text" />
						<EdiTor data="" />
						<EdiTor data="" />
					</StyledComponent.CreateEditorMenu> */}
					<Editor defaultValue="Hello world!" />
				</StyledComponent.CreateContent>
				<StyledComponent.CreateImageButton>
					Image upload
				</StyledComponent.CreateImageButton>
			</StyledComponent.CreateNameDiv>
		</StyledComponent.CreateContainer>
	);
};
export default CreatePage;
