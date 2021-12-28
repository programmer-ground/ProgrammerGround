/* eslint-disable react/no-array-index-key */
/* eslint-disable react/jsx-key */
/* eslint-disable react/require-default-props */
/* eslint-disable react/prop-types */
import React from 'react';
import useShow from '@src/hooks/useShow';
import { useHistory } from 'react-router-dom';
import { getOnePlayground, getOneUser } from '@src/lib/axios/playground';
import { getOnePlaygroundItem } from '@src/store/modules/Playground';
import * as StyledComponent from './style';

interface Playground {
	id: number;
	title: string;
	createDate: string;
	src?: string;
	user: string;
	positionList: any;
}

const PlaygroundContent = ({
	id,
	title,
	createDate,
	src,
	user,
	positionList,
}: Playground) => {
	const [show, dispatch] = useShow();
	const history = useHistory();
	const createModalFunc = async (
		playgroundId: number,
		event: any,
		playgroundTitle: string,
	) => {
		const data = await getOnePlayground(playgroundId);
		const loginUserName = await getOneUser();
		dispatch(getOnePlaygroundItem(data));
		history.push({
			pathname: `/playground/${playgroundId}`,
			state: {
				playgroundTitle,
				data,
				loginUserName,
				src,
				createDate,
				positionList,
				id,
			},
		});
	};
	return (
		<>
			<StyledComponent.PlaygroundContent
				onClick={(e) => createModalFunc(id, e, title)}
			>
				<StyledComponent.PlaygroundImg
					src={`${process.env.REACT_APP_API_ADDRESS}/images/pgmainimg/${src}`}
				/>
				<StyledComponent.PlaygroundPersonInfo>
					<StyledComponent.PlaygroundStatus>
						모집중
					</StyledComponent.PlaygroundStatus>
					<StyledComponent.PlaygroundTitle>
						{title}
					</StyledComponent.PlaygroundTitle>
					<StyledComponent.PlaygroundUserContainer>
						<StyledComponent.PlaygroundUserName>
							{user}
						</StyledComponent.PlaygroundUserName>
						<StyledComponent.PlaygroundCreateDate>
							{createDate}
						</StyledComponent.PlaygroundCreateDate>
					</StyledComponent.PlaygroundUserContainer>
				</StyledComponent.PlaygroundPersonInfo>
			</StyledComponent.PlaygroundContent>
		</>
	);
};

export default PlaygroundContent;
