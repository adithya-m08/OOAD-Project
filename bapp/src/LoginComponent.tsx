import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { AuthContext } from './AuthContext'

const LoginComponent = () => {
	const {
		username,
		setUsername,
		password,
		setPassword,
		loggedIn,
		setLoggedIn,
	} = useContext(AuthContext)
    const navigate = useNavigate()
	const handleClick = (e: React.MouseEvent<HTMLSpanElement, MouseEvent>) => {
		if (!loggedIn) {
			const p = prompt('Enter your username: ')
			console.log(p)
			setUsername(p ? p : '')
			const pass = prompt('Enter your password: ')
			console.log(pass)
			setPassword(pass ? pass : '')
			fetch('http://localhost:8080/api/users', {
				method: 'POST',
				body: JSON.stringify({
					username: p,
					password: pass,
					role: 'READER',
				}),
				headers: new Headers({
					'content-type': 'application/json',
				}),
			})
		} else {
			setUsername('')
			setPassword('')
		}
		setLoggedIn(!loggedIn)
	}
	return (
		<div className='flex items-center relative bg-purple-600 text-white'>
			<div className='flex-1 font-sans p-4 cursor-pointer' onClick={() => navigate('/')}>
				Home
			</div>
            {loggedIn && <span className='px-3 cursor-pointer' onClick={() => navigate('/create')}>
                Create Blog
            </span>}
			<div className='font-sans p-4 bg-purple-700 cursor-pointer'>
				<span onClick={handleClick}>
					{loggedIn ? `${'Hello, ' + username + ' |  Logout'}` : `Login`} 
				</span>
			</div>
		</div>
	)
}

export default LoginComponent
