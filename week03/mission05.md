# 회원 가입 하기

# 회원 가입 API 명세

## Endpoint
POST `/api/users/signUp`

## Path Variable
- 없음

## Query String
- 없음

## Request Header
- `Content-Type: application/json`

## Request Body

```json

{
  "userName": "김동국",
  "userGender": "MALE",
  "userBirth": "2000-12-24",
  "address": {
    "roadAddr": "서울특별시 성북구 정릉로 11",
    "detailAddr": "101동 101호"
  },
  "terms": [ 1, 2, 4 ],
  "cuisines": [ 2, 3, 5 ]
}
```

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
  "address": {
    "addressId": 123,
    "roadAddr": "서울특별시 성북구 정릉로 11",
    "detailAddr": "101동 101호"
  },
  "terms": [ 1, 2, 4 ],
  "cuisines": [ 2, 3, 5 ],
  "createdAt": "2025-10-03T04:10:22.123456",
  "updatedAt": "2025-10-03T04:10:22.123456"
  
} 
```
