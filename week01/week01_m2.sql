--마이 페이지 화면 쿼리--
SELECT
    u.user_id,
    u.user_profile_url,
    u.user_nickname,
    u.user_email,
    u.user_phone,
    u.user_point,
    u.is_active,
    u.user_social_provider,
    u.social_id,
FROM user u
WHERE u.user_id = :user_id;