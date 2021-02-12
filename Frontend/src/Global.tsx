/* eslint-disable import/prefer-default-export */
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  body{
    margin:0;
    padding:0;
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
    color: var(--color);
  }
  
`;
