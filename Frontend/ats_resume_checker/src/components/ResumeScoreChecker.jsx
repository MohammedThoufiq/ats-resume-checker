import React, { useState } from "react";
import axios from "axios";
import { FaFileUpload, FaClipboardList } from "react-icons/fa";

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
    <div className="min-h-screen bg-gradient-to-br from-purple-500 via-indigo-500 to-blue-500 flex items-center justify-center px-6 py-10">
      <div className="bg-white p-10 rounded-3xl shadow-2xl w-full max-w-2xl">
        <h1 className="text-4xl font-extrabold text-center text-indigo-800 mb-8">
          ATS Resume Score Checker
        </h1>
        <p className="text-center text-lg text-gray-600 mb-10">
          Upload your resume and the job description to see how well your resume fits the job.
        </p>
        <form onSubmit={handleSubmit} className="space-y-8">
          <div>
            <label className="block text-lg font-medium text-gray-700 mb-3 flex items-center gap-2">
              <FaFileUpload className="text-indigo-600" /> Resume (PDF)
            </label>
            <input
              type="file"
              accept="application/pdf"
              onChange={(e) => setFile(e.target.files[0])}
              className="w-full p-4 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>
          <div>
            <label className="block text-lg font-medium text-gray-700 mb-3 flex items-center gap-2">
              <FaClipboardList className="text-indigo-600" /> Job Description
            </label>
            <textarea
              rows={6}
              placeholder="Paste Job Description here..."
              className="w-full p-4 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
              value={jobDescription}
              onChange={(e) => setJobDescription(e.target.value)}
            />
          </div>
          <div className="text-center">
            <button
              type="submit"
              disabled={loading}
              className="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-3 px-6 rounded-lg font-semibold transition-all duration-300 flex items-center justify-center gap-2"
            >
              {loading ? (
                <>
                  <span className="loader"></span> Scoring...
                </>
              ) : (
                "Check Resume Score"
              )}
            </button>
          </div>
        </form>

        {result && (
          <div className="bg-gray-50 p-8 mt-10 rounded-lg shadow-md space-y-6">
            <h2 className="text-2xl font-semibold text-indigo-700 text-center">
              Score: {result.score}/100
            </h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 text-sm text-gray-700">
              <p>
                <strong>Skills:</strong> {result.skillScore}/50 – {result.skillComment}
              </p>
              <p>
                <strong>Education:</strong> {result.educationScore}/20 – {result.educationComment}
              </p>
              <p>
                <strong>Experience:</strong> {result.experienceScore}/20 – {result.experienceComment}
              </p>
              <p>
                <strong>Format:</strong> {result.formatScore}/10 – {result.formatComment}
              </p>
            </div>

            <p className="text-center">
              <strong>Matched Skills:</strong> {result.matchedSkills.join(", ")}
            </p>
            <p className="text-center">
              <strong>Missing Skills:</strong> {result.missingSkills.join(", ")}
            </p>

            {result.formatTips?.length > 0 && (
              <ul className="list-disc list-inside mt-4 text-sm text-gray-700">
                {result.formatTips.map((tip, i) => (
                  <li key={i}>{tip}</li>
                ))}
              </ul>
            )}

            <p className="text-green-700 font-medium text-center mt-6">
              Summary: {result.summary}
            </p>
          </div>
        )}
      </div>
    </div>
  );
};

export default ResumeScoreChecker;