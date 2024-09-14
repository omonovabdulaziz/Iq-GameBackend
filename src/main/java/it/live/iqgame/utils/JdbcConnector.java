package it.live.iqgame.utils;

import it.live.iqgame.entity.enums.Region;
import it.live.iqgame.payload.UserDTOs.RatingData;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcConnector {
    private final JdbcTemplate jdbcTemplate;

    public void rating_calculation_func() {
        String rating = """
                CREATE OR REPLACE FUNCTION calculate_rating(p_subject_id bigint, p_page_size integer, p_page_number integer)
                    RETURNS TABLE(
                                     user_id bigint,
                                     name varchar(255),
                                     surname varchar(255),
                                     region varchar(255),
                                     iq integer,
                                     percent integer,
                                     ball_count bigint,
                                     key_count bigint,
                                     total_attempts bigint
                                 )
                    LANGUAGE plpgsql
                AS $$
                BEGIN
                    RETURN QUERY
                        WITH attempt_summary AS (
                            SELECT
                                a.user_id,
                                SUM(CASE WHEN a.question_type = 'IMAGE' THEN 1 ELSE 0 END) AS key_count,
                                SUM(CASE WHEN a.question_type = 'TEST' THEN 1 ELSE 0 END) AS ball_count,
                                COUNT(*) AS total_attempts
                            FROM attempts a
                            WHERE a.subject_id = p_subject_id
                            GROUP BY a.user_id
                        )
                        SELECT
                            u.id AS user_id,
                            u.name,
                            u.surname,
                            u.region,
                            GREATEST(CAST(FLOOR((ats.ball_count * 50.0) / 75 + (ats.key_count * 50.0) / 15 - ats.total_attempts) AS INTEGER), 0) AS iq,
                            CAST(FLOOR((ats.ball_count * 50.0) / 75 + (ats.key_count * 50.0) / 15) AS INTEGER) AS percent,
                            ats.ball_count,
                            ats.key_count,
                            ats.total_attempts
                        FROM attempt_summary ats
                                 JOIN users u ON u.id = ats.user_id
                        ORDER BY iq DESC
                        OFFSET p_page_size * (p_page_number - 1) LIMIT p_page_size;
                END;
                $$;
                
                ALTER FUNCTION calculate_rating(bigint, integer, integer) OWNER TO postgres;
                """;
        jdbcTemplate.execute(rating);
    }

    public List<RatingData> getRating(Long subjectId, int page, int size) {
        String sql = "SELECT * FROM calculate_rating(?, ?, ?)";
        try {
            return jdbcTemplate.query(sql, new Object[]{subjectId, size, page + 1}, (rs, rowNum) -> RatingData.builder().id(rs.getLong("user_id")).name(rs.getString("name")).surname(rs.getString("surname")).region(rs.getString("region") != null ? Region.valueOf(rs.getString("region")) : null).iq(rs.getObject("iq") != null ? rs.getInt("iq") : 0).percent(rs.getObject("percent") != null ? rs.getInt("percent") : 0).ball_count(rs.getObject("ball_count") != null ? rs.getInt("ball_count") : 0).key_count(rs.getObject("key_count") != null ? rs.getInt("key_count") : 0).total_attempts(rs.getObject("total_attempts") != null ? rs.getInt("total_attempts") : 0).build());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public int getTotalCount(Long subjectId) {
        String countSql = "SELECT COUNT(*) FROM attempts WHERE subject_id = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(countSql, Integer.class, subjectId);
            return count != null ? count : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}