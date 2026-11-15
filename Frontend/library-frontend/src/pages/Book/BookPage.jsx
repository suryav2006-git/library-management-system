import React from 'react';
import GenreFilter from './GenreFilter';

const genres = [
	{
		active: true,
		bookCount: 0,
		code: "FICTION",
		createdAt: "2025-10-10T10:40:08.725525",
		description: "Genre that includes imaginative stories",
		id: 1,
		name: "Fiction",
		parentGenreId: null,
		parentGenreName: null,
		subGenres: null,
		updatedAt: "2025-10-10T10:40:08.725525"
	},
	{
		active: true,
		bookCount: 0,
		code: "NON_FICTION",
		createdAt: "2025-10-10T10:41:15.120410",
		description: "Genre based on real events, facts, and information",
		id: 2,
		name: "Non-Fiction",
		parentGenreId: null,
		parentGenreName: null,
		subGenres: null,
		updatedAt: "2025-10-10T10:41:15.120410"
	},
	{
		active: true,
		bookCount: 0,
		code: "MYSTERY",
		createdAt: "2025-10-10T10:42:05.884120",
		description: "Genre focused on solving a crime or uncovering secrets",
		id: 3,
		name: "Mystery",
		parentGenreId: 1,
		parentGenreName: "Fiction",
		subGenres: null,
		updatedAt: "2025-10-10T10:42:05.884120"
	},
	{
		active: true,
		bookCount: 0,
		code: "SCI_FI",
		createdAt: "2025-10-10T10:43:22.450112",
		description: "Genre exploring futuristic concepts, space, and advanced technology",
		id: 4,
		name: "Science Fiction",
		parentGenreId: 1,
		parentGenreName: "Fiction",
		subGenres: null,
		updatedAt: "2025-10-10T10:43:22.450112"
	},
	{
		active: true,
		bookCount: 0,
		code: "BIOGRAPHY",
		createdAt: "2025-10-10T10:44:10.992301",
		description: "Detailed account of a person's life written by someone else",
		id: 5,
		name: "Biography",
		parentGenreId: 2,
		parentGenreName: "Non-Fiction",
		subGenres: null,
		updatedAt: "2025-10-10T10:44:10.992301"
	}
];



const BookPage = () => {

	const [selectedGenreId, setSelectedGenreId] = React.useState(null);

	const handleGenreChange = (event) => {
		const genreId = event.target.value;
		setSelectedGenreId(genreId);
	}

	console.log("Selected Genre Id : ", selectedGenreId);


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
							<GenreFilter onGenreSelect={handleGenreChange} genres={genres} />
						</div>


					</aside>

				</div>
			</div>

		</div>

	)
}

export default BookPage