## 웹 기반 클라우드 서비스를 이용한 설문조사 서비스 서베어(Surbear)

![Thumbnail](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/5af9bd99-647c-41b9-bc80-955039dced90)

### 프로젝트 정보
---
- 가천대학교 24학년도 1학기 종합프로젝트(캡스톤디자인)에서 진행한 협업 프로젝트입니다.
- 클라우드 시스템을 기반으로 한 설문조사 서비스를 개발하였으며, chatGPT3.5를 통해 질문을 추천 받을 수 있고, 결과를 각종 차트를 통해 시각적으로 확인할 수 있으며, 적립한 포인트를 통해 상품을 교환할 수 있는 서비스를 개발하였습니다.

### 배포 주소
---
> 프론트 주소: https://app.surbear.site
>
> 백엔드 주소: https://api.surbear.site

### 개발 기간
---
- 2024.03 ~ 2024.05

### 멤버
---
|## Team
|<img src="https://avatars.githubusercontent.com/u/123955813?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/128457944?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/101727551?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/95222741?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|Jangho Yoon<br/>[@seorang42](https://github.com/seorang42)|김현중<br/>[@kimgorok](https://github.com/kimgorok)|Jinyoung Sung<br/>[@Jinyoung2000](https://github.com/Jinyoung2000)|Lim Jick Chan<br/>[@jickDo](https://github.com/jickDo)|


<br />

### 기술 스택
---

## **Environment**
<img src="https://img.shields.io/badge/VISUAL STUDIO CODE-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">  <img src="https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white">  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white">

## **Cooperation**

<img src="https://img.shields.io/badge/FIGMA-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> <img src="https://img.shields.io/badge/NOTION-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/DISCORD-5865F2?style=for-the-badge&logo=discord&logoColor=white">

<br><br>

# 프론트엔드

## **Development**
<img src="https://img.shields.io/badge/REACT-61DAFB?style=for-the-badge&logo=react&logoColor=white">  <img src="https://img.shields.io/badge/NEXT JS-FFFFFF?style=for-the-badge&logo=next.js&logoColor=black"> <img src="https://img.shields.io/badge/TAILWIND CSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white">  <img src="https://img.shields.io/badge/AXIOS-5A29E4?style=for-the-badge&logo=axios&logoColor=white">  <img src="https://img.shields.io/badge/FRAMER MOTION-0055FF?style=for-the-badge&logo=framer&logoColor=white">  <img src="https://img.shields.io/badge/MUI MATERIAL-007FFF?style=for-the-badge&logo=mui&logoColor=white">  <img src="https://img.shields.io/badge/REACT HOOK FORM-EC5990?style=for-the-badge&logo=reacthookform&logoColor=white">  <img src="https://img.shields.io/badge/REACT QUERY-FF4154?style=for-the-badge&logo=reactquery&logoColor=white">  <img src="https://img.shields.io/badge/RECOIL-3578E5?style=for-the-badge&logo=recoil&logoColor=white">


## **Hybrid platform**
<img src="https://img.shields.io/badge/CAPACITOR-119EFF?style=for-the-badge&logo=capacitor&logoColor=white">

<br><br>

# 백엔드

## **Language**

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">

## **Framework**

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">


## **Database**

<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">

## **Deploy**

<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white"> <img src="https://img.shields.io/badge/Route53-8C4FFF?style=for-the-badge&logo=amazonroute53&logoColor=white"> <img src="https://img.shields.io/badge/ALB-8C4FFF?style=for-the-badge&logo=awselasticloadbalancing&logoColor=white">




<br />

### 시작 가이드
---
#### Requirements
> - Next.js : 13 이상
> - Node.js : 14.6.0 이상

### Installation
```
$ git clone https://github.com/SurBear-Team/surbear-frontend.git
$ cd surbear-frontend

$ npm install
$ npm run dev
```


<br />

### 주요 기능
---
## 설문 제작 기능
- 설문 제작자는 설문 주제, 설문 설명, 카테고리, 결과 비공개 여부, 최대 참여 인원, 설문 종료 날짜 등을 작성한 뒤, 설문의 질문을 제작할 수 있습니다.
- 설문 제작자가 입력한 설문 주제를 가지고 chatGPT가 설문 주제에 맞는 질문을 추천해 줍니다.
- 설문 제작자는 객관식 - 다중선택, 객관식 - 단일선택, 주관식, 단답형, 슬라이더 유형의 질문을 제작할 수 있습니다.

## 설문 참여 및 상품 교환 기능
- 설문에 참여한 회원은 포인트를 지급 받으며, 지급 받은 포인트로 상품을 교환할 수 있습니다.

## 결과 조회 기능
- 종료된 설문의 결과는 다양한 차트 및 표를 통해 시각적으로 조회할 수 있습니다.

<br />

### 화면 구성
---
|스플래시 이미지|설문 둘러보기|설문 제작|상품 교환|
|---|---|---|---|
|![image](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/a3c07bdc-44fe-4016-8b31-f68bb4a3b0e5)|![image](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/7b4916e2-65d2-4031-bf75-a5f90d7d3e7b)|![image](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/9181a28a-cad0-42b8-ada9-39bedb213ef5)|![image](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/3c736de9-80b4-4552-b74e-effcdc61c458)

|chatGPT 질문 추천|설문 결과 조회|
|---|---|
|![image](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/a9b7a702-749a-4e74-92ef-4f92764d4e78)|![image](https://github.com/SurBear-Team/surbear-frontend/assets/128457944/49172c99-bf34-462d-8aad-d731daddfda2)















<h1>Surbear</h1>


---

<h1 align="center">
  <br>
  <a href="http://www.amitmerchant.com/electron-markdownify"><img src="picture/surbear280px.png" width="200"></a>
  <br>
  <br>
</h1>

<h2 align="center">설문조사 플랫폼</h2>



## Survey Platform 

---

> 가천대학교 컴퓨터공학과 설문조사 플랫폼
> 
> 개발기간: 2024.03 ~ 2024.06


## How To Use (배포 주소)

---

> 프론트 주소: https://app.surbear.site
> 
> 백엔드 주소: https://api.surbear.site
