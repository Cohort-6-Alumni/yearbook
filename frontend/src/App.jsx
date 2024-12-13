import { Routes, Route, Navigate } from 'react-router';
import Login from './pages/app/Login';
import CompleteProfile from './pages/app/CompleteProfile';

const App = () => {
  return (
    <Routes>
      <Route exact path="/login" element={<Login />} />
      <Route exact path="/completeProfile" element={<CompleteProfile />} />
      <Route exact path="*" element={<Navigate to="/login" />} />
    </Routes>
  );
};

export default App;
