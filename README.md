# 📅 Schedule Management API
Spring Boot와 Spring Data JPA를 활용하여
일정을 생성, 조회, 수정, 삭제하는 RESTful API를 구현한 프로젝트

본 프로젝트는 **3-Layer Architecture** 기반으로 설계되었으며,
**JPA Auditing**을 활용하여 작성일/수정일을 자동 관리합니다.

### 📄 API 명세서
https://documenter.getpostman.com/view/50422128/2sBXVbHtVK
---
### 🗃️ ERD
<img width="681" height="292" alt="image" src="https://github.com/user-attachments/assets/a0f2bd85-3ca6-4c54-937a-ecf0210e1c49" />

### 설계 구조

- **3-Layer Architecture**
  - Controller → Service → Repository
- Entity와 DTO를 분리하여 설계
- 비즈니스 로직은 Service 계층에서 처리
- Controller는 요청/응답 책임만 가짐

### 📌 주요 기능

### ✅ Lv 1. 일정 생성
- 일정 제목, 내용, 작성자명, 비밀번호 저장
- 작성일/수정일 자동 생성
- API 응답에서 비밀번호 제외

### ✅ Lv 2. 일정 조회
#### 전체 일정 조회
- 작성자명 기준 조회 가능 (Optional)
- 작성자명이 없을 경우 전체 조회
- 수정일 기준 내림차순 정렬
- API 응답에서 비밀번호 제외

#### 선택 일정 조회
- 일정 ID로 단건 조회
- API 응답에서 비밀번호 제외

### ✅ Lv 3. 일정 수정
- 일정 제목, 작성자명만 수정 가능
- 수정 시 비밀번호 검증
- 작성일은 유지, 수정일은 갱신
- API 응답에서 비밀번호 제외

### ✅ Lv 4. 일정 삭제
- 일정 ID로 삭제
- 삭제 시 비밀번호 검증

### ✅ Lv 5. 댓글 추가
- 일정에 댓글을 작성할 수 있는 기능 구현
- 하나의 일정에는 최대 10개의 댓글 작성 가능
- 댓글에는 댓글 내용, 작성자명, 비밀번호, 작성일, 수정일이 저장
- 댓글은 반드시 하나의 일정에 소속되며, 일정 ID를 외래키로 가짐
- API응답에서 비밀번호 제외

### ✅ Lv 6. 일정 단건 조회 시 댓글 표시 기능 추가

### ✅ Lv 7. 입력값 검증


### 🔐 비밀번호 처리 정책

- 비밀번호는 일정 수정/삭제 시 검증 용도로만 사용
- API 응답에는 절대 포함하지 않음
- DTO를 활용하여 Entity 직접 노출 방지


### 📂 프로젝트 구조

```
src/main/java
└─ com.example.schedule
├─ controller # API 요청/응답 처리
├─ service # 비즈니스 로직
├─ repository # 데이터베이스 접근
├─ entity # JPA Entity
└─ dto # Request / Response DTO
```

📝 작성 참고 사이트: https://duektmf34.tistory.com/206
