import React from 'react';
import { useHistory } from 'react-router-dom';
import * as StyledComponent from './style';

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
				<StyledComponent.ApplyButton>참가 요청하기</StyledComponent.ApplyButton>
			</StyledComponent.SameContainer>
		</StyledComponent.ApplyMainContainer>
	);
};

export default OtherUI;
