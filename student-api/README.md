# 학생 CRUD API 예제

Spring Boot **4.0.7**, MyBatis, MySQL로 만든 수업용 REST API입니다. 성공과 실패를 모두 같은 JSON 구조로 반환하는 것이 핵심입니다.

## 1. 실행 준비

- JDK 17 이상
- Gradle 8.14 이상 또는 Gradle Wrapper
- MySQL 8 이상

MySQL에서 `src/main/resources/schema.sql`을 실행한 후, 환경 변수 또는 `application.yml`에 접속 정보를 설정합니다.

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "mysql-password"
gradle bootRun
```

기본 DB URL은 `jdbc:mysql://localhost:3306/new_student`입니다. 다른 주소를 쓰려면 `DB_URL`도 설정합니다. `schema.sql`은 수동 실행용이며 애플리케이션 시작 시 자동 실행되지 않습니다.

## 2. 통합 응답 형식

모든 API는 다음 네 필드를 가집니다.

| 필드 | 의미 |
|---|---|
| `success` | 성공 여부 |
| `code` | 클라이언트가 분기할 코드 |
| `message` | 사람이 읽을 설명 |
| `data` | 결과 데이터 또는 검증 오류 (`없으면 null`) |

성공 예시 (`200 OK`):

```json
{
  "success": true,
  "code": "SUCCESS",
  "message": "요청에 성공했습니다.",
  "data": {
    "no": "20260001",
    "majorNo": "101",
    "majorName": "컴퓨터공학과",
    "name": "홍길동",
    "phone": "01012345678"
  }
}
```

실패 예시 (`404 Not Found`):

```json
{
  "success": false,
  "code": "STUDENT_NOT_FOUND",
  "message": "학생을 찾을 수 없습니다.",
  "data": null
}
```

검증 실패 예시 (`400 Bad Request`):

```json
{
  "success": false,
  "code": "INVALID_INPUT",
  "message": "입력값이 올바르지 않습니다.",
  "data": {
    "name": "이름은 필수입니다."
  }
}
```

HTTP 상태는 통신 결과를, `code`는 애플리케이션의 구체적인 실패 이유를 표현합니다.

## 3. API 목록

| 기능 | Method | URL | 성공 상태 |
|---|---|---|---|
| 전체 조회 | GET | `/api/students` | 200 |
| 단건 조회 | GET | `/api/students/{no}` | 200 |
| 등록 | POST | `/api/students/{no}` | 201 |
| 수정 | PUT | `/api/students/{no}` | 200 |
| 삭제 | DELETE | `/api/students/{no}` | 200 |

등록/수정 본문:

```json
{
  "majorNo": "101",
  "name": "홍길동",
  "phone": "01012345678"
}
```

IDE의 HTTP Client를 사용할 경우 `requests.http`에서 모든 요청을 바로 실행할 수 있습니다.

## 4. 오류 코드

| 코드 | HTTP 상태 | 발생 상황 |
|---|---:|---|
| `INVALID_INPUT` | 400 | 학번, 학과 번호, 이름, 전화번호 검증 실패 |
| `MALFORMED_JSON` | 400 | 잘못된 JSON |
| `MAJOR_NOT_FOUND` | 400 | 존재하지 않는 학과 번호 |
| `STUDENT_NOT_FOUND` | 404 | 학생 없음 |
| `STUDENT_ALREADY_EXISTS` | 409 | 학번 중복 |
| `DATABASE_ERROR` | 500 | DB 처리 실패 |
| `INTERNAL_SERVER_ERROR` | 500 | 예상하지 못한 서버 오류 |

## 5. 코드 흐름

```text
HTTP 요청
  → StudentController: URL/JSON 수신, 입력 검증
  → StudentService: 중복·존재 여부 같은 업무 규칙 처리
  → StudentMapper: SQL 실행
  → ApiResponse: 성공 응답 생성

예외 발생
  → GlobalExceptionHandler
  → ErrorCode에 맞는 HTTP 상태와 통합 실패 응답 생성
```

- `controller`: HTTP 요청과 응답을 담당합니다.
- `service`: 트랜잭션과 업무 규칙을 담당합니다.
- `mapper`: Java 메서드와 `StudentMapper.xml` SQL을 연결합니다.
- `common`: 성공/실패 응답 및 전역 예외 처리를 담당합니다.

## 6. 수업에서 짚을 부분

1. Controller가 매번 `try-catch`하지 않아도 예외가 `GlobalExceptionHandler` 한 곳에 모입니다.
2. Service는 학생·학과 존재 여부를 확인하고 의미 있는 `BusinessException`을 던집니다.
3. 실제 SQL은 XML에 있어 Java 코드와 분리해 살펴볼 수 있습니다.
4. 외래 키 오류가 나기 전에 학과 존재 여부를 검사해 `MAJOR_NOT_FOUND`라는 이해하기 쉬운 코드를 반환합니다.
5. 운영 환경의 DB 오류 상세 내용은 로그에만 남기고 API 응답에는 노출하지 않습니다.

## 7. 테스트

```powershell
gradle test
```

`ApiResponseTest`는 성공과 실패가 동일한 네 필드 구조를 사용하는지 보여주는 가장 작은 단위 테스트입니다.
