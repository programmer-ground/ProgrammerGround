프론트엔드 - 개발자 송진현

개발 환경
#### 📘 Front-End : React, Typescript, Redux, Webpack & Babel

디렉토리 구조
  ```
  📁client  
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
