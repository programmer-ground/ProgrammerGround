/* eslint-disable react/jsx-pascal-case */
/* eslint-disable react/jsx-indent-props */
/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
/* eslint-disable jsx-a11y/label-has-associated-control */

import React from 'react';
import Editor from 'rich-markdown-editor';
import * as StyledComponent from './style';

const CreatePage = () => {
	const changeFunc = (a) => {
		console.log(a);
		console.log(a());
	};
	return (
		<StyledComponent.CreateContainer>
			<StyledComponent.CreateSubTitle>
				플레이 그라운드 생성 페이지
			</StyledComponent.CreateSubTitle>
			<StyledComponent.CreateNameDiv>
				<label htmlFor="projectId">프로젝트 이름 </label>
				<input
					type="text"
					placeholder="프로젝트 제목을 입력하세요"
					id="projectId"
				/>
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
					<Editor onChange={changeFunc} defaultValue="Hello world!" />
				</StyledComponent.CreateContent>
				<StyledComponent.CreateImageButton>
					Image upload
				</StyledComponent.CreateImageButton>
				<StyledComponent.CreateLeaderPosition>
					<label>Leader 포지션</label>
					<select name="position">
						<option value="Backend">Backend</option>
						<option value="Fronted">Frontend</option>
						<option value="Infra">Infra</option>
						<option value="UI/UX">UI/UX</option>
						<option value="디자이너">디자이너</option>
					</select>
				</StyledComponent.CreateLeaderPosition>
				<StyledComponent.CreateLabel>
					<StyledComponent.PersonNumber>최대 0 명</StyledComponent.PersonNumber>
					<StyledComponent.addButton>추가하기</StyledComponent.addButton>
					<StyledComponent.removeButton>삭제하기</StyledComponent.removeButton>
				</StyledComponent.CreateLabel>
				<StyledComponent.AttributeLabel>
					<label>포지션</label>
					<label>인원</label>
					<label>경력</label>
					<label>언어</label>
				</StyledComponent.AttributeLabel>
			</StyledComponent.CreateNameDiv>
		</StyledComponent.CreateContainer>
	);
};
export default CreatePage;
