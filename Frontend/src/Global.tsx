/* eslint-disable import/prefer-default-export */
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`

  body{
    margin:0;
    padding:0;
    font-family: 'Do Hyeon', sans-serif;
  }
 
  h1{
    margin:0;
    padding:0;
  }
  em{
    font-style:normal;
  }
  :root[color-theme='light'] {
    --background: #fff;
    --color: #000;
  }
  
  :root[color-theme='dark'] {
    --background: #000;
    --color: #fff;
  }
  
  html {
    background: var(--background);
    color:var(--color);
  }
  
`;
