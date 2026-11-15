import { Search, Sort } from '@mui/icons-material';
import { FormControl, InputAdornment, InputLabel, MenuItem, Select, TextField } from '@mui/material';
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
	const [availabilityFilter, setAvailabilityFilter] = React.useState('ALL');
	const [searchTerm, setSearchTerm] = React.useState("");
	const [sortBy, setSortBy] = React.useState("createdAt");
	const [sortDirection, setSortDirection] = React.useState("DESC");

	const handleGenreChange = (event) => {
		const genreId = event.target.value;
		setSelectedGenreId(genreId);
	}

	console.log("Selected Genre Id : ", selectedGenreId);

	const handleSortChange = (value) => {
		const [field, direction] = value.split("-");

		setSortBy(field);
		setSortDirection(direction.toUpperCase());
	}

	const getCurrentSortValue = () => {
		return `${sortBy}-${sortDirection.toLowerCase()}`;
	}


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
							{/* Genre Filter Section */}
							<GenreFilter onGenreSelect={handleGenreChange} genres={genres} />

							{/* Availability Filter */}
							<div className='bg-white rounded-xl shadow-md p-4 border border-gray-100' >
								<h3 className='text-lg font-bold text-gray-900 mb-4 pb-3 border-b border-gray-200' >
									Availability
								</h3>

								<FormControl fullWidth >

									<Select
										value={availabilityFilter}
										onChange={(e) => setAvailabilityFilter(e.target.value)}
									>
										<MenuItem value={"ALL"} >All Books</MenuItem>
										<MenuItem value={"AVAILABLE"} >Available Only</MenuItem>
										<MenuItem value={"CHECKED_OUT"} >Checked Out</MenuItem>
									</Select>
								</FormControl>


							</div>

						</div>


					</aside>

					{/* Main Content Area */}
					<main className='flex-1 space-y-6' >

						<div className='flex flex-col md:flex-row gap-4' >

							{/* Search Input */}
							<div className='flex-1' >
								<TextField
									fullWidth
									placeholder='Search by Title, Author, Category... '
									value={searchTerm}
									onChange={(e) => setSearchTerm(e.target.value)}
									InputProps={{
										startAdornment: (
											<InputAdornment position='start' >
												<Search className="text-gray-400" />
											</InputAdornment>
										),
									}}
									sx={{
										'& .MuiOutlinedInput-root': {
											'&:hover fieldset': {
												borderColor: '#4F46E5',
											},
											'&.Mui-focused fieldset': {
												borderColor: '#4F46E5',
											},
										}
									}}
								/>
							</div>

							{/* Sort Dropdown */}
							<div className='md:w-64' >
								<FormControl fullWidth >
									<InputLabel>Sort By</InputLabel>
									<Select
										value={getCurrentSortValue()}
										onChange={(e) => handleSortChange(e.target.value)}
										label="Sort By"
										startAdornment={
											<InputAdornment position='start' >
												<Sort className="text-gray-400" />
											</InputAdornment>
										}
										sx={{
											'& .MuiOutlinedInput-notchedOutline': {
												borderColor: '#E5E7EB'
											},
											'&:hover .MuiOutlinedInput-notchedOutline': {
												borderColor: '4F46E5'
											},
											'&.Mui-focused .MuiOutlinedInput-notchedOutline': {
												borderColor: '4F46E5'
											},
										}}
									>
										<MenuItem value="title-acs" >Title (A-Z)</MenuItem>
										<MenuItem value="title-desc" >Title (Z-A)</MenuItem>
										<MenuItem value="author-acs" >Author (A-Z)</MenuItem>
										<MenuItem value="author-desc" >Author (Z-A)</MenuItem>
										<MenuItem value="createdAt-acs" > Newest First</MenuItem>
										<MenuItem value="createdAt-desc" >Oldest First</MenuItem>

									</Select>
								</FormControl>
							</div>

						</div>

					</main>


				</div>
			</div>

		</div>

	)
}

export default BookPage