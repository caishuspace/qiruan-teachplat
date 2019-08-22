package team.qiruan.service.impl;

import java.util.LinkedList;
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
        return jdbcTemplate.query("SELECT * FROM file WHERE id NOT IN (SELECT fid FROM filerel)",
                new BeanPropertyRowMapper<>(File.class));
    }

    @Override
    public void deleteFiles(List<File> files) {
        List<Object[]> args = new LinkedList<>();
        for (File i : files) {
            args.add(new Object[]{i.getId()});
        }
        jdbcTemplate.batchUpdate("DELETE FROM filerel WHERE fid=?", args);
        jdbcTemplate.batchUpdate("DELETE FROM file WHERE id=?", args);
    }

    @Override
    public void updateFileRelationShip(String filename, List<String> itemNames) {
        jdbcTemplate.update("DELETE FROM filerel WHERE fid=(SELECT id FROM file WHERE filename=?)",filename);
        List<Object[]>args=new LinkedList<>();
        for (String i : itemNames) {
            args.add(new Object[]{filename,i});
        }
        jdbcTemplate.batchUpdate("INSERT INTO filerel(fid,itemname)values((SELECT id FROM file WHERE filename=?),?)");
    }
}
