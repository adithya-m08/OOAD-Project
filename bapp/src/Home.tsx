import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import LoginComponent from './LoginComponent'

const Home = () => {
	const [posts, setPosts] = useState<
		{
			title: string
			subtitle: string
			id: number
		}[]
	>([])
	useEffect(() => {
		fetch('http://localhost:8080/api/posts')
			.then((res) => res.json())
			.then((data) => setPosts(data))
			.catch(console.log)
	}, [])
	const navigate = useNavigate()
	return (
		<div className='min-h-screen'>
			<LoginComponent />
			<div className='grid justify-items-center content-center'>
				<p className='text-center text-white w-[700px] m-4 mt-5 text-xl font-serif'>
					Blogging App
				</p>
				<div className='flex items-baseline gap-3 w-[600px] py-3 font-extrabold text-gray-100 text-2xl'>
					<span>Articles</span>
				</div>
				{posts.map((post) => {
					return (
						<div
							className='w-[600px]'
							key={post.id}
							onClick={(_) => navigate(`/page/${post.id}`)}
						>
							<div className='p-2 grid gap-3 justify-items-center'>
								<div className='w-full bg-zinc-900 p-5 rounded-lg font-serif'>
									<a className='text-2xl font-bold text-zinc-100'>
										{post.title}
									</a>
									<p className='text-zinc-100 py-3 text-lg'>{post.subtitle}</p>
									<div className='flex justify-end'>
										<div className='mt-3 px-3 py-2 font-sans font-bold text-white rounded-lg bg-zinc-800 cursor-pointer'>
											Read More
										</div>
									</div>
								</div>
							</div>
						</div>
					)
				})}
			</div>
		</div>
	)
}

export default Home
