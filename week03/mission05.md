# 회원 가입 하기

# 회원 가입 API 명세

## Endpoint
POST `/api/auth/signUp`

## Path Variable
- 없음

## Query String
- 없음

## Request Header
- `Content-Type: application/json`

## Response Body

```json
{
  
  "userId": 1111,
  "userName": "김동국",
  "userGender": "MALE",
  "userBirth": "2000-12-24T00:00:00",
  "userPoint": 0,
  "isActive": true,
  "isSocial": false,
  "socialId" : null,
  "userSocialProvider" : null,
  "userProfileUrl": null,
  "userNickname": "goto",
  "userEmail": "goto@umc.com",
  "userPhone": "010-1234-5678",
  "address": {
    "addressId": 123,
    "roadAddr": "서울특별시 성북구 정릉로 11",
    "detailAddr": "101동 101호"
  },
  "createdAt": "2025-10-03T04:10:22.123456",
  "updatedAt": "2025-10-03T04:10:22.123456"
  
}
