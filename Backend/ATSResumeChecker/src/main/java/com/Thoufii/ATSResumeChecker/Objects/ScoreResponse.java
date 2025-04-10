package com.Thoufii.ATSResumeChecker.Objects;


import lombok.*;

import java.util.List;

public class ScoreResponse {
    private int score;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private boolean educationMatch;
    private boolean experienceMatch;
    private List<String> formatTips;
    private String summary;
    private int skillScore;
    private int educationScore;
    private int experienceScore;
    private int formatScore;
    private String skillComment;
    private String educationComment;
    private String experienceComment;
    private String formatComment;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public boolean isEducationMatch() {
        return educationMatch;
    }

    public void setEducationMatch(boolean educationMatch) {
        this.educationMatch = educationMatch;
    }

    public boolean isExperienceMatch() {
        return experienceMatch;
    }

    public void setExperienceMatch(boolean experienceMatch) {
        this.experienceMatch = experienceMatch;
    }

    public List<String> getFormatTips() {
        return formatTips;
    }

    public void setFormatTips(List<String> formatTips) {
        this.formatTips = formatTips;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(int skillScore) {
        this.skillScore = skillScore;
    }

    public int getEducationScore() {
        return educationScore;
    }

    public void setEducationScore(int educationScore) {
        this.educationScore = educationScore;
    }

    public int getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(int experienceScore) {
        this.experienceScore = experienceScore;
    }

    public int getFormatScore() {
        return formatScore;
    }

    public void setFormatScore(int formatScore) {
        this.formatScore = formatScore;
    }

    public String getSkillComment() {
        return skillComment;
    }

    public void setSkillComment(String skillComment) {
        this.skillComment = skillComment;
    }

    public String getEducationComment() {
        return educationComment;
    }

    public void setEducationComment(String educationComment) {
        this.educationComment = educationComment;
    }

    public String getExperienceComment() {
        return experienceComment;
    }

    public void setExperienceComment(String experienceComment) {
        this.experienceComment = experienceComment;
    }

    public String getFormatComment() {
        return formatComment;
    }

    public void setFormatComment(String formatComment) {
        this.formatComment = formatComment;
    }

    public  ScoreResponse() {
    }
    public ScoreResponse(
            int score,
            List<String> matchedSkills,
            List<String> missingSkills,
            boolean educationMatch,
            boolean experienceMatch,
            List<String> formatTips,
            String summary,
            int skillScore,
            int educationScore,
            int experienceScore,
            int formatScore,
            String skillComment,
            String educationComment,
            String experienceComment,
            String formatComment
    ) {
        this.score = score;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.educationMatch = educationMatch;
        this.experienceMatch = experienceMatch;
        this.formatTips = formatTips;
        this.summary = summary;
        this.skillScore = skillScore;
        this.educationScore = educationScore;
        this.experienceScore = experienceScore;
        this.formatScore = formatScore;
        this.skillComment = skillComment;
        this.educationComment = educationComment;
        this.experienceComment = experienceComment;
        this.formatComment = formatComment;
    }
}
