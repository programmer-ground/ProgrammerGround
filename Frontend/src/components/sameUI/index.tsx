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
	const colors = ['red', 'yellow', 'blue', 'green', 'purple', 'pink'];
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
				<StyledComponent.SameSeveralContent>
					{positionList.map((v: any) => {
						return (
							<>
								<StyledComponent.SameContentTextContainer>
									<StyledComponent.SameContentTextInner>
										<StyledComponent.SameContentTextLabel>
											포지션:
										</StyledComponent.SameContentTextLabel>
										<StyledComponent.SameContentValue>
											{v.position_name}
										</StyledComponent.SameContentValue>
									</StyledComponent.SameContentTextInner>
									<StyledComponent.SameContentTextInner>
										<StyledComponent.SameContentTextLabel>
											인원:
										</StyledComponent.SameContentTextLabel>
										<StyledComponent.SameContentValue>
											{v.current_position_num} 명
										</StyledComponent.SameContentValue>
									</StyledComponent.SameContentTextInner>
									<StyledComponent.SameContentTextInner>
										<StyledComponent.SameContentTextLabel>
											언어:
										</StyledComponent.SameContentTextLabel>
										{v.language.map((x: any, i: number) => {
											return (
												<StyledComponent.SameContentLanguageLabel
													color={colors[i]}
												>
													{x}
												</StyledComponent.SameContentLanguageLabel>
											);
										})}
									</StyledComponent.SameContentTextInner>
								</StyledComponent.SameContentTextContainer>
							</>
						);
					})}
				</StyledComponent.SameSeveralContent>
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
