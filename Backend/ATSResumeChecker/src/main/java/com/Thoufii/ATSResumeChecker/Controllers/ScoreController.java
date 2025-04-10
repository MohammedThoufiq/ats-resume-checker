package com.Thoufii.ATSResumeChecker.Controllers;

import com.Thoufii.ATSResumeChecker.Objects.ScoreResponse;
import com.Thoufii.ATSResumeChecker.Services.ResumeScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    private ResumeScoreService resumeScoreService;

    @PostMapping("/score")
    public ResponseEntity<ScoreResponse> scoreResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription
    ) {
        return ResponseEntity.ok(resumeScoreService.evaluateResume(file, jobDescription));
    }
}
