# 홈 화면

# 홈 화면 API 명세

## Endpoint
GET `/api/feed/home`

## Path Variable
- 없음

## Query String
- `regionId` (number): 현재 선택된 지역 ID
- `page` (number, default: 0): 페이지 번호
- `size` (number, default: 10): 페이지 크기
- `sort` (string, default: `createdAt,DESC;missionId,DESC`): 정렬 기준

## Request Header
- `Authorization: Bearer <JWT>`

## Request Body
- 없음

## Response Body

```json
{
  "regionId": 3,
  "regionName": "서울 성북구",
  
  "items": [
    {
      "missionId": 101,
      "missionPoint": 500,
      "missionCost": 10000,
      "missionDue": 7,
      "storeId": 55,
      "storeName": "반이학생마라탕",
      "storeType": "CHINESE"
    },
    {
      "missionId": 102,
      "missionPoint": 500,
      "missionCost": 12000,
      "missionDue": 7,
      "storeId": 66,
      "storeName": "동방칼국수",
      "storeType": "KOREAN"
    },
    
    ...
  ],

  "page": 0,
  "size": 10,
  "totalItems": 145,
  "totalPages": 15,
  "sort": ["createdAt,DESC", "missionId,DESC"]
}
```