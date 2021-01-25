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

## How to Install?

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