import { Search, Sort } from '@mui/icons-material';
import { FormControl, InputAdornment, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import React from 'react';
import BookCard from './BookCard';
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

const books = [
	{
		"active": true,
		"alreadyHaveLoan": null,
		"alreadyHaveReservation": null,
		"author": "Ashok",
		"availableCopies": 3,
		"coverImageUrl": "https://media.istockphoto.com/id/537331536/photo/programming-code-abstract-technology-background-of-software-deve.jpg?s=1024x1024&w=is&k=20&c=BUB6a2pu1aqFRoRVD8gdck_m9eZ_ae4sWyvTs6axwNE=",
		"createdAt": "2025-10-26T09:15:12.114201",
		"description": "A comprehensive guide to building scalable microservices using Spring Cloud and Docker.",
		"genreCode": "PROGRAMMING",
		"genreId": 10,
		"genreName": "PROGRAMMING",
		"id": 10,
		"isbn": "978-1-4028-9463-3",
		"language": "English",
		"pages": 410,
		"price": 599,
		"publicationDate": "2024-08-15",
		"publisher": "Mr Studies Helper",
		"title": "Microservices with Spring Cloud",
		"totalCopies": 5,
		"updatedAt": "2025-12-26T11:20:10.45120"
	},
	{
		"active": true,
		"alreadyHaveLoan": null,
		"alreadyHaveReservation": null,
		"author": "Priya Nair",
		"availableCopies": 2,
		"coverImageUrl": "https://media.istockphoto.com/id/1389287506/photo/react-inscription-against-laptop-and-code-background.jpg?s=1024x1024&w=is&k=20&c=E8im8d3k0ng5M8eXChH6YKd8aaT81yaRHFHrnCFCUfw=",
		"createdAt": "2025-10-27T14:22:45.882104",
		"description": "Learn modern React patterns, hooks, and performance optimization techniques.",
		"genreCode": "WEB_DEV",
		"genreId": 12,
		"genreName": "WEB DEVELOPMENT",
		"id": 11,
		"isbn": "978-1-4028-9464-0",
		"language": "English",
		"pages": 285,
		"price": 450,
		"publicationDate": "2024-09-10",
		"publisher": "TechPress India",
		"title": "Modern React Architecture",
		"totalCopies": 4,
		"updatedAt": "2025-12-27T16:05:33.19842"
	},
	{
		"active": true,
		"alreadyHaveLoan": null,
		"alreadyHaveReservation": null,
		"author": "Vikram Sharma",
		"availableCopies": 1,
		"coverImageUrl": "https://media.istockphoto.com/id/1162706973/photo/data-structures-concept-with-random-parts-of-program-code-data-structures-with-programming.jpg?s=1024x1024&w=is&k=20&c=3-Ym0s_BxFbNuiVfu9rx2gkih04OECSwglKwa5p7wRQ=",
		"createdAt": "2025-11-01T08:40:19.330912",
		"description": "Master core data structures and algorithms with practical Java implementations.",
		"genreCode": "COMPUTER_SCIENCE",
		"genreId": 15,
		"genreName": "COMPUTER SCIENCE",
		"id": 12,
		"isbn": "978-1-4028-9465-7",
		"language": "English",
		"pages": 520,
		"price": 650,
		"publicationDate": "2024-06-20",
		"publisher": "CodeCraft Publications",
		"title": "Data Structures and Algorithms in Java",
		"totalCopies": 3,
		"updatedAt": "2025-12-28T09:12:44.78210"
	},
	{
		"active": true,
		"alreadyHaveLoan": null,
		"alreadyHaveReservation": null,
		"author": "Ananya Iyer",
		"availableCopies": 4,
		"coverImageUrl": "https://media.istockphoto.com/id/1673063844/vector/sql-dtabase-flat-design.jpg?s=1024x1024&w=is&k=20&c=i--89940C8xbdOCxB2B96Ey7Ei7C07s-mN7A918ZXNg=",
		"createdAt": "2025-11-05T11:10:05.512390",
		"description": "Deep dive into indexing, query planning, and high-availability database architecture.",
		"genreCode": "DATABASES",
		"genreId": 18,
		"genreName": "DATABASES",
		"id": 13,
		"isbn": "978-1-4028-9466-4",
		"language": "English",
		"pages": 360,
		"price": 525,
		"publicationDate": "2024-10-05",
		"publisher": "DataSphere Press",
		"title": "PostgreSQL Internals & Optimization",
		"totalCopies": 6,
		"updatedAt": "2025-12-29T14:30:19.10455"
	},
	{
		"active": true,
		"alreadyHaveLoan": null,
		"alreadyHaveReservation": null,
		"author": "Rohan Deshmukh",
		"availableCopies": 2,
		"coverImageUrl": "https://media.istockphoto.com/id/1493300343/vector/kubernetes-development-environment-infographic-icon-set.jpg?s=1024x1024&w=is&k=20&c=KS0EH-AMFCW-PTu0tPTmJOM2S0aJmRBrpON-n-FZFsc=",
		"createdAt": "2025-11-12T17:45:30.908123",
		"description": "Practical strategies for deploying, securing, and monitoring containerized workloads at scale.",
		"genreCode": "DEVOPS",
		"genreId": 20,
		"genreName": "DEVOPS & CLOUD",
		"id": 14,
		"isbn": "978-1-4028-9467-1",
		"language": "English",
		"pages": 445,
		"price": 699,
		"publicationDate": "2024-11-18",
		"publisher": "CloudNative Books",
		"title": "Kubernetes in Production",
		"totalCopies": 4,
		"updatedAt": "2025-12-30T10:18:52.66301"
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

						{/* Search And Sort */}
						<div className='flex flex-col md:flex-row gap-4' >

							{/* Search Input */}
							<div className='flex-1' >
								<TextField
									fullWidth
									placeholder='Search by Title, Author, Category... '
									value={searchTerm}
									onChange={(e) => setSearchTerm(e.target.value)}
									slotProps={{
										input: {
											startAdornment: (
												<InputAdornment position='start' >
													<Search className="text-gray-400" />
												</InputAdornment>
											),
										}
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
										<MenuItem value="title-asc" >Title (A-Z)</MenuItem>
										<MenuItem value="title-desc" >Title (Z-A)</MenuItem>
										<MenuItem value="author-asc" >Author (A-Z)</MenuItem>
										<MenuItem value="author-desc" >Author (Z-A)</MenuItem>
										<MenuItem value="createdAt-asc" > Newest First</MenuItem>
										<MenuItem value="createdAt-desc" >Oldest First</MenuItem>

									</Select>
								</FormControl>
							</div>

						</div>

						{/* Books Grid */}
						<div className='grid gird-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6' >

							{books.map((book) => (<BookCard key={book.id} book={book} />))}

						</div>

					</main>


				</div>
			</div>

		</div>

	)
}

export default BookPage