# 미션 성공 누르기

# 미션 성공 버튼 API 명세

## Endpoint
POST `/api/mission/user_mission/{userMissionId}`

## Path Variable
- `userMissionId` : 진행 중인 유저의 미션의 ID (PK)

## Query String
- 없음

## Request Header
- `Authorization: Bearer <JWT>`
- `Content-Type: application/json`

## Response Body

```json
{
  "userMissionId" : 50,
  "userId" : 10,
  "missionId" : 110,
  "missionCode" : 20251003291021,
  "userMissionStatus" : "SUCCESS",
  "userPoint" : 1500
}
