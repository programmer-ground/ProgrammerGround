import React, {useState, useEffect} from 'react';
import { getNoticeLeaderList } from '@src/lib/axios/playground';
import * as StyledComponent from './style';

const ApplyList = () => {
  
  const [noticeItem, setNoticeItem] = useState([]);

  useEffect(() => {
		const getData = async () => {
			const noticeData = await getNoticeLeaderList();
			setNoticeItem(noticeData.user_notice);
		}
		getData();
	}, []);
  
  return (
    <>
    {noticeItem.map((v,i)=> {
        return (
          <StyledComponent.InfoBodyContent key={i}>
            <StyledComponent.InfoAuthorContainer>
              <StyledComponent.InfoContainerItem>
                <StyledComponent.InfoTitleBody>
                    <StyledComponent.InfoBodyTitle>{v.playground_title}</StyledComponent.InfoBodyTitle>
                    <StyledComponent.InfoBodyDate>{v.date.toString().slice(0, 10)}</StyledComponent.InfoBodyDate>
                </StyledComponent.InfoTitleBody>
                <StyledComponent.InfoBodyAuthor>
                  <StyledComponent.InfoAuthorName>
                      <StyledComponent.InfoNameEmphasis>{v.user_name}</StyledComponent.InfoNameEmphasis>
                    님</StyledComponent.InfoAuthorName>
                  <StyledComponent.InfoAuthorPosition>{v.position}</StyledComponent.InfoAuthorPosition>
                </StyledComponent.InfoBodyAuthor>
              </StyledComponent.InfoContainerItem>
              <StyledComponent.InfoContainerItem>
                <StyledComponent.InfoAcceptButton>수락</StyledComponent.InfoAcceptButton>
                <StyledComponent.InfoRejectButton>거절</StyledComponent.InfoRejectButton>
              </StyledComponent.InfoContainerItem>
            </StyledComponent.InfoAuthorContainer>
          </StyledComponent.InfoBodyContent>
        )
		  })}
    </>
  )
}

export default ApplyList;

