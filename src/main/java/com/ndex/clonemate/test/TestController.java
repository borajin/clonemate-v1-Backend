package com.ndex.clonemate.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("test")
@RestController
public class TestController {
    private final TestRepository testRepository;

    @GetMapping
    public ResponseEntity<?> getDatas() {
        List<TestEntity> datas = testRepository.findAll();
        return ResponseEntity.ok().body(datas);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> createData(@RequestBody String data) {
        TestEntity newData = new TestEntity();
        newData.setData(data);
        testRepository.save(newData);
        return ResponseEntity.ok().body(newData.getId() + " 생성");
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
        testRepository.deleteById(id);
        return ResponseEntity.ok().body(id + " 삭제");
    }
}
