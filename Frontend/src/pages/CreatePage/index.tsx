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
import LoadingSpinner from '@src/components/loading';
import {
	createPosition,
	deletePosition,
	changePosition,
	changeLevel,
	changeSeveralPosition,
	changeLanguage,
} from '@src/store/modules/createPosition';
import { RootState } from '@src/store/modules/index';

import { createPlayground } from '@src/lib/axios/playground';
import * as StyledComponent from './style';

const CreatePage = () => {
	const [show, dispatch] = useShow();
	const { position } = useSelector((state: RootState) => state.positionReducer);
	const [totalPersonNumber, setTotalPerson] = useState(0);
	const [loading, setLoading] = useState(false);
	// 프로젝트 이름
	const [title, setTitle] = useState('');
	// 프로젝트 설명
	const [description, setDescription] = useState('');
	// 리더 포지션
	const [leaderPosition, setLeaderPosition] = useState('');

	const plusPosition = () => {
		dispatch(createPosition(position.length));
	};

	const deletePerson = () => {
		dispatch(deletePosition(position.length - 1));
	};

	const changeValue = (index: number, e: any) => {
		const obj = {
			index,
			currentValue: e.target.value === '' ? 0 : e.target.value,
		};
		dispatch(changePosition(obj));
	};

	const onSubmitHandler = (e) => {
		e.preventDefault();
		const obj = {
			title,
			description,
			max_user_num: position.reduce((acc, cur) => {
				return acc + parseInt(cur.position_max_num);
			}, 0),
			leader_position: leaderPosition,
		};
		const result = {
			...obj,
			position,
		};
		console.log(result);
		const create = async () => {
			setLoading(true);
			const data = await createPlayground(result);
			setLoading(false);
		};
		create();
	};

	const titleFunc = (e) => {
		setTitle(e.target.value);
	};

	const descriptionFunc = (a) => {
		setDescription(a());
	};

	const leaderFunc = (e) => {
		setLeaderPosition(e.target.value);
	};

	const changeFunc = (index: number, e: any) => {
		const obj = {
			index,
			positionLevel: e.target.value,
		};
		dispatch(changeLevel(obj));
	};

	const changeValue = (index: number, e: any) => {
		const obj = {
			index,
			currentValue: e.target.value === '' ? 0 : +e.target.value,
		};
		dispatch(changePosition(obj));
	};

	const severalPosition = (index: number, e: any) => {
		const obj = {
			index,
			position: e.target.value,
		};
		dispatch(changeSeveralPosition(obj));
	};

	const changeLanguageFunc = (index: number, e: any) => {
		const languageList = e.target.value.split(',');
		const obj = {
			index,
			positionLanguage: languageList.reduce((acc, cur) => {
				acc.push({ language_name: cur });
				return acc;
			}, []),
		};
		dispatch(changeLanguage(obj));
	};
	return (
		<StyledComponent.CreateContainer>
			<StyledComponent.CreateSubTitle>
				플레이 그라운드 생성 페이지
			</StyledComponent.CreateSubTitle>
			<form onSubmit={onSubmitHandler}>
				<StyledComponent.CreateNameDiv>
					<StyledComponent.ProjectLabel>
						프로젝트 이름
					</StyledComponent.ProjectLabel>
					<input
						type="text"
						onChange={titleFunc}
						placeholder="프로젝트 이름을 입력하세요"
					/>
				</StyledComponent.CreateNameDiv>
				<StyledComponent.ProjectLabel>
					프로젝트 내용
				</StyledComponent.ProjectLabel>
				<StyledComponent.CreateContent>
					<Editor onChange={descriptionFunc} defaultValue="Hello world!" />
				</StyledComponent.CreateContent>
				<StyledComponent.CreateLeaderPosition>
					<label htmlFor="positionId">리더포지션</label>
					<select
						name="position"
						id="positionId"
						defaultValue="Backend"
						onChange={leaderFunc}
					>
						<option value="BACKEND">BACKEND</option>
						<option value="FRONTEND">FRONTED</option>
						<option value="INFRA">INFRA</option>
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
				<StyledComponent.PersonNumberLength>
					최대
					{position.reduce((acc, cur) => {
						return acc + parseInt(cur.position_max_num);
					}, 0)}
					명
				</StyledComponent.PersonNumberLength>

				<StyledComponent.AttributeLabel>
					<label>포지션</label>
					<label>인원</label>
					<label>경력</label>
					<label>언어</label>
				</StyledComponent.AttributeLabel>
				{position.map((v, i) => {
					return (
						<StyledComponent.PersonContainer>
							<input
								type="text"
								name="position_name"
								onChange={(e) => severalPosition(i, e)}
								placeholder={v.position_name}
							/>

							<input
								name="position_max_num"
								onChange={(e) => changeValue(i, e)}
								placeholder={v.position_max_num}
							/>
							<input
								type="text"
								name="position_level"
								onChange={(e) => changeFunc(i, e)}
								placeholder="junior"
							/>
							<input
								type="text"
								name="position_language"
								onChange={(e) => changeLanguageFunc(i, e)}
								placeholder="react,typescript"
							/>
						</StyledComponent.PersonContainer>
					);
				})}
				<button type="submit">제출</button>
			</form>
			{loading ? <LoadingSpinner /> : ''}
		</StyledComponent.CreateContainer>
	);
};
export default CreatePage;
