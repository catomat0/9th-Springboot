# 미션 목록 조회

# 미션 목록 조회(진행중 / 진행 완료) API 명세

## Endpoint
GET `/api/v1/user_missions`

## Path Variable
- 없음

## Query String
- `status` (string): `IN_PROGRESS` or `SUCCESS`
- `page` (number, default: 0): 페이지 번호
- `size` (number, default: 10): 페이지 크기
- `sort` (string, default: `createdAt,DESC;missionId,DESC`): 정렬 기준


## Request Header
- `Authorization: Bearer <JWT>`
- `Content-Type: application/json`

## Response Body

```json
{
  
  "inProgress": {
    
    items: [
        
        {
          "missionId": 101,
          "missionPoint": 500,
          "missionCost": 10000,
          "missionDue": 7,
          "storeId": 55,
          "storeName": "반이학생마라탕",
          "storeType": "CHINESE",
          "regionId": 3,
          "regionName": "서울 성북구"
        },
        {
          "missionId": 102,
          "missionPoint": 500,
          "missionCost": 10000,
          "missionDue": 7,
          "storeId": 66,
          "storeName": "동방칼국수",
          "storeType": "KOREAN",
          "regionId": 5,
          "regionName": "서울 성북구"
        },
        
        ...
      ],
      
      "page": 0,
      "size": 10,
      "totalElements": 125,
      "totalPages": 13,
      "sort": ["createdAt,DESC", "missionId,DESC"]
  },
  
  "success": {
  
    items : [
        
        {
          "missionId": 101,
          "missionPoint": 500,
          "missionCost": 10000,
          "missionDue": 7,
          "storeId": 55,
          "storeName": "반이학생마라탕",
          "storeType": "CHINESE",
          "regionId": 3,
          "regionName": "서울 성북구"
        },
        {
          "missionId": 102,
          "missionPoint": 500,
          "missionCost": 10000,
          "missionDue": 7,
          "storeId": 66,
          "storeName": "동방칼국수",
          "storeType": "KOREAN",
          "regionId": 5,
          "regionName": "서울 성북구"
        },
        
        ...
      ],
      
      "page": 0,
      "size": 10,
      "totalElements": 125,
      "totalPages": 13,
      "sort": ["createdAt,DESC", "missionId,DESC"]
  }
  
}
