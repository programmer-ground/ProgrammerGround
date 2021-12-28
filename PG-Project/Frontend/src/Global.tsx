/* eslint-disable import/prefer-default-export */
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`


body, h1, h2, h3, h4, h5, h6,
ul, ol, dl, dd, p, fieldset, legend{
  margin:0;
  padding:0;
}
html, body{
  height:100%;
  background-color:#e9e9e9;
}

#root{
  height:100%;
}
body, input, textarea, select, button{
  font-size:14px;
  font-family: Dotum, '돋움', Helvetica, "Apple SD Gothic Neo", sans-serif;
}

ul, ol{
  list-style:none;
}

table{
  border-collapse: collapse;
}

em{
  font-style:normal;
}

a{
  color:inherit;
  text-decoration:none;
}

img{
  vertical-align:top;
}

fieldset{
  border:0;
}
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
