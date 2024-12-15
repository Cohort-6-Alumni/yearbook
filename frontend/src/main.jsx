import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App.jsx';
import { ThemeProvider } from '@material-tailwind/react';
import { BrowserRouter as Router } from 'react-router';
import ContextProvider from "./context/contextApi.js";

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <ThemeProvider>
            <ContextProvider>
                <Router>
                    <App/>
                </Router>
            </ContextProvider>,
        </ThemeProvider>
    </StrictMode>
);
