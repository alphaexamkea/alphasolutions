package org.example.alphasolutions.repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.example.alphasolutions.Interfaces.SubProjectRepository;
import org.example.alphasolutions.model.SubProject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SubProjectRepositoryImpl implements SubProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public SubProjectRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//---------find all---------
    @Override
    public List<SubProject> findAll() {
        String sql = "SELECT * FROM subprojects";
        return jdbcTemplate.query(sql, new SubProjectRowMapper());
    }
//---------find by id---------
    @Override
    public SubProject findByID(int subProjectID) {
        String sql = "SELECT * FROM subprojects WHERE subproject_id = ?";
        return jdbcTemplate.queryForObject(sql, new SubProjectRowMapper(), subProjectID);
    }
//---------find by project id---------
    @Override
    public List<SubProject> findByProjectID(int projectID) {
        String sql = "SELECT * FROM subprojects WHERE project_id = ?";
        return jdbcTemplate.query(sql, new SubProjectRowMapper(), projectID);
    }
//---------find by status---------
    @Override
    public List<SubProject> findByStatus(String status) {
        String sql = "SELECT * FROM subprojects WHERE subproject_status = ?";
        return jdbcTemplate.query(sql, new SubProjectRowMapper(), status);
    }
    //---------find by priority---------
    @Override
    public List<SubProject> findByPriority(String priority) {
        String sql = "SELECT * FROM subprojects WHERE subproject_priority = ?";
        return jdbcTemplate.query(sql, new SubProjectRowMapper(), priority);
    }
    //------------CRUD OPS----------
    //---------save---------
    @Override
    public int save(SubProject subProject) {
        String sql = "INSERT INTO subprojects (subproject_name, subproject_description, subproject_start_date, " +
                "subproject_deadline, subproject_time_estimate, subproject_time_spent, subproject_status, " +
                "subproject_priority, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subProject.getSubProjectName());
            ps.setString(2, subProject.getSubProjectDescription());
            ps.setObject(3, subProject.getSubProjectStartDate());
            ps.setObject(4, subProject.getSubProjectDeadline());
            ps.setInt(5, subProject.getSubProjectTimeEstimate());
            ps.setInt(6, subProject.getSubProjectTimeSpent());
            ps.setString(7, subProject.getSubProjectStatus());
            ps.setString(8, subProject.getSubProjectPriority());
            ps.setInt(9, subProject.getProjectID());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
//---------update---------
    @Override
    public void update(SubProject subProject, int subProjectID) {
        String sql = "UPDATE subprojects SET subproject_name = ?, subproject_description = ?, " +
                "subproject_start_date = ?, subproject_deadline = ?, subproject_time_estimate = ?, " +
                "subproject_time_spent = ?, subproject_status = ?, subproject_priority = ?, " +
                "project_id = ? WHERE subproject_id = ?";

        jdbcTemplate.update(sql,
                subProject.getSubProjectName(),
                subProject.getSubProjectDescription(),
                subProject.getSubProjectStartDate(),
                subProject.getSubProjectDeadline(),
                subProject.getSubProjectTimeEstimate(),
                subProject.getSubProjectTimeSpent(),
                subProject.getSubProjectStatus(),
                subProject.getSubProjectPriority(),
                subProject.getProjectID(),
                subProject.getSubProjectID());
    }
//---------delete---------
    @Override
    public void delete(int subProjectID) {
        String sql = "DELETE FROM subprojects WHERE subproject_id = ?";
        jdbcTemplate.update(sql, subProjectID);
    }
//---------RowMapper (result set)---------
    private static class SubProjectRowMapper implements RowMapper<SubProject> {
        @Override
        public SubProject mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SubProject(
                    rs.getInt("subproject_id"),
                    rs.getString("subproject_name"),
                    rs.getString("subproject_description"),
                    rs.getDate("subproject_start_date").toLocalDate(),
                    rs.getDate("subproject_deadline").toLocalDate(),
                    rs.getInt("subproject_time_estimate"),
                    rs.getInt("subproject_time_spent"),
                    rs.getString("subproject_status"),
                    rs.getString("subproject_priority"),
                    rs.getInt("project_id")
            );
        }
    }
}
