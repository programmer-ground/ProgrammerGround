import React from 'react';
import * as StyledComponent from './style';

interface playground {
	playgroundTitle: string;
	src: string;
	createDate: string;
	data: any;
	positionList: any;
}
const SameUI = ({
	playgroundTitle,
	src,
	createDate,
	data,
	positionList,
}: playground) => {
	console.log(positionList);
	return (
		<StyledComponent.SameMainContainer>
			<StyledComponent.SameTitle>
				<StyledComponent.SameHightlightTitle>
					{playgroundTitle}
				</StyledComponent.SameHightlightTitle>
			</StyledComponent.SameTitle>
			<StyledComponent.SameTitleImage
				src={`http://localhost:9000/images/pgmainimg/${src}`}
				alt="대표 이미지"
			/>
			<StyledComponent.SameTitle>
				<StyledComponent.SameHightlightTitle>
					프로젝트 내용
				</StyledComponent.SameHightlightTitle>
			</StyledComponent.SameTitle>
			<StyledComponent.SameContent>
				<StyledComponent.SameContentText>
					{data.description}
				</StyledComponent.SameContentText>
			</StyledComponent.SameContent>
			<StyledComponent.SameTitle>
				<StyledComponent.SameHightlightTitle>
					필요한 개발자 현황
				</StyledComponent.SameHightlightTitle>
			</StyledComponent.SameTitle>
			<StyledComponent.SameContent>
				<StyledComponent.SameContentText />
			</StyledComponent.SameContent>
			<StyledComponent.SameContainer>
				<StyledComponent.SameModifyButton>
					수정하기
				</StyledComponent.SameModifyButton>
				<StyledComponent.SameDeleteButton>
					삭제하기
				</StyledComponent.SameDeleteButton>
			</StyledComponent.SameContainer>
		</StyledComponent.SameMainContainer>
	);
};

export default SameUI;
