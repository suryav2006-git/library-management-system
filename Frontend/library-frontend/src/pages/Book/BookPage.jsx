import React from 'react'

const BookPage = () => {
	return (
		<div className='min-h-screen bg-gradient-to-br from-indigo-50 via-white to-purple-50' >
			
			{/* Header */}
			<div className='bg-white border-b border-grey-200 text-center' >
				
				<div className='px-4 sm:px-6 lg:px-8 py-8' >
					
					<div className='text-4xl font-bold text-grey-900 mb-2' >
						
						<h1 className='font-bold' >Browse Our{" "} 
							<span className='bg-gradient-to-r from-indigo-600 to-purple-600 bg-clip-text text-transparent' >
								Collection
							</span>
						</h1>
						<p className='text-lg text-grey-600  ' >
							Discover Thousands of Book Across All Genres
						</p>
							


					</div>

				</div>
			</div>

			{/* Main Content */}
			<div className='px-4 sm:px-6 lg:px-8 py-8' >
				<div className='flex flex-col lg:flex-row gap-8' >
					{/* Sidebar Filter */}
					<aside className='lg:w-72 space-y-6'>
						{/* Filter Container */}
						<div className='space-y-6' >
							<GenreFilter />
						</div>


					</aside>

				</div>
			</div>

		</div>
	)
}

export default BookPage