package com.Thoufii.ATSResumeChecker.Services;

import com.Thoufii.ATSResumeChecker.Objects.ScoreResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeScoreService {

    private final List<String> expectedSkills = Arrays.asList("java", "spring", "react", "docker", "aws", "sql", "hibernate");

    public ScoreResponse evaluateResume(MultipartFile file, String jobDescription) {
        String resumeText = extractTextFromPDF(file);

        List<String> resumeWords = tokenize(resumeText);
        List<String> jdWords = tokenize(jobDescription);

        Set<String> resumeSet = new HashSet<>(resumeWords);
        Set<String> jdSet = new HashSet<>(jdWords);

        List<String> matchedSkills = expectedSkills.stream().filter(resumeSet::contains).collect(Collectors.toList());
        List<String> missingSkills = expectedSkills.stream().filter(skill -> !resumeSet.contains(skill)).collect(Collectors.toList());

        int score = (int) (((double) matchedSkills.size() / expectedSkills.size()) * 100);

        boolean educationMatch = resumeText.toLowerCase().contains("bachelor") || resumeText.toLowerCase().contains("b.tech");
        boolean experienceMatch = resumeText.toLowerCase().contains("experience") || resumeText.toLowerCase().contains("years");

        List<String> formatTips = new ArrayList<>();
        if (resumeText.contains("table")) formatTips.add("Avoid using tables for layout");
        if (!resumeText.toLowerCase().contains("email")) formatTips.add("Include a valid email address");

        String summary = "Your resume is well aligned with the job description.";
        if (!missingSkills.isEmpty()) summary += " Add " + String.join(", ", missingSkills) + " to improve your score.";

        int skillScore = matchedSkills.size() * 5; // max 50
        int educationScore = educationMatch ? 20 : 0;
        int experienceScore = experienceMatch ? 20 : 0;
        int formatScore = Math.max(0, 10 - formatTips.size());

        int totalScore = skillScore + educationScore + experienceScore + formatScore;

        String skillComment = matchedSkills.size() >= 5 ?
                "Great skill match!" : "Add more relevant skills from the job description.";
        String educationComment = educationMatch ?
                "Education meets the requirement." : "Consider aligning your education to the job.";
        String experienceComment = experienceMatch ?
                "Relevant experience detected." : "Tailor your experience for this role.";
        String formatComment = formatTips.isEmpty() ?
                "Resume format looks good." : "Fix format issues for better ATS readability.";

        return new ScoreResponse(
                totalScore,          // int score
                matchedSkills,       // List<String> matchedSkills
                missingSkills,       // List<String> missingSkills
                educationMatch,      // boolean educationMatch
                experienceMatch,     // boolean experienceMatch
                formatTips,
                "Here’s a breakdown of your resume’s ATS score.", // String summary
                skillScore,          // int skillScore
                educationScore,      // int educationScore
                experienceScore,     // int experienceScore
                formatScore,         // int formatScore
                skillComment,        // String skillComment
                educationComment,    // String educationComment
                experienceComment,   // String experienceComment
                formatComment        // String formatComment
        );
    }

    private String extractTextFromPDF(MultipartFile file) {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            return new PDFTextStripper().getText(document);
        } catch (IOException e) {
            return "";
        }
    }

    private List<String> tokenize(String text) {
        return Arrays.stream(text.toLowerCase().split("\\W+")).filter(w -> w.length() > 2).collect(Collectors.toList());
    }
}