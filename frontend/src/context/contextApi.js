import React, { useState } from "react";
import { useCookies } from "react-cookie";
import PropTypes from 'prop-types';

const AppContext = React.createContext();

const ContextProvider = ({ children }) => {
    const [cookies, setCookie, removeCookie] = useCookies(["appToken"]);
    const [userSessionData, setUserSessionData] = useState(undefined);

    const setSession = (token) => {
        setCookie("appToken", token, {
            path: "/",
            maxAge: 3600 * 1000, // 1 hour in milliseconds
        });
    };

    const getSession = () => {
        return cookies.appToken || null;
    };

    const setUserData = (userData) => setUserSessionData(userData);

    const getUserData = () => userSessionData;

    const logout = () => {
        removeCookie("appToken", { path: "/" });
        setUserData(undefined);
    };

    return (
        <AppContext.Provider
            value={{
                setSession,
                getSession,
                setUserData,
                getUserData,
                logout,
            }}
        >
            {children}
        </AppContext.Provider>
    );
};

export { AppContext };
export default ContextProvider;


// Define PropTypes
ContextProvider.propTypes = {
    children: PropTypes.node.isRequired, //must be a a renderable element (string, number, React element).
};
