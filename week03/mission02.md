# 마이 페이지 리뷰 작성

# 마이페이지 리뷰 작성 API 명세

## Endpoint
POST `/api/stores/{storeId}/reviews`

## Path Variable
- `storeId` : 해당 가게의 ID (PK)

## Query String
- 없음

## Request Header
- `Authorization: Bearer <JWT>`
- `Content-Type: application/json`

## Response Body

```json
{
  "userId" : 5,
  "storeId" : 55,
  "reviewId" : 3,
  "reviewRating" : 4.5,
  "reviewText" : "너무 맛있었어요!", 
}
