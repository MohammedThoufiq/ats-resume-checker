import React, { useState } from "react";
import axios from "axios";

const ResumeScoreChecker = () => {
  const [file, setFile] = useState(null);
  const [jobDescription, setJobDescription] = useState("");
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file || !jobDescription) return;

    const formData = new FormData();
    formData.append("file", file);
    formData.append("jobDescription", jobDescription);

    try {
      setLoading(true);
      const res = await axios.post("http://localhost:8080/resume/score", formData);
      setResult(res.data);
    } catch (error) {
      console.error("Error scoring resume:", error);
      alert("Error scoring resume. Check backend console or network tab.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center px-4 py-6">
      <div className="bg-white p-6 rounded-xl shadow-md w-full max-w-2xl">
        <h1 className="text-2xl font-bold mb-4 text-center text-blue-700">ATS Resume Score Checker</h1>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="file"
            accept="application/pdf"
            onChange={(e) => setFile(e.target.files[0])}
            className="border p-2 w-full rounded"
          />
          <textarea
            rows={6}
            placeholder="Paste Job Description here..."
            className="w-full border p-2 rounded"
            value={jobDescription}
            onChange={(e) => setJobDescription(e.target.value)}
          />
          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded font-medium"
          >
            {loading ? "Scoring..." : "Check Score"}
          </button>
        </form>

        {result && (
          <div className="bg-gray-50 p-4 mt-6 rounded-lg shadow-sm space-y-4">
            <h2 className="text-xl font-semibold">Score: {result.score}/100</h2>
            <div className="grid grid-cols-2 gap-4 text-sm">
              <p><strong>Skills:</strong> {result.skillScore}/50 – {result.skillComment}</p>
              <p><strong>Education:</strong> {result.educationScore}/20 – {result.educationComment}</p>
              <p><strong>Experience:</strong> {result.experienceScore}/20 – {result.experienceComment}</p>
              <p><strong>Format:</strong> {result.formatScore}/10 – {result.formatComment}</p>
            </div>

            <p><strong>Matched Skills:</strong> {result.matchedSkills.join(", ")}</p>
            <p><strong>Missing Skills:</strong> {result.missingSkills.join(", ")}</p>

            {result.formatTips?.length > 0 && (
              <ul className="list-disc list-inside">
                {result.formatTips.map((tip, i) => (
                  <li key={i}>{tip}</li>
                ))}
              </ul>
            )}

            <p className="text-green-700 font-medium">Summary: {result.summary}</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default ResumeScoreChecker;
