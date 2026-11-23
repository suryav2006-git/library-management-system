
import { useState } from "react";
import { Bookmark, Search } from "@mui/icons-material";
import WishlistBookCard from "./WishlistBookCard";
import "./Wishlist.css";

const initialBooks = [
    {
        id: 1,
        title: "Lord of the Mysteries",
        author: "Cuttlefish That Loves Diving",
        category: "Fantasy",
        status: "AVAILABLE",
        coverColor: "purple",
    },
    {
        id: 2,
        title: "Omniscient Reader's Viewpoint",
        author: "Sing Shong",
        category: "Fantasy",
        status: "AVAILABLE",
        coverColor: "blue",
    },
    {
        id: 3,
        title: "The Wings of Fire",
        author: "Tui T. Sutherland",
        category: "Fantasy",
        status: "UNAVAILABLE",
        coverColor: "orange",
    },
    {
        id: 4,
        title: "I'm Not a Regressor",
        author: "Unknown",
        category: "Fantasy",
        status: "AVAILABLE",
        coverColor: "green",
    },
];

const Wishlist = () => {
    const [books, setBooks] = useState(initialBooks);
    const [searchTerm, setSearchTerm] = useState("");

    const removeBook = (id) => {
        setBooks((currentBooks) =>
            currentBooks.filter((book) => book.id !== id)
        );
    };

    const filteredBooks = books.filter((book) =>
        `${book.title} ${book.author}`
            .toLowerCase()
            .includes(searchTerm.toLowerCase())
    );

    return (
        <div className="wishlist-page">

            {/* Header */}
            <div className="wishlist-header">
                <div>
                    <div className="wishlist-title">
                        <Bookmark className="wishlist-title-icon" />
                        <h1>My Wishlist</h1>
                    </div>

                    <p>Keep track of the books you want to read next.</p>
                </div>

                <div className="wishlist-count">
                    {books.length} Saved Books
                </div>
            </div>

            {/* Search */}
            <div className="wishlist-search">
                <Search />
                <input
                    type="text"
                    placeholder="Search books or authors..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
            </div>

            {/* Book Grid */}
            {filteredBooks.length > 0 ? (
                <div className="wishlist-grid">
                    {filteredBooks.map((book) => (
                        <WishlistBookCard
                            key={book.id}
                            book={book}
                            onRemove={removeBook}
                        />
                    ))}
                </div>
            ) : (
                <div className="wishlist-empty">
                    <Bookmark />
                    <h2>No books found</h2>
                    <p>
                        {books.length === 0
                            ? "Your wishlist is empty. Add books you want to read."
                            : "Try searching with another book title or author."}
                    </p>
                </div>
            )}
        </div>
    );
};

export default Wishlist;
