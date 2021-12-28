프론트엔드 - 개발자 송진현

개발 환경
#### 📘 Front-End : React, Typescript, Redux, Webpack & Babel

디렉토리 구조
  ```
  📁Frontend 
  ├── 📁public
  │   ├── 📁images    
  │   └── index.html
  └── 📁src
      ├── 📁@types
      ├── 📁api
      ├── 📁assets
      ├── 📁components  
      │   ├── 📁Common 
      ├── 📁hooks
      ├── 📁pages
      │   ├── LoginPage
      │          ├── index.tsx
      │          ├── style.ts
      └── 📁tests
      └── 📁utils
  ```
## Page
- LoginPage 
  <img width="1464" alt="스크린샷 2021-01-28 오후 10 03 23" src="https://user-images.githubusercontent.com/22065725/106142810-4b959080-61b5-11eb-8765-caa947cfdd77.png">
  
## How to Install?
- Frontend 
```
   cd Frontend
   npm install
   npm run start
```

## How to Settings 
- .env.development : Frontend 디렉토리 바로  밑에  추가
```
REACT_APP_GET_OAUTH_TOKEN=
REACT_APP_GET_JWT_TOKEN=
REACT_APP_API_ADDRESS=
REACT_APP_FRONT_ADDRESS=
```

### Pre Requirement
- docker
- docker-compose

### 1. Build
```
   git clone https://github.com/programmer-ground/ProgrammerGround
   cd ProgrammerGround
   docker-compose build
```

### 2. Run
```
  docker-compose up -d
```

### 3. Check Container process
```
  docker-compose ps
```


### 4. Logs
```
  docker logs <container_ip>
```

### 5. Shutdown all container
```
  docker-compose down
```

## 백엔드 환경 구성
### 초기 개발 환경 세팅

+ 오른쪽 상단의 application -> edit configuration을 클릭한다.

<img width="384" alt="Screen Shot 2021-12-25 at 0 54 52" src="https://user-images.githubusercontent.com/22961251/147363309-561b362d-3344-4a54-8379-a35533a345c8.png">

+ 설정할 어플리케이션을 왼쪽 탭에서 클릭하여 수정한다.

<img width="1037" alt="Screen Shot 2021-12-25 at 0 57 40" src="https://user-images.githubusercontent.com/22961251/147363383-edf5ba4a-7cff-4fae-a52b-650efc0a4c5f.png">

+ 수정할 내용은 Active Profile, Environment variables 탭이다.
+ 없을 경우에는 아래의 이미지처럼 Modify options을 통해서 찾을 수 있다.

<img width="532" alt="Screen Shot 2021-12-25 at 0 59 07" src="https://user-images.githubusercontent.com/22961251/147363454-f99b925b-1d5d-4738-b8f1-f52e8c1a12e1.png">


해당 환경 설정 샘플은 다음과 같다.
+ AuthApplication

```.sh
MYSQL_USER=root;MYSQL_USER_PASS=test;GITHUB_APP_CLIENT_ID=test;GITHUB_APP_CLIENT_SECRET=test;JWT_SECRET_KEY=test;GITHUB_APP_SCOPE=public_repo,read:user
```

+ ProgrammerGroundApplication

```.sh
MYSQL_USER=root;MYSQL_USER_PASS=test
```
