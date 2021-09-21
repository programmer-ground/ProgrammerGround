// @ts-nocheck
import React, {useState, useEffect} from 'react';
import { useHistory } from 'react-router-dom';
import * as StyledComponent from './style';
import {getPositionList} from '@src/lib/axios/playground';

interface playground {
	playgroundTitle: string;
	src: string;
	createDate: string;
	data: any;
	positionList: any;
	id: number;
}
const OtherUI = ({
	playgroundTitle,
	src,
	createDate,
	data,
	positionList,
	id,
}: playground) => {
	const colors = ['red', 'yellow', 'blue', 'green', 'purple', 'pink'];
	const history = useHistory();
	const [isShow, setIsShow] = useState(false);
	const [list, setPositionList] = useState([]);
	const [issue, setIssueList] = useState([]);

	const applyHandler = async () => {
		setIsShow(true);
	}

	const onClose = () => {
		setIsShow(false);
	}

	const handleCheckboxChange = ( positionList:any, list: any) => {
	  const newItem = list.map((v:boolean) => !v);
		setPositionList({
			positionList,
			checkedList: newItem
		});
	} 

	useEffect(()=> {
    const getData = async () => {
			const data = await getPositionList(id);
			setPositionList({
				positionList: data.playground_positions,
				checkedList: new Array(data.playground_positions.length).fill(false)
			})
		}
		getData();
	},[]);
	return (
		<StyledComponent.ApplyMainContainer>
			<StyledComponent.ApplyTitle>
				<StyledComponent.ApplyHightlightTitle>
					{playgroundTitle}
				</StyledComponent.ApplyHightlightTitle>
			</StyledComponent.ApplyTitle>
			<StyledComponent.ApplyTitleImage
				src={`http://localhost:9000/images/pgmainimg/${src}`}
				alt="대표 이미지"
			/>
			<StyledComponent.ApplyTitle>
				<StyledComponent.ApplyHightlightTitle>
					프로젝트 내용
				</StyledComponent.ApplyHightlightTitle>
			</StyledComponent.ApplyTitle>
			<StyledComponent.ApplyContent>
				<StyledComponent.ApplyContentText>
					{data.description}
				</StyledComponent.ApplyContentText>
			</StyledComponent.ApplyContent>
			<StyledComponent.ApplyTitle>
				<StyledComponent.ApplyHightlightTitle>
					필요한 개발자 현황
				</StyledComponent.ApplyHightlightTitle>
			</StyledComponent.ApplyTitle>
			<StyledComponent.ApplyContent>
				<StyledComponent.ApplySeveralContent>
					{positionList.map((v: any) => {
						return (
							<>
								<StyledComponent.ApplyContentTextContainer>
									<StyledComponent.ApplyContentTextInner>
										<StyledComponent.ApplyContentTextLabel>
											포지션:
										</StyledComponent.ApplyContentTextLabel>
										<StyledComponent.ApplyContentValue>
											{v.position_name}
										</StyledComponent.ApplyContentValue>
									</StyledComponent.ApplyContentTextInner>
									<StyledComponent.ApplyContentTextInner>
										<StyledComponent.ApplyContentTextLabel>
											인원:
										</StyledComponent.ApplyContentTextLabel>
										<StyledComponent.ApplyContentValue>
											{v.current_position_num} 명
										</StyledComponent.ApplyContentValue>
									</StyledComponent.ApplyContentTextInner>
									<StyledComponent.ApplyContentTextInner>
										<StyledComponent.ApplyContentTextLabel>
											언어:
										</StyledComponent.ApplyContentTextLabel>
										{v.language.map((x: any, i: number) => {
											return (
												<StyledComponent.ApplyContentLanguageLabel
													color={colors[i]}
												>
													{x}
												</StyledComponent.ApplyContentLanguageLabel>
											);
										})}
									</StyledComponent.ApplyContentTextInner>
								</StyledComponent.ApplyContentTextContainer>
							</>
						);
					})}
				</StyledComponent.ApplySeveralContent>
			</StyledComponent.ApplyContent>
			<StyledComponent.SameContainer>
				<StyledComponent.ApplyButton onClick={applyHandler}>참가 요청하기</StyledComponent.ApplyButton>
			</StyledComponent.SameContainer>
			{isShow && 
			<StyledComponent.ApplyModalContainer>
				<StyledComponent.ApplyModalContent>
					  <StyledComponent.ApplyModalHead>
								<StyledComponent.ApplyModalTitle>포지션 리스트 - 원하는 포지션을 체크해주세요!</StyledComponent.ApplyModalTitle>
							  <StyledComponent.ApplyModalButton onClick={onClose}>X</StyledComponent.ApplyModalButton>
						</StyledComponent.ApplyModalHead>
						<StyledComponent.ApplyModalBody>
						   {list.positionList.map((v,i)=> {
								 return (
									 <>
									 	 <StyledComponent.ApplyModalInput id="position"></StyledComponent.ApplyModalInput>
									   <StyledComponent.ApplyModalLabel htmlFor="position"  checkState={list.checkedList[i]} onClick={(e) => handleCheckboxChange(list.positionList, list.checkedList)}>{v.position}</StyledComponent.ApplyModalLabel>
									 </>
									) 
							 })}
						</StyledComponent.ApplyModalBody>
				</StyledComponent.ApplyModalContent>
			</StyledComponent.ApplyModalContainer>}
		</StyledComponent.ApplyMainContainer>
	);
};

export default OtherUI;
