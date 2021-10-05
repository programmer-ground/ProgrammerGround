import React, {useState, useEffect} from 'react';
import {getNoticeWaitingList} from '@src/lib/axios/playground';
import * as StyledComponent from './style';

const ResultList = (menu: number) => {
  const [resultItem, setResultItem] = useState([]);
  useEffect(() => {
    const getData = async () => {
      const userNoticeWaitingList = await getNoticeWaitingList();
      setResultItem(userNoticeWaitingList.user_notice);
    };
    getData();
  },[menu]); 

  return (
    <>
      {resultItem.map((v, i) => {
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
                   <StyledComponent.InfoNameEmphasis>{v.position}</StyledComponent.InfoNameEmphasis>
               </StyledComponent.InfoAuthorName>
               <StyledComponent.InfoAuthorPosition>{v.status}</StyledComponent.InfoAuthorPosition>
             </StyledComponent.InfoBodyAuthor>
           </StyledComponent.InfoContainerItem>
         </StyledComponent.InfoAuthorContainer>
       </StyledComponent.InfoBodyContent>
      )})}
    </>
  )
}

export default ResultList;