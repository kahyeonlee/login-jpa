## 🔊JPA로 회원가입과 로그인 구현


### 📌 회원가입 기능 구현 [/api/v1/users/join]()
#### 기능 정의 
    - userName과 password 입력하면 userName이 중복되지 않을 경우 회원가입되기
    - 중복이 될 경우에 id중복 메세지반환 , 재가입 요청


#### 1. 테스트 작성(회원가입 성공, 회원가입 실패)
#### 2. customException 처리

***

### 📌로그인 기능 구현 [/api/v1/users/login]()
#### 기능 정의
    - userName, password 입력해서 로그인하기


#### 1. spring Security 사용
#### 2. 로그인 성공, 실패 Test
    - 로그인 실패1 - userName 존재하지 않음
    - 로그인 실패2 - password에 일치하지 않음
#### 3. 비밀번호 암호화
#### 4. token 발행

***

### 📌리뷰 기능 구현 [/api/v1/reviews]()

#### 기능 정의
    - 로그인 한 사용자가 병원 리뷰 작성하는 기능
    

#### 1. POST /api/v1/reviews Endpoint만들기
#### 2. JwtTokenFilter 인증 계층 추가 하기
#### 3. 모든 요청에 권한 부여하기
#### 4. 필터 처리
    -  Token이 없으면 권한 부여 하지 않기
    -  Token의 유효기간 초과시 권한 부여하지 않기
#### 5. token에 담긴 `userName` controller에서 사용 

***

### 📌방문 기록 기능 구현 [/api/v1/visits]()

#### 기능 정의
    - 로그인 한 사용자가 본인이 방문한 병원에 어떤 증상으로 방문 했는지 진료비는 얼마였는지를 기록
    - 전체, 특정 user, 특정 병원 별로 방문 기록을 조회
#### 엔티티 다이어그램


#### 1. create기능(POST /api/v1/visits)
#### 2. 전체조회 기능(GET /api/v1/visits)
#### 3. 특정 user의 기록 조회(GET /api/v1/visits/users/{id})
#### 4. 특정 병원의 기록 조회(GET /api/v1/visits/hospitals/{id})

