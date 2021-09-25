import React, {useState, useEffect} from 'react';
import {getNoticeResult} from '@src/lib/axios/playground';
import * as StyledComponent from './style';

const ResultList = () => {
  const [resultItem, setResultItem] = useState([]);
  useEffect(() => {
    const getData = async () => {
      const userNoticeList = await getNoticeResult();
      setResultItem(userNoticeList.user_notice);
    };
    getData();
  },[]); 

  return (
    <>
      {resultItem.map((v, i) => {
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
         </StyledComponent.InfoAuthorContainer>
       </StyledComponent.InfoBodyContent>
      })}
    </>
  )
}

export default ResultList;