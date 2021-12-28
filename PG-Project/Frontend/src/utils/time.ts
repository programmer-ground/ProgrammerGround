export const getTimeInfo = (time: string) => {
  const date = new Date(time).valueOf();
  const currentDate = new Date().valueOf();

  const timeDiff  = currentDate - date;
  const oneSecond = 1000;
  const oneMinute = oneSecond * 60;
  const oneHour = oneMinute * 60;
  const oneDay = oneHour * 24;
  const oneMonth = oneDay * 30;
  const oneYear = oneMonth * 12;

  if (timeDiff < oneMinute) {
    const secondDiff = Math.round(timeDiff / oneSecond);
    return `${secondDiff}초전`;
  }

  if (timeDiff < oneHour) {
    const minuteDiff = Math.round(timeDiff / oneMinute);
    return `${minuteDiff}분전`;
  }

  if (timeDiff < oneDay) {
    const hourDiff = Math.round(timeDiff / oneHour);
    return `${hourDiff}시간전`;
  }

  if (timeDiff < oneMonth) {
    const daysDiff = Math.round(timeDiff / oneDay);
    return `${daysDiff}일전`;
  }
 
  if (timeDiff < oneYear) {
    const daysDiff = Math.round(timeDiff / oneMonth);
    return `${daysDiff}달전`;
  }

  const daysDiff = Math.round(timeDiff / oneYear);
  return `${daysDiff}년전`;

}