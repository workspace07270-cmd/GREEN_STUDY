import { useState } from "react";

function App() {
  const [searchQuery, setSearchQuery] = useState("");
  const [hospitals, setHospitals] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchHospitals = async () => {
    setLoading(true);
    setError(null);

    try {
      const endpoint = searchQuery
        ? `/hospitals/search?name=${encodeURIComponent(searchQuery)}`
        : "/hospitals";
      const response = await fetch(endpoint);
      if (!response.ok) {
        const message = await response.text();
        throw new Error(message || "조회에 실패했습니다.");
      }
      const data = await response.json();
      setHospitals(data);
    } catch (err) {
      setError(err.message || "데이터를 가져오지 못했습니다.");
      setHospitals([]);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    fetchHospitals();
  };

  return (
    <div className="app-container">
      <h1>양천구 병원 검색</h1>

      <form className="search-form" onSubmit={handleSubmit}>
        <input
          type="text"
          value={searchQuery}
          placeholder="병원 명을 입력하세요"
          onChange={(event) => setSearchQuery(event.target.value)}
        />
        <button type="submit">검색</button>
      </form>

      {loading && <p className="status">조회 중...</p>}
      {error && <p className="status error">{error}</p>}

      {!loading && hospitals.length > 0 && (
        <div className="table-wrapper">
          <table>
            <thead>
              <tr>
                <th>병원명</th>
                <th>종별</th>
                <th>전화번호</th>
                <th>주소</th>
                <th>개설일자</th>
              </tr>
            </thead>
            <tbody>
              {hospitals.map((hospital) => (
                <tr key={hospital.ykiho}>
                  <td>{hospital.yadmNm}</td>
                  <td>{hospital.clCdNm}</td>
                  <td>{hospital.telno}</td>
                  <td>{hospital.addr}</td>
                  <td>{hospital.estbDd}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {!loading && hospitals.length === 0 && !error && (
        <p className="status">검색어를 입력하고 검색 버튼을 눌러주세요.</p>
      )}
    </div>
  );
}

export default App;
