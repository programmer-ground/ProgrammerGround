/* eslint-disable import/prefer-default-export */
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`

  body{
    margin:0;
    padding:0;
    font-family: 'Do Hyeon', sans-serif;
    background-color: #f6f7f6;
  }
 
  h1, h2, h3, h4, h5, h6{
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
