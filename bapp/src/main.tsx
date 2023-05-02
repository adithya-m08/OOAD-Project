import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import Home from './Home'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Create from './Create'
import Update from './Update'
import Page from './Page'
import AuthProvider from './AuthContext'


const router = createBrowserRouter([
    {
        path: "/",
        element: <Home/>
    },
    {
        path: "/create",
        element: <Create/>
    },
    {
        path: "/update/:id",
        element: <Update/>
    },
    {
        path: "/page/:id",
        element: <Page/>
    }
])

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<React.StrictMode>
        <AuthProvider>
            <RouterProvider router={router}/>
        </AuthProvider>
	</React.StrictMode>
)
