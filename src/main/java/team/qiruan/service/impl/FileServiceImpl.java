package team.qiruan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.domain.File;
import team.qiruan.service.FileService;

@Service
/**
 * FileServiceImpl
 */
public class FileServiceImpl implements FileService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean addFile(String filename, String type) {
        return jdbcTemplate.update("INSERT INTO file(filename,type) VALUES(?,?)", filename, type) > 0;
    }

    @Override
	public List<File> getUnusedFile() {
		return jdbcTemplate.query("SELECT * FROM file WHERE id NOT IN (SELECT fid FROM filerel)", new BeanPropertyRowMapper<>(File.class));
	}
}
