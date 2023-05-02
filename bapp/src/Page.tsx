import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { AuthContext } from './AuthContext'
import LoginComponent from './LoginComponent'

const Page = () => {
	const { id } = useParams()
	const [title, setTitle] = useState('')
	const [subtitle, setSubtitle] = useState('')
	const [file, setFile] = useState('')
	const [username, setUsername] = useState('')
	const { username: loggedUser } = useContext(AuthContext)
	const navigate = useNavigate()
	useEffect(() => {
		fetch(`http://localhost:8080/api/posts/${id}`)
			.then((res) => res.json())
			.then((data) => {
				setTitle(data.title)
				setSubtitle(data.subtitle)
				setFile(data.file)
				setUsername(data.username)
			})
	}, [])

	const handleDelete = (e: React.MouseEvent<HTMLSpanElement, MouseEvent>) => {
		fetch('http://localhost:8080/api/posts/' + id, {
			method: 'DELETE',
		}).then((_) => {
			navigate('/')
		})
	}
	return (
		<div className='min-h-screen '>
			<LoginComponent />
			<div className='w-[100vw] grid place-items-center'>
				<div className='flex justify-between items-center my-5 w-[600px]'>
					<span>
						<span className='text-center text-white text-xl font-serif'>
							{title + ' '}
						</span>
						<span className='text-center text-zinc-500 text-xl font-serif'>
							- {subtitle}
						</span>
					</span>
					<div className='text-white'>
						{username == loggedUser && (
							<span>
								<span
									className='mx-1 px-4 py-2 rounded-full border border-white cursor-pointer'
									onClick={() => navigate(`/update/${id}`)}
								>
									Update
								</span>
								<span
									className='mx-1 mr-3 px-4 py-2 rounded-full border border-white cursor-pointer'
									onClick={handleDelete}
								>
									Delete
								</span>
							</span>
						)}
						by {username}
					</div>
				</div>
				{/*Render file content as text*/}
				<div className='text-white w-[700px] m-0 break-words'>
					{new TextDecoder('utf-8').decode(
						new Uint8Array([...file].map((char) => char.charCodeAt(0)))
					)}
				</div>
			</div>
		</div>
	)
}

export default Page
