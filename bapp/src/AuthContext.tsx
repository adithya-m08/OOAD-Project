import React, { createContext, useState } from 'react';

interface AuthContextType {
  username: string;
  password: string;
  loggedIn: boolean
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
  setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
}

export const AuthContext = createContext<AuthContextType>({
  username: '',
  password: '',
  loggedIn: false,
  setUsername: () => {},
  setLoggedIn: () => {},
  setPassword: () => {},
});

interface Props {
  children: React.ReactNode;
}

const AuthProvider: React.FC<Props> = ({ children }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loggedIn, setLoggedIn] = useState(false)

  return (
    <AuthContext.Provider value={{ username, password, loggedIn, setLoggedIn, setUsername, setPassword }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
