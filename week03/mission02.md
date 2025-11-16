# 마이 페이지 리뷰 작성

# 마이페이지 리뷰 작성 API 명세

## Endpoint
POST `/api/reviews/{storeId}`

## Path Variable
- `storeId` : 해당 가게의 ID (PK)

## Query String
- 없음

## Request Header
- `Authorization: Bearer <JWT>`
- `Content-Type: application/json`

## Request Body

```json
{
  "reviewRating" : 4.5,
  "reviewText" : "너무 맛있었어요!"
}
```

## Response Body

```json
{
  "reviewId" : 3,
  "reviewRating" : 4.5,
  "reviewText" : "너무 맛있었어요!",
  "createdAt": "2025-10-03T04:10:22.123456",
  "updatedAt": "2025-10-03T04:10:22.123456"
}
```