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
import { useHistory } from 'react-router-dom';
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
import Header from '@src/components/header';
import * as StyledComponent from './style';

const CreatePage = () => {
	const [show, dispatch] = useShow();
	const history = useHistory();
	const { position } = useSelector((state: RootState) => state.positionReducer);
	const [loading, setLoading] = useState(false);
	const [img, setImage] = useState(null);
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

	const fileChangedHandler = (e: any) => {
		setImage(e.target.files[0]);
	};

	const onSubmitHandler = (e) => {
		e.preventDefault();

		const formData = new FormData();

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

		formData.append(
			'info',
			new Blob([JSON.stringify(result)], { type: 'application/json' }),
		);
		formData.append('mainImg', img);

		if (!leaderPosition) {
			alert('리더의 포지션이 체크되지 않았습니다.');
			return;
		}

		for (let index = 0; index < result.position.length; index++) {
			if (!result.position[index].position_level) {
				alert('경력이 체크되지 않았습니다.');
				return;
			}
			if (result.position[index].position_name === '포지션 선택') {
				alert('포지션이 체크되지 않았습니다.');
				return;
			}
		}

		const create = async () => {
			try {
				setLoading(true);
				await createPlayground(formData, 'image');
				setLoading(false);
				history.push('/');
			} catch (error) {
				console.error(error);
			}
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
		<>
			<Header />

			<StyledComponent.CreateContainer>
				<StyledComponent.CreateSubTitle>
					<h1 className="create_project_title">
						* 프로젝트 생성
						<span className="create_comment_title">
							- 아래의 필수 입력사항을 모두 입력해주세요
						</span>
					</h1>
				</StyledComponent.CreateSubTitle>

				<form onSubmit={onSubmitHandler} encType="multipart/form-data">
					<StyledComponent.CreateNameDiv>
						<label htmlFor="projectNameId" className="project_label_name">
							프로젝트 이름
						</label>
						<input
							className="project_text_name"
							id="projectNameId"
							type="text"
							onChange={titleFunc}
							placeholder="최소 3자이상 입력하세요"
							required
						/>
					</StyledComponent.CreateNameDiv>
					<StyledComponent.CreateNameDiv>
						<label htmlFor="projectContentId" className="project_label_name">
							프로젝트 설명
						</label>
						<div className="project_editor_text">
							<Editor
								id="projectContentId"
								onChange={descriptionFunc}
								defaultValue=""
							/>
						</div>
					</StyledComponent.CreateNameDiv>
					<input id="image" type="file" onChange={fileChangedHandler} />
					<StyledComponent.CreateNameDiv>
						<label htmlFor="positionId" className="project_label_name">
							리더포지션
						</label>
						<select
							name="position"
							id="positionId"
							className="project_position"
							onChange={leaderFunc}
						>
							<option value="리더 포지션 선택">리더 포지션 선택</option>
							<option value="BACKEND">BACKEND</option>
							<option value="FRONTEND">FRONTEND</option>
							<option value="DESIGN">DESIGN</option>
							<option value="PLANNER">PLANNER</option>
							<option value="DEVOPS">DEVOPS</option>
						</select>
					</StyledComponent.CreateNameDiv>
					<StyledComponent.PersonNumberLength>
						<span className="person_max_text">인원수</span>
						<span className="person_number_info">
							최대
							<span className="person_number">
								{position.reduce((acc, cur) => {
									return acc + parseInt(cur.position_max_num);
								}, 0)}
							</span>
							<span className="person_unit">명</span>
						</span>
					</StyledComponent.PersonNumberLength>
					<StyledComponent.CreateLabel>
						<StyledComponent.addButton onClick={plusPosition}>
							추가하기
						</StyledComponent.addButton>
						<StyledComponent.removeButton onClick={deletePerson}>
							삭제하기
						</StyledComponent.removeButton>
					</StyledComponent.CreateLabel>

					<StyledComponent.AttributeLabel>
						<label htmlFor="position_id">포지션</label>
						<label htmlFor="position_num">인원</label>
						<label htmlFor="position_level">경력</label>
						<label htmlFor="position_language">언어</label>
					</StyledComponent.AttributeLabel>
					{position.map((v, i) => {
						return (
							<StyledComponent.PersonContainer>
								<select
									name="position_name"
									className="person_position"
									id="position_id"
									onChange={(e) => severalPosition(i, e)}
								>
									<option value="포지션 선택">포지션 선택</option>
									<option value="BACKEND">BACKEND</option>
									<option value="FRONTEND">FRONTEND</option>
									<option value="DESIGN">DESIGN</option>
									<option value="PLANNER">PLANNER</option>
									<option value="DEVOPS">DEVOPS</option>
								</select>

								<input
									id="position_num"
									className="project_text_name"
									name="position_max_num"
									onChange={(e) => changeValue(i, e)}
									placeholder={v.position_max_num}
									className="project_text_name"
									required
								/>
								<select
									name="position_level"
									id="position_level"
									onChange={(e) => changeFunc(i, e)}
									className="person_position"
								>
									<option value="경력 선택">경력 선택</option>
									<option value="JUNIOR">JUNIOR</option>
									<option value="SENIOR">SENIOR</option>
									<option value="STUDENT">STUDENT</option>
									<option value="NEWCOMER">NEWCOMER</option>
								</select>

								<input
									type="text"
									id="position_language"
									name="position_language"
									className="project_text_name"
									onChange={(e) => changeLanguageFunc(i, e)}
									placeholder="REACT"
									required
								/>
							</StyledComponent.PersonContainer>
						);
					})}
					<StyledComponent.SubmitButton>생성</StyledComponent.SubmitButton>
				</form>
				{loading ? <LoadingSpinner /> : ''}
			</StyledComponent.CreateContainer>
		</>
	);
};
export default CreatePage;
