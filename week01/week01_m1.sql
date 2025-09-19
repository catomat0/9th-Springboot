-- 리뷰 작성하는 쿼리 --
INSERT INTO review (
    user_id,
    store_id,
    review_rating,
    review_text,
    created_at
) VALUES (
    :user_id,
    :store_id,
    :review_rating,
    :review_text,
    NOW(6)
);