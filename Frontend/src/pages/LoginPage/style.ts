/* eslint-disable import/no-unresolved */
import React from 'react';
import styled, { createGlobalStyle } from 'styled-components';
import logo from '@src/assets/logo.svg';
import githublogo from '@src/assets/github.svg';

export const GlobalStyle = createGlobalStyle`
  body{
    background-color:#e9e9e9;
    margin:0;
    padding:0;
  }
`;
export const LoginContainer = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  width: 40%;
  height: 400px;
  border: 1px solid black;
  background-color: #fff;
  margin: 200px auto;
  @media screen and (max-width: 840px) and (min-width: 480px) {
    height: 330px;
    width: 300px;
  }
  @media screen and (max-width: 479px) and (min-width: 0px) {
    height: 230px;
    width: 200px;
  }
`;

export const LoginLogo = styled.img.attrs({
  src: logo,
})`
  position: relative;
  width: 300px;
  height: 250px;
  margin: 0 auto;
  @media screen and (max-width: 840px) and (min-width: 480px) {
    height: 200px;
    width: 200px;
  }
  @media screen and (max-width: 479px) and (min-width: 0px) {
    height: 130px;
    width: 130px;
  }
`;
export const LoginButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;
export const LoginButton = styled.button`
  position: relative;
  display: inline-block;
  width: 600px;
  height: 70px;
  background-color: #000;
  margin: 20px auto;
  color: #fff;
  font-weight: bold;
  font-size: 30px;
  &:hover {
    cursor: pointer;
  }

  @media screen and (max-width: 840px) and (min-width: 480px) {
    height: 60px;
    width: 340px;
    font-size: 20px;
  }
  @media screen and (max-width: 479px) and (min-width: 0px) {
    height: 40px;
    width: 260px;
    font-size: 15px;
  }
`;

export const GithubLogo = styled.img.attrs({
  src: githublogo,
})`
  width: 25px;
  height: 25px;
  margin-left: 10px;

  @media screen and (max-width: 479px) and (min-width: 0px) {
    width: 15px;
    height: 15px;
  }
`;
