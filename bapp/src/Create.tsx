import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { AuthContext } from './AuthContext'
import LoginComponent from './LoginComponent'

const Create = () => {
	const [title, setTitle] = useState('')
	const [subtitle, setSubTitle] = useState('')
	const [file, setFile] = useState<File | null>(null)
	const { username } = useContext(AuthContext)
	const navigate = useNavigate()
	const handleSubmit = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
		if (!username || username === '') {
			alert("You're not logged in")
			return
		}

		const formData: any = new FormData()
		formData.append('file', file)
		formData.append('title', title)
		formData.append('subtitle', subtitle)
		formData.append('username', username)

		fetch('http://localhost:8080/api/posts', {
			method: 'POST',
			body: formData,
		})
			.then((_) => {
				navigate('/')
			})
			.catch(alert)
	}
	return (
		<div>
			<div className='min-h-screen'>
				<LoginComponent />
				<div className='w-full grid place-items-center mt-36 '>
					<div className='text-2xl my-4 font-bold underline text-white'>
						Create Blog
					</div>
					<form className='border rounded-md border-white p-4'>
						<input
							className='block w-64 p-2 text-white bg-zinc-900 rounded-md my-3'
							type='text'
							value={title}
							onChange={(e) => setTitle(e.target.value)}
							placeholder='Title'
						/>
						<input
							type='text'
							className='block w-64 p-2 text-white bg-zinc-900 rounded-md my-3'
							value={subtitle}
							onChange={(e) => setSubTitle(e.target.value)}
							placeholder='Subtitle'
						/>
						<input
							className='block w-64 p-2 text-white bg-zinc-900 rounded-md my-3'
							type='file'
							onChange={(e) =>
								setFile((e.target.files && e.target.files[0]) || null)
							}
							placeholder='Upload File'
						/>
						<div className='flex justify-center items-center'>
							<div
								onClick={handleSubmit}
								className='rounded-full p-3 font-bold text-white bg-purple-700 px-4'
							>
								Submit
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	)
}

export default Create
