/* eslint-disable react/jsx-pascal-case */
/* eslint-disable react/jsx-indent-props */
/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
/* eslint-disable jsx-a11y/label-has-associated-control */

import HashTag from '@src/components/hashTag';
import React, { useState } from 'react';
import Editor from 'rich-markdown-editor';
import useShow from '@src/hooks/useShow';
import { useDispatch, useSelector } from 'react-redux';

import {
	createPosition,
	deletePosition,
	changePosition,
} from '@src/store/modules/createPosition';
import { RootState } from '@src/store/modules/index';

import * as StyledComponent from './style';

const CreatePage = () => {
	const [show, dispatch] = useShow();
	const { persons } = useSelector((state: RootState) => state.positionReducer);
	const changeFunc = (a) => {
		console.log(a());
	};

	const plusPosition = () => {
		dispatch(createPosition(persons.length));
	};

	const deletePerson = () => {
		dispatch(deletePosition(persons.length - 1));
	};

	const changeValue = (index: number, e: any) => {
		const obj = {
			index,
			currentValue: e.target.value === '' ? 0 : e.target.value,
		};
		dispatch(changePosition(obj));
	};
	return (
		<StyledComponent.CreateContainer>
			<StyledComponent.CreateSubTitle>
				플레이 그라운드 생성 페이지
			</StyledComponent.CreateSubTitle>
			<StyledComponent.CreateNameDiv>
				<StyledComponent.ProjectLabel>
					프로젝트 이름
				</StyledComponent.ProjectLabel>
				<input type="text" placeholder="프로젝트 이름을 입력하세요" />
			</StyledComponent.CreateNameDiv>
			<StyledComponent.ProjectLabel>프로젝트 내용</StyledComponent.ProjectLabel>
			<StyledComponent.CreateContent>
				<Editor onChange={changeFunc} defaultValue="Hello world!" />
			</StyledComponent.CreateContent>
			<StyledComponent.CreateLeaderPosition>
				<label>리더포지션</label>
				<select name="position">
					<option value="Backend">Backend</option>
					<option value="Fronted">Frontend</option>
					<option value="Infra">Infra</option>
					<option value="UI/UX">UI/UX</option>
					<option value="디자이너">디자이너</option>
				</select>
			</StyledComponent.CreateLeaderPosition>
			<StyledComponent.CreateLabel>
				<StyledComponent.addButton onClick={plusPosition}>
					추가하기
				</StyledComponent.addButton>
				<StyledComponent.removeButton onClick={deletePerson}>
					삭제하기
				</StyledComponent.removeButton>
			</StyledComponent.CreateLabel>
			최대
			{persons.reduce((acc, cur) => {
				return acc + parseInt(cur.personNumber);
			}, 0)}
			명
			<StyledComponent.AttributeLabel>
				<label>포지션</label>
				<label>인원</label>
				<label>경력</label>
				<label>언어</label>
			</StyledComponent.AttributeLabel>
			{persons.map((v, i) => {
				return (
					<StyledComponent.PersonContainer>
						<input type="text" name="position_name" placeholder={v.position} />

						<input
							name="position_max_num"
							onChange={(e) => changeValue(i, e)}
							placeholder={v.personNumber}
						/>
						<input type="text" name="position_level" placeholder="junior" />
						<input type="text" name="position_language" placeholder="react" />
					</StyledComponent.PersonContainer>
				);
			})}
		</StyledComponent.CreateContainer>
	);
};
export default CreatePage;
